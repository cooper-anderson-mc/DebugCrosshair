package ninja.cooperstuff.debugcrosshair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DCEventHandler {
	@SubscribeEvent
	public void onScreenUpdate(RenderGameOverlayEvent.Pre event) {
		if (DCConfig.enabled && !DebugCrosshair.gameSettings.showDebugInfo && DebugCrosshair.gameSettings.thirdPersonView == 0 && event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
			Minecraft mc = Minecraft.getMinecraft();
			ScaledResolution scaledresolution = new ScaledResolution(mc);
			int l = scaledresolution.getScaledWidth();
			int i1 = scaledresolution.getScaledHeight();
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) (l / 2), (float) (i1 / 2), -90);
			Entity entity = mc.getRenderViewEntity();
			GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * mc.getRenderPartialTicks(), -1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * mc.getRenderPartialTicks(), 0.0F, 1.0F, 0.0F);
			GlStateManager.scale(-1.0F, -1.0F, -1.0F);
			DebugCrosshair.renderAxes();
			GlStateManager.popMatrix();
			event.setCanceled(true);
		}
	}
}
