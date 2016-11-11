package biz.skulsoft.realtime.config;

import biz.skulsoft.realtime.info.ModInfo;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
    public static void init(File file) {
        Configuration config = new Configuration(file);
        config.load();
        ModInfo.MOD_ENABLED = config.get("modconfig", "mod_enabled", true).getBoolean(true);
        ModInfo.USE_SERVERTIME = config.get("timeconfig", "use_server_time", true).getBoolean(false);
        ModInfo.TIME_OFFSET = config.get("timeconfig", "time_offset", 0).getInt();
        config.save();
    }
}

