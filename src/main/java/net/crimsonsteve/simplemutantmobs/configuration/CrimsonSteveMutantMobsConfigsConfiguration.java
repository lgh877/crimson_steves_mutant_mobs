package net.crimsonsteve.simplemutantmobs.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class CrimsonSteveMutantMobsConfigsConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> MUTANTSKELETONSPAWNROLLS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> MUTANTSKELETONSHOULDSPAWNNEARBYSKELETONS;
	public static final ForgeConfigSpec.ConfigValue<Double> MUTANTSKELETONSCALE;
	static {
		BUILDER.push("mutantSkeleton");
		MUTANTSKELETONSPAWNROLLS = BUILDER.define("spawnRolls", (double) 100);
		MUTANTSKELETONSHOULDSPAWNNEARBYSKELETONS = BUILDER.define("shouldSpawnNearbySkeletons", true);
		MUTANTSKELETONSCALE = BUILDER.define("scale", (double) 1);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
