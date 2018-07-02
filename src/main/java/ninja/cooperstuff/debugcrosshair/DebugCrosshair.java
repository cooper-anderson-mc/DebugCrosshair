package ninja.cooperstuff.debugcrosshair;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = DebugCrosshair.MODID, name = DebugCrosshair.NAME, version = DebugCrosshair.VERSION)
public class DebugCrosshair
{
    public static final String MODID = "debugcrosshair";
    public static final String NAME = "Debug Crosshair";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
