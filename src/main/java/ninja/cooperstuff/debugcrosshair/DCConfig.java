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


	@Config.Name("Render Options")
	@Config.Comment("Configure the color and opacity")
	public static RenderOptions renderOptions;

	public static final class RenderOptions {
		@Config.Name("X Axis")
		@Config.Comment("Render options for the X axis")
		public static Axis xAxis = new Axis(new Color(255, 0, 0, 255), 10.0);
		@Config.Name("Y Axis")
		@Config.Comment("Render options for the Y axis")
		public static Axis yAxis = new Axis(new Color(0, 255, 0, 255), 10.0);
		@Config.Name("Z Axis")
		@Config.Comment("Render options for the Z axis")
		public static Axis zAxis = new Axis(new Color(0, 0, 255, 255), 10.0);
		@Config.RangeInt(min=0, max=255)
		public static int opacity = 255;
		@Config.Comment("Scale the entire model")
		public static double scale = 1.0;
		@Config.Comment("Thickness of the axes")
		public static double thickness = 2.0;
		@Config.Name("thickness outline")
		@Config.Comment("Thickness of the axes' outlines")
		public static double thicknessOutline = 4.0;

		public static final class Axis {
			@Config.Comment("Color of the axis")
			public Color color;
			@Config.Comment("Outline color of the axis")
			public Color outline;
			@Config.Comment("Length of the axis")
			public double length;

			public Axis(Color color, double length) {
				this.color = color;
				this.outline = new Color(0, 0, 0, 255);
				this.length = length;
			}
		}

		public static final class Color {
			@Config.RangeInt(min=0, max=255)
			@Config.Comment("Red channel of the color")
			public int red;
			@Config.RangeInt(min=0, max=255)
			@Config.Comment("Green channel of the color")
			public int green;
			@Config.RangeInt(min=0, max=255)
			@Config.Comment("Blue channel of the color")
			public int blue;
			@Config.RangeInt(min=0, max=255)
			@Config.Comment("Alpha channel of the color")
			public int alpha;

			public Color(int red, int green, int blue, int alpha) {
				this.red = red;
				this.green = green;
				this.blue = blue;
				this.alpha = alpha;
			}
		}
	}

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
