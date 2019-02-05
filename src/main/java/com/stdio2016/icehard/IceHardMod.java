package com.stdio2016.icehard;

import com.stdio2016.icehard.blocks.BlockIceHard;
import com.stdio2016.icehard.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//
// Use this superflat preset:
// 3;minecraft:bedrock,59*minecraft:stone,3*icehard:icehard_sand,icehard:icehard_grass_block;40;village,biome_1,decoration,stronghold,mineshaft,lake,lava_lake,dungeon

@Mod(modid = IceHardMod.MODID, version = IceHardMod.VERSION)
public class IceHardMod
{
    public static final String MODID = "icehard";
    public static final String VERSION = "2.2.0";
    public static final String ClientOnlyClass = "com.stdio2016.icehard.proxy.ClientOnly";
    public static final String ServerOnlyClass = "com.stdio2016.icehard.proxy.ServerOnly";

    public static final CreativeTabs ourTab = new CreativeTabs(MODID) {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            int highestLv = BlockIceHard.iceHard.length-1;
            return new ItemStack(BlockIceHard.iceHard[highestLv]);
        }
    };
    @SidedProxy(clientSide = ClientOnlyClass, serverSide = ServerOnlyClass)
    public static CommonProxy proxy;
    @Mod.Instance
    public static IceHardMod me;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("preinit IceHard");
        proxy.preInit(event);
        System.out.println("preinit IceHard successful!");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("init IceHard");
        proxy.init(event);
        System.out.println("init IceHard successful!");
    }
}
