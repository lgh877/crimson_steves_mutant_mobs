package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModEntities;

public class MutantSkeletonDeathTimeIsReachedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.FLASH, x, y, z, 5, 0, 1, 1, 0);
		if (world instanceof ServerLevel _serverLevel) {
			Entity entityinstance = CrimsonstevesMutantMobsModEntities.STUNT_SKELETON_UPPER_BODY.get().create(_serverLevel, null, null, BlockPos.containing(x, y + entity.getBbWidth(), z), MobSpawnType.MOB_SUMMONED, false, false);
			if (entityinstance != null) {
				entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
				if (!(entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
						? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
						: "").isEmpty()) {
					{
						Entity _entityTeam = entityinstance;
						PlayerTeam _pt = _entityTeam.level().getScoreboard()
								.getPlayerTeam((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
										? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
										: ""));
						if (_pt != null) {
							if (_entityTeam instanceof Player _player)
								_entityTeam.level().getScoreboard().addPlayerToTeam(_player.getGameProfile().getName(), _pt);
							else
								_entityTeam.level().getScoreboard().addPlayerToTeam(_entityTeam.getStringUUID(), _pt);
						}
					}
				}
				if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
					if (entityinstance instanceof Mob _entity && (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof LivingEntity _ent)
						_entity.setTarget(_ent);
				}
				_serverLevel.addFreshEntity(entityinstance);
			}
		}
	}
}
