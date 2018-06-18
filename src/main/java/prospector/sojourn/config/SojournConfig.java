package prospector.sojourn.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.sojourn.lib.SojournConstants;

import java.io.File;

public class SojournConfig {
	public static File configDir;
	public static File mainConfig;
	public static Configuration config;
	public static SojournConfig sojournConfiguration;

	public static String sojournBiome = "minecraft:forest";

	public static boolean forceSojurnWorlds = true;

	private static SojournConfig instance = null;

	private SojournConfig() {
		config = new Configuration(mainConfig);
		config.load();

		sojournBiome = config.get(Configuration.CATEGORY_GENERAL, "sojournBiome", sojournBiome, "The registry name for the biome Sojourn uses for worlds. [Example: traverse:autumnal_woods]").getString();

		forceSojurnWorlds = config.get(Configuration.CATEGORY_CLIENT, "forceSojurnWorlds", forceSojurnWorlds, "When true, users will not be able to make non-sojourn worlds").getBoolean();

		config.save();
	}

	public static SojournConfig initialize() {
		if (instance == null)
			instance = new SojournConfig();
		else
			throw new IllegalStateException("Cannot initialize config twice");

		return instance;
	}

	public static SojournConfig getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Instance of config requested before initialization");
		}
		return instance;
	}

	public static void initialize(FMLPreInitializationEvent event) {
		configDir = new File(event.getModConfigurationDirectory(), SojournConstants.MOD_ID);
		if (!configDir.exists()) {
			configDir.mkdir();
		}

		mainConfig = new File(configDir, "sojourn.cfg");
		sojournConfiguration = initialize();
	}
}
