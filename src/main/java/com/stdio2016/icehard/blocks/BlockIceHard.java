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
    public static final int MaxLevel = iceHardNames.length;
    public static final BlockIceHard iceHard[] = new BlockIceHard[MaxLevel];
    public static final ItemBlock iceHardItem[] = new ItemBlock[MaxLevel];
    public static BlockIceHard packedIceHard;
    public static ItemBlock packedIceHardItem;
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
        String name = "packed_icehard_blue";
        packedIceHard = new BlockIceHard(name, 0);
        packedIceHardItem = new ItemBlock(packedIceHard);
        packedIceHardItem.setRegistryName(name).setUnlocalizedName(name);
    }

    public BlockIceHard(String name, int level) {
        super(Material.ROCK);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setHardness(3f);
        this.setHarvestLevel("pickaxe",1);
        this.setResistance(10.0f);
        this.level = level;
    }
}
