package ninja.cooperstuff.debugcrosshair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@Mod(modid = DebugCrosshair.MODID, name = DebugCrosshair.NAME, version = DebugCrosshair.VERSION)
public class DebugCrosshair {
    public static final String MODID = "debugcrosshair";
    public static final String NAME = "Debug Crosshair";
    public static final String VERSION = "1.1";

    protected static Logger logger;
    protected static Minecraft mc;
    protected static GameSettings gameSettings;
    protected static KeyBinding toggleKey;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    @SideOnly(value = Side.CLIENT)
    public void initClient(FMLInitializationEvent event) {
        mc = Minecraft.getMinecraft();
        gameSettings = mc.gameSettings;
        toggleKey = new KeyBinding("debugcrosshair:keybind.toggle", Keyboard.KEY_BACK, "debugcrosshair:category.debugcrosshair");
        ClientRegistry.registerKeyBinding(toggleKey);
    }

    @EventHandler
    @SideOnly(value = Side.SERVER)
    public void initServer(FMLInitializationEvent event) {
        logger.warn("DebugCrosshair found in server! DebugCrosshair is a clientside mod and will do nothing on a server.");
    }

    @EventHandler
    @SideOnly(value = Side.CLIENT)
    public void postInit(FMLPostInitializationEvent event) {
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new DCEventHandler());
    }

    public static void renderAxes() {
        double scale = DCConfig.RenderOptions.scale;
        double opacity = (double) DCConfig.RenderOptions.opacity / 255;
        double thickness = DCConfig.RenderOptions.thickness;
        double thicknessOutline = DCConfig.RenderOptions.thicknessOutline;
        DCConfig.RenderOptions.Axis xAxis = DCConfig.RenderOptions.xAxis;
        DCConfig.RenderOptions.Axis yAxis = DCConfig.RenderOptions.yAxis;
        DCConfig.RenderOptions.Axis zAxis = DCConfig.RenderOptions.zAxis;

        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GL11.glLineWidth((float) thicknessOutline);
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(xAxis.outline.red, xAxis.outline.green, xAxis.outline.blue, (int) (opacity * xAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(scale * xAxis.length, 0.0D, 0.0D).color(xAxis.outline.red, xAxis.outline.green, xAxis.outline.blue, (int) (opacity * xAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(yAxis.outline.red, yAxis.outline.green, yAxis.outline.blue, (int) (opacity * yAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, scale * yAxis.length, 0.0D).color(yAxis.outline.red, yAxis.outline.green, yAxis.outline.blue, (int) (opacity * yAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(zAxis.outline.red, zAxis.outline.green, zAxis.outline.blue, (int) (opacity * zAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, scale * zAxis.length).color(zAxis.outline.red, zAxis.outline.green, zAxis.outline.blue, (int) (opacity * zAxis.outline.alpha)).endVertex();
        tessellator.draw();
        GL11.glLineWidth((float) thickness);
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(xAxis.color.red, xAxis.color.green, xAxis.color.blue, (int) (opacity * xAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(scale * xAxis.length, 0.0D, 0.0D).color(xAxis.color.red, xAxis.color.green, xAxis.color.blue, (int) (opacity * xAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(yAxis.color.red, yAxis.color.green, yAxis.color.blue, (int) (opacity * yAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, scale * yAxis.length, 0.0D).color(yAxis.color.red, yAxis.color.green, yAxis.color.blue, (int) (opacity * yAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(zAxis.color.red, zAxis.color.green, zAxis.color.blue, (int) (opacity * zAxis.outline.alpha)).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, scale * zAxis.length).color(zAxis.color.red, zAxis.color.green, zAxis.color.blue, (int) (opacity * zAxis.outline.alpha)).endVertex();
        tessellator.draw();
        GL11.glLineWidth(1.0F);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
    }
}
