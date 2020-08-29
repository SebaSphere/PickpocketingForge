package com.sebasphere.pickpocketing.common.listeners;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CheckPlayerLocation {

    private static String playerName;
    private static float rotationPitch;
    private static float rotationYaw;
    private static EntityPlayer otherPlayer;
    private static EntityPlayer mainPlayer;

    //time to figure this out


    AxisAlignedBB boundBox = new AxisAlignedBB(-5,-5,-5,5,5,5);

    @SubscribeEvent
    public void playerLocationCheckForNear(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerSP) return;
        if (event.player instanceof EntityPlayerMP) return;

        EntityPlayer player = event.player.world.getClosestPlayer(event.player.posX, event.player.posY, event.player.posZ, 6, false);
        otherPlayer = player;
        

    }
    @SubscribeEvent
    public void playerLocationForMe(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerSP) return;
        if (event.player instanceof EntityOtherPlayerMP) return;
        mainPlayer = event.player;


    }



    @SubscribeEvent
    public void PlayerRotations(TickEvent.PlayerTickEvent event) {

        rotationYaw = MathHelper.wrapDegrees(event.player.rotationYaw);
        rotationPitch = event.player.rotationPitch;
        playerName = event.player.getName();

    }

    @SideOnly(Side.SERVER)
    public static float GetPlayerPitch() {
       return rotationPitch;
    }
    @SideOnly(Side.SERVER)
    public static float GetPlayerYaw() {
        return rotationYaw;
    }
    @SideOnly(Side.SERVER)
    public static String GetPlayerName() {
        return playerName;
    }

    public static EntityPlayer getOtherPlayer() {
        return otherPlayer;
    }

    public static EntityPlayer getMainPlayer() {
        return mainPlayer;
    }
}





