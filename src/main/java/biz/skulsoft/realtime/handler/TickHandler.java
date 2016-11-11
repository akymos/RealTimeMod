package biz.skulsoft.realtime.handler;

import biz.skulsoft.realtime.info.ModInfo;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TickHandler {
    private int tickCount = 0;
    private int syncTick = 1200;
    private World worldObj;

    @SubscribeEvent
    public void tick(TickEvent.WorldTickEvent event) {
        this.worldObj = event.world;
        if(ModInfo.MOD_ENABLED && ModInfo.USE_SERVERTIME && this.tickCount == 0){
            this.syncTime();
        }
        if(!ModInfo.MOD_ENABLED || !ModInfo.USE_SERVERTIME){
            this.worldObj.getWorldInfo().getGameRulesInstance().addGameRule("doDaylightCycle", "true", GameRules.ValueType.BOOLEAN_VALUE);
        }
        if (ModInfo.MOD_ENABLED) {
            ++this.tickCount;
            if (ModInfo.USE_SERVERTIME && this.tickCount % this.syncTick == 0) {
                this.syncTime();
            }
        }
    }

    private void syncTime() {
        this.worldObj.getWorldInfo().getGameRulesInstance().addGameRule("doDaylightCycle", "false", GameRules.ValueType.BOOLEAN_VALUE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        String hour = dateFormat.format(date);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("mm");
        Date date1 = new Date();
        String minute = dateFormat1.format(date1);
        int offset = ModInfo.TIME_OFFSET;
        float offset_sec = offset * 60 * 60;
        float offset_goal = offset_sec / 86400.0f * 24000.0f;
        int hour_int = Integer.parseInt(hour);
        int minute_int = Integer.parseInt(minute);
        float total_sec = hour_int * 60 * 60 + minute_int * 60;
        float goal_time = total_sec / 86400.0f * 24000.0f;
        long value = Math.round(goal_time) + 18000 + Math.round(offset_goal);
        this.worldObj.setWorldTime(value);
    }
}

