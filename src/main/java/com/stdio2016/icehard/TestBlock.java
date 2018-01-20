package com.stdio2016.icehard;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by stdio2016 on 2017/6/19.
 */

public class TestBlock {
    public static TestBlock_ block;
    public static Item item;
    static {
        block = new TestBlock_();
        block.setUnlocalizedName("icehard").
                setRegistryName("icehard").
                setCreativeTab(CreativeTabs.TOOLS).
                setHardness(5f).
                setHarvestLevel("pickaxe",0);
        item = new ItemBlock(block);
        item.setUnlocalizedName("icehard");
        item.setRegistryName("icehard");
        GameRegistry.addSmelting(Items.STONE_SWORD, new ItemStack(Items.STONE_SWORD), 0.0f);
    }

    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.register(block);
        GameRegistry.register(item);
    }

    public void init() {

    }

    static public class TestBlock_ extends Block {
        public TestBlock_() {
            super(Material.GLASS);
            setSoundType(SoundType.GLASS);
        }
    }
}
