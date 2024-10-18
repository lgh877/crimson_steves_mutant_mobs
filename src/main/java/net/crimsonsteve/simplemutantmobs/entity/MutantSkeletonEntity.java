
package net.crimsonsteve.simplemutantmobs.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.crimsonsteve.simplemutantmobs.procedures.ShouldStopMSkeleProcedure;
import net.crimsonsteve.simplemutantmobs.procedures.MutantSkeletonSpawnConditionProcedure;
import net.crimsonsteve.simplemutantmobs.procedures.MutantSkeletonOnInitialEntitySpawnProcedure;
import net.crimsonsteve.simplemutantmobs.procedures.MutantSkeletonOnEntityTickUpdateProcedure;
import net.crimsonsteve.simplemutantmobs.procedures.MutantSkeletonEntityScaleProcedure;
import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModEntities;
import net.crimsonsteve.simplemutantmobs.MutantSkeletonMoveControl;

import javax.annotation.Nullable;

public class MutantSkeletonEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_attackType = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_attackProgress = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_particleSettings = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_scale = SynchedEntityData.defineId(MutantSkeletonEntity.class, EntityDataSerializers.INT);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public MutantSkeletonEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(CrimsonstevesMutantMobsModEntities.MUTANT_SKELETON.get(), world);
	}

	public MutantSkeletonEntity(EntityType<MutantSkeletonEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setMaxUpStep(1.5f);
		this.moveControl = new MutantSkeletonMoveControl(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "simple_mutant_skeleton");
		this.entityData.define(DATA_attackType, 0);
		this.entityData.define(DATA_attackProgress, 0);
		this.entityData.define(DATA_particleSettings, 0);
		this.entityData.define(DATA_scale, 10);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractGolem.class, true, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Villager.class, true, false));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 0;
			}

			@Override
			public boolean canUse() {
				double x = MutantSkeletonEntity.this.getX();
				double y = MutantSkeletonEntity.this.getY();
				double z = MutantSkeletonEntity.this.getZ();
				Entity entity = MutantSkeletonEntity.this;
				Level world = MutantSkeletonEntity.this.level();
				return super.canUse() && ShouldStopMSkeleProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MutantSkeletonEntity.this.getX();
				double y = MutantSkeletonEntity.this.getY();
				double z = MutantSkeletonEntity.this.getZ();
				Entity entity = MutantSkeletonEntity.this;
				Level world = MutantSkeletonEntity.this.level();
				return super.canContinueToUse() && ShouldStopMSkeleProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1) {
			@Override
			public boolean canUse() {
				double x = MutantSkeletonEntity.this.getX();
				double y = MutantSkeletonEntity.this.getY();
				double z = MutantSkeletonEntity.this.getZ();
				Entity entity = MutantSkeletonEntity.this;
				Level world = MutantSkeletonEntity.this.level();
				return super.canUse() && ShouldStopMSkeleProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MutantSkeletonEntity.this.getX();
				double y = MutantSkeletonEntity.this.getY();
				double z = MutantSkeletonEntity.this.getZ();
				Entity entity = MutantSkeletonEntity.this;
				Level world = MutantSkeletonEntity.this.level();
				return super.canContinueToUse() && ShouldStopMSkeleProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				double x = MutantSkeletonEntity.this.getX();
				double y = MutantSkeletonEntity.this.getY();
				double z = MutantSkeletonEntity.this.getZ();
				Entity entity = MutantSkeletonEntity.this;
				Level world = MutantSkeletonEntity.this.level();
				return super.canUse() && ShouldStopMSkeleProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MutantSkeletonEntity.this.getX();
				double y = MutantSkeletonEntity.this.getY();
				double z = MutantSkeletonEntity.this.getZ();
				Entity entity = MutantSkeletonEntity.this;
				Level world = MutantSkeletonEntity.this.level();
				return super.canContinueToUse() && ShouldStopMSkeleProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(8, new FloatGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_death"));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.FALL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		MutantSkeletonOnInitialEntitySpawnProcedure.execute(this);
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putInt("DataattackType", this.entityData.get(DATA_attackType));
		compound.putInt("DataattackProgress", this.entityData.get(DATA_attackProgress));
		compound.putInt("DataparticleSettings", this.entityData.get(DATA_particleSettings));
		compound.putInt("Datascale", this.entityData.get(DATA_scale));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.contains("DataattackType"))
			this.entityData.set(DATA_attackType, compound.getInt("DataattackType"));
		if (compound.contains("DataattackProgress"))
			this.entityData.set(DATA_attackProgress, compound.getInt("DataattackProgress"));
		if (compound.contains("DataparticleSettings"))
			this.entityData.set(DATA_particleSettings, compound.getInt("DataparticleSettings"));
		if (compound.contains("Datascale"))
			this.entityData.set(DATA_scale, compound.getInt("Datascale"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		MutantSkeletonOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		Entity entity = this;
		Level world = this.level();
		double x = this.getX();
		double y = entity.getY();
		double z = entity.getZ();
		return super.getDimensions(p_33597_).scale((float) MutantSkeletonEntityScaleProcedure.execute(entity));
	}

	public static void init() {
		SpawnPlacements.register(CrimsonstevesMutantMobsModEntities.MUTANT_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return MutantSkeletonSpawnConditionProcedure.execute(world, x, y, z);
		});
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
		builder = builder.add(Attributes.MAX_HEALTH, 150);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 7);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.simple_mutant_skeleton.walk"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.simple_mutant_skeleton.idle"));
		}
		return PlayState.STOP;
	}

	String prevAnim = "empty";

	private PlayState procedurePredicate(AnimationState event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
			if (!this.animationprocedure.equals(prevAnim))
				event.getController().forceAnimationReset();
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			prevAnim = "empty";
			return PlayState.STOP;
		}
		prevAnim = this.animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(MutantSkeletonEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 0, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 0, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
