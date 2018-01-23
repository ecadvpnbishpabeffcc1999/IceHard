package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by User on 2018/1/22.
 */
public class BlockIceHard extends Block {
    public static final String iceHardNames[] = {
            "blue", "green", "yellow", "orange",
            "red", "purple", "light"
    };
    public static final BlockIceHard iceHard[] = new BlockIceHard[iceHardNames.length];
    public static final ItemBlock iceHardItem[] = new ItemBlock[iceHardNames.length];
    public final int level;

    public static void registerBlocks() {
        for (int i = 0; i < iceHard.length; i++) {
            String name = "icehard_" + BlockIceHard.iceHardNames[i];
            iceHard[i] = new BlockIceHard(name, i);
            // set ice hard abilities here!
            iceHardItem[i] = new ItemBlock(iceHard[i]);
            iceHardItem[i].setRegistryName(name).setUnlocalizedName(name);
            if (i > 0) {
                // smelt to make higher level Ice Hard
                GameRegistry.addSmelting(iceHardItem[i-1], new ItemStack(iceHardItem[i]), 0.1f);
            }
        }
    }

    public BlockIceHard(String name, int level) {
        super(Material.ROCK);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setHardness(5f);
        this.setHarvestLevel("pickaxe",0);
        this.setResistance(10.0f);
        this.level = level;
    }
}
