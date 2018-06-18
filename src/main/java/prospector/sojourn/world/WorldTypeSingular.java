package prospector.sojourn.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import prospector.sojourn.config.SojournConfig;

import java.security.InvalidParameterException;
import java.util.Objects;

public class WorldTypeSingular extends WorldType {
	public WorldTypeSingular() {
		super("sojourn");
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		try {
			return new BiomeProviderSingle(Objects.requireNonNull(ForgeRegistries.BIOMES.getValue(new ResourceLocation(SojournConfig.sojournBiome))));
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new InvalidParameterException("Invalid biome registry name: " + SojournConfig.sojournBiome + " was set in Sojourn's biome config.");
		}
	}
}
