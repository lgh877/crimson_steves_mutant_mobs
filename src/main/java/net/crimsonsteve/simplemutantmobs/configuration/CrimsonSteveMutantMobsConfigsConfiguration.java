package net.crimsonsteve.simplemutantmobs.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class CrimsonSteveMutantMobsConfigsConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> MUTANTSKELETONSPAWNROLLS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> MUTANTSKELETONSHOULDSPAWNNEARBYSKELETONS;
	public static final ForgeConfigSpec.ConfigValue<Double> HOPKELETONSPAWNROLLS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> HOPKELETONSHOULDSPAWNNEARBYSKELETONS;
	public static final ForgeConfigSpec.ConfigValue<Double> WITHEREDHOPKELETONSPAWNROLLS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> WITHEREDHOPKELETONSHOULDSPAWNNEARBYSKELETONS;
	static {
		BUILDER.push("mutantSkeleton");
		MUTANTSKELETONSPAWNROLLS = BUILDER.define("spawnRolls", (double) 50);
		MUTANTSKELETONSHOULDSPAWNNEARBYSKELETONS = BUILDER.define("shouldSpawnNearbySkeletons", true);
		BUILDER.pop();
		BUILDER.push("hopkeleton");
		HOPKELETONSPAWNROLLS = BUILDER.define("spawnRolls", (double) 10);
		HOPKELETONSHOULDSPAWNNEARBYSKELETONS = BUILDER.define("shouldSpawnNearbySkeletons", true);
		BUILDER.pop();
		BUILDER.push("witheredHopkeleton");
		WITHEREDHOPKELETONSPAWNROLLS = BUILDER.define("spawnRolls", (double) 10);
		WITHEREDHOPKELETONSHOULDSPAWNNEARBYSKELETONS = BUILDER.define("shouldSpawnNearbySkeletons", true);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
