package biz.skulsoft.realtime.info;

public class ModInfo {

    public static final  String MODID   = "realtimemod";
    public static final  String NAME    = "RealTime Mod";
    private static final String MAJOR   = "@MAJOR@";
    private static final String MINOR   = "@MINOR@";
    public static final  String VERSION = "@MC_VERSION@-" + MAJOR + "." + MINOR;

    public static boolean MOD_ENABLED = true;
    public static boolean USE_SERVERTIME = true;
    public static int TIME_OFFSET = 0;
}

