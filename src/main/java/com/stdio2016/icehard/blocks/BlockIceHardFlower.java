package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by User on 2019/1/28.
 */
public class BlockIceHardFlower extends BlockBush implements IBlockIceHard {
    public ItemBlock item;
    public BlockIceHardFlower(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setSoundType(SoundType.PLANT);
        this.item = new ItemBlock(this);
        this.item.setRegistryName(name).setUnlocalizedName(name);
    }

    public Item itemBlock() {
        return item;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.getBoundingBox(state, world, pos).offset(state.getOffset(world, pos));
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }
}
