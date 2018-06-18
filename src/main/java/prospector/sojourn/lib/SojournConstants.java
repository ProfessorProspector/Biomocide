package prospector.sojourn.lib;

public class SojournConstants {
	public static final String MOD_ID = "sojourn";
	public static final String MOD_NAME = "Sojourn";
	public static final String VERSION = "@version@";
	public static final String MINECRAFT_VERSION = "@mcversion@";
	public static final String FORGE_VERSION = "@forgeversion@";
	public static final String DEPENDENCIES = true ? "" : "required-after:forge@[" + FORGE_VERSION + ",);";
	public static final String SERVER_PROXY_CLASS = "prospector.sojourn.SojournServer";
	public static final String CLIENT_PROXY_CLASS = "prospector.sojourn.SojournClient";
}
