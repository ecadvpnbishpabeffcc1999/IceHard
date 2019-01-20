package com.stdio2016.icehard;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = IceHardMod.MODID)
public class Settings {
    @Config.LangKey("config.icehard.ice_hard_freezes_water.name")
    public static boolean IceHardFreezesWater = true;

    @Config.LangKey("config.icehard.cleaner_craftable.name")
    @Config.RequiresMcRestart
    public static boolean CleanerCraftable = false;

    @Config.LangKey("config.icehard.cleaner_enabled.name")
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
