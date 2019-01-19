package com.stdio2016.icehard.proxy;

import com.stdio2016.icehard.blocks.BlockCleanerManager;
import com.stdio2016.icehard.blocks.RegisterBlock;
import com.stdio2016.icehard.blocks.TileEntityCleaner;
import com.stdio2016.icehard.items.RegisterItem;
import com.stdio2016.icehard.worldgen.GenIceHard;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by User on 2018/1/20.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent ev) {
        RegisterBlock.preInit(ev);
        MinecraftForge.EVENT_BUS.register(RegisterBlock.class);
        RegisterItem.preInit(ev);
        MinecraftForge.EVENT_BUS.register(RegisterItem.class);
        GameRegistry.registerWorldGenerator(new GenIceHard(), 1);
        GameRegistry.registerTileEntity(TileEntityCleaner.class, "tileEntityCleaner");
    }

    public void init(FMLInitializationEvent ev) {
        RegisterItem.init(ev);
        MinecraftForge.EVENT_BUS.register(new BlockCleanerManager());
        System.out.println();
    }
}
