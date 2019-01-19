package com.stdio2016.icehard.blocks;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

/**
 * Created by User on 2019/1/18.
 */
public class BlockCleanerManager {
    public static class StoppedCleanerId {
        int count;
        int tick;

        public StoppedCleanerId(int n) {
            count = 1;
            tick = n;
        }
    }

    private static Map<Integer, StoppedCleanerId> stoppedId = new HashMap<>();

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (Iterator<Map.Entry<Integer, StoppedCleanerId>> it = stoppedId.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Integer, StoppedCleanerId> entry = it.next();
                StoppedCleanerId clr = entry.getValue();
                clr.tick--;
                if (clr.tick <= 0) {
                    it.remove();
                    System.out.println("Removed " + clr.count + " clearers from block " + entry.getKey());
                }
            }
        }
    }

    public static void addStoppedCleaner(int id) {
        StoppedCleanerId clr = new StoppedCleanerId(50);
        stoppedId.put(id, clr);
    }

    public static void addMoreStoppedCleaner(int id) {
        StoppedCleanerId clr = stoppedId.get(id);
        if (clr != null) {
            clr.count++;
        }
    }

    public static boolean isStopped(int id) {
        return stoppedId.containsKey(id);
    }
}
