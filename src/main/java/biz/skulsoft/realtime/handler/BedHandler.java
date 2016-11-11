package biz.skulsoft.realtime.handler;

import biz.skulsoft.realtime.info.ModInfo;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BedHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void bedClicked(PlayerInteractEvent.RightClickBlock event) {
        if (ModInfo.MOD_ENABLED && ModInfo.USE_SERVERTIME){
            if (!event.getWorld().isRemote){
                BlockPos pos = event.getPos();
                IBlockState state = event.getWorld().getBlockState(pos);
                if (state.getBlock() instanceof BlockBed){
                    if (state.getValue(BlockBed.PART) != BlockBed.EnumPartType.HEAD){
                        pos = event.getPos().offset(state.getValue(BlockBed.FACING));
                        state = event.getWorld().getBlockState(pos);
                        if (!(state.getBlock() instanceof BlockBed) || state.getValue(BlockBed.PART) != BlockBed.EnumPartType.HEAD){
                            return;
                        }
                    }
                    if (event.getWorld().provider.canRespawnHere() && event.getWorld().provider.getBiomeForCoords(pos) != Biomes.HELL){
                        event.getEntityPlayer().setSpawnPoint(event.getEntityPlayer().getPosition(), false);
                        event.getEntityPlayer().setSpawnChunk(pos, false, event.getWorld().provider.getDimension());
                        event.getEntityPlayer().addChatComponentMessage(new TextComponentString("You cannot sleep. RealTime Mod is enabled."));
                        event.getEntityPlayer().addChatComponentMessage(new TextComponentString("New spawn point set"));
                        event.setCanceled(true);
                    }
                }
            }
        }
    }




}

