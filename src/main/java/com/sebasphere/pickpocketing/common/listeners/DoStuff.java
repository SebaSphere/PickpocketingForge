package com.sebasphere.pickpocketing.common.listeners;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.vecmath.Point3d;

public class DoStuff {

    //uh do shid to check if players are looking in same yaw and near eachother


    public static double distanceTo(Point3d p1, Point3d p2) {
        return Math.sqrt( Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));
    }


    @SubscribeEvent
    public void doStuff(TickEvent.PlayerTickEvent event) {
        if (!(event.player.world.isRemote)) return; //don't remember why I have this
        EntityPlayer p1 = CheckPlayerLocation.getMainPlayer();
        EntityPlayer p2 = CheckPlayerLocation.getOtherPlayer();


        try {
            Point3d pl1 = new Point3d(p1.posX, p1.posY, p1.posZ);
            Point3d pl2 = new Point3d(p2.posX, p2.posY, p2.posZ);

            System.out.println(distanceTo(pl1, pl2));
        } catch (NullPointerException e) {
            //you know this is a really cancer solution
            //uh based on my minimal testing, doesn't log anything in client2 on a lan game
            //I think it prints the distance on logout normally
        }

    }

}
