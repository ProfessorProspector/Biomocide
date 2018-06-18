package prospector.sojourn;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import prospector.sojourn.config.SojournConfig;
import prospector.sojourn.lib.SojournConstants;
import prospector.sojourn.world.WorldTypeSingular;

@Mod(modid = SojournConstants.MOD_ID, name = SojournConstants.MOD_NAME, version = SojournConstants.VERSION)
@Mod.EventBusSubscriber(modid = SojournConstants.MOD_ID)
public class Sojourn {

	public static final String[] SELECTED_INDEX = new String[] { "M", "field_146331_K", "selectedIndex" };
	public static final String[] MAP_TYPE_BUTTON = new String[] { "F", "field_146320_D", "btnMapType" };
	@Mod.Instance
	public static Sojourn instance;
	@SidedProxy(clientSide = SojournConstants.CLIENT_PROXY_CLASS, serverSide = SojournConstants.SERVER_PROXY_CLASS)
	public static SojournCommon proxy;
	public WorldType worldType;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void openGUI(GuiScreenEvent.DrawScreenEvent event) {
		if (SojournConfig.forceSojurnWorlds && event.getGui() instanceof GuiCreateWorld) {
			GuiCreateWorld create = (GuiCreateWorld) event.getGui();
			ReflectionHelper.setPrivateValue(GuiCreateWorld.class, create, instance.worldType.getId(), SELECTED_INDEX);
			GuiButton button = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, create, MAP_TYPE_BUTTON);
			if (button != null) {
				button.enabled = false;
			}
		}
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SojournConfig.initialize(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		worldType = new WorldTypeSingular();
	}

}
