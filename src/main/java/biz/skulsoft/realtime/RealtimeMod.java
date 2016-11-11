package biz.skulsoft.realtime;

import biz.skulsoft.realtime.command.CommandShowTime;
import biz.skulsoft.realtime.config.ConfigHandler;
import biz.skulsoft.realtime.handler.BedHandler;
import biz.skulsoft.realtime.handler.TickHandler;
import biz.skulsoft.realtime.info.ModInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION, acceptableRemoteVersions = "*")
public class RealtimeMod {

    @Mod.Instance(value="RealTimeMod")
    public static RealtimeMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TickHandler());
        MinecraftForge.EVENT_BUS.register(new BedHandler());
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandShowTime());
    }
}
