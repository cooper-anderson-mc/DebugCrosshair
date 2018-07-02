package ninja.cooperstuff.debugcrosshair;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = DebugCrosshair.MODID)
public class DCConfig {
	@Config.Name("Enabled")
	@Config.Comment({"Simple way to toggle all features", "(without restarting)"})
	public static boolean enabled = true;

	public static void saveConfig() {
		ConfigManager.sync(DebugCrosshair.MODID, Config.Type.INSTANCE);
	}

	@Mod.EventBusSubscriber(modid = DebugCrosshair.MODID)
	private static class ConfigHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(DebugCrosshair.MODID)) {
				saveConfig();
			}
		}
	}
}
