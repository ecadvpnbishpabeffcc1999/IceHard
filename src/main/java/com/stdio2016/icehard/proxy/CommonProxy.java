package com.stdio2016.icehard.proxy;

import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by User on 2018/1/20.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent ev) {
        RegisterBlock.preInit(ev);
        MinecraftForge.EVENT_BUS.register(RegisterBlock.class);
    }

    public void init(FMLInitializationEvent ev) {

    }
}
