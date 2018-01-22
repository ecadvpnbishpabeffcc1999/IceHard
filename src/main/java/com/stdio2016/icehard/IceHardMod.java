package com.stdio2016.icehard;

import com.stdio2016.icehard.blocks.TestBlock;
import com.stdio2016.icehard.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = IceHardMod.MODID, version = IceHardMod.VERSION)
public class IceHardMod
{
    public static final String MODID = "icehard";
    public static final String VERSION = "0.1";
    public static final String ClientOnlyClass = "com.stdio2016.icehard.proxy.ClientOnly";
    public static final String ServerOnlyClass = "com.stdio2016.icehard.proxy.ServerOnly";

    public final TestBlock testBlockInst = new TestBlock();
    @SidedProxy(clientSide = ClientOnlyClass, serverSide = ServerOnlyClass)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        testBlockInst.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("IceHard is really hard!");
        proxy.init(event);
    }
}
