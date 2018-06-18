package prospector.sojourn;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import prospector.sojourn.config.SojournConfig;

public class SojournServer extends SojournCommon {

	public static final String[] SERVER_SETTINGS = new String[] { "q", "field_71340_o", "settings" };

	@Override
	public void init(FMLInitializationEvent event) {
		if (SojournConfig.forceSojurnWorlds) {
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (server instanceof DedicatedServer) {
				DedicatedServer dedi = (DedicatedServer) server;
				PropertyManager manager = ReflectionHelper.getPrivateValue(DedicatedServer.class, dedi, SERVER_SETTINGS);
				manager.setProperty("level-type", "sojourn");
				manager.saveProperties();
			}
		}
	}
}
