package com.stdio2016.icehard;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = IceHardMod.MODID)
public class Settings {
    @Config.Comment("Does Ice Hard freeze nearby water?")
    public static boolean IceHardFreezesWater = true;

    @Config.Comment("Can you craft a block cleaner?")
    public static boolean CleanerCraftable = false;

    @Config.Comment("Do block cleaners work?")
    public static boolean CleanerEnabled = true;


    @Mod.EventBusSubscriber(modid = IceHardMod.MODID)
    private static class Handler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(IceHardMod.MODID))
            {
                ConfigManager.sync(IceHardMod.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
