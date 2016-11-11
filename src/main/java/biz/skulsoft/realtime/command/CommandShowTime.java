package biz.skulsoft.realtime.command;

import biz.skulsoft.realtime.info.ModInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommandShowTime extends CommandBase{

    @Override
    public String getCommandName(){
        return "rtshowtime";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/rtshowtime";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] astring) throws NumberInvalidException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        String hour = dateFormat.format(date);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("mm");
        Date date1 = new Date();
        String minute = dateFormat1.format(date1);
        int offset = ModInfo.TIME_OFFSET;
        float offset_sec = offset * 60 * 60;
        int hour_int = Integer.parseInt(hour);
        int minute_int = Integer.parseInt(minute);
        float total_sec = hour_int * 60 * 60 + minute_int * 60;
        TimeZone tz = TimeZone.getTimeZone("UTC");
        Date date2 = new Date((long)(total_sec + offset_sec) * 1000);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(tz);
        String time = df.format(date2);
        sender.addChatMessage(new TextComponentString("Current time is: " + time));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

}

