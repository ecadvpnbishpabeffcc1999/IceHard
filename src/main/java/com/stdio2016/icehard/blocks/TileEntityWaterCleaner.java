package com.stdio2016.icehard.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

/**
 * Created by User on 2019/1/19.
 */
public class TileEntityWaterCleaner extends TileEntityCleaner {
    public TileEntityWaterCleaner() {
        super();
        range = 50;
        power = range * range / 3;
    }

    protected void findClearBlocks() {
        blocksToRemove = new ArrayList<>();
        blocksToRemove.add(Blocks.WATER.getDefaultState());
        blocksToRemove.add(Blocks.ICE.getDefaultState());
        blocksToRemove.add(Blocks.LAVA.getDefaultState());
        blocksToRemove.add(Blocks.COBBLESTONE.getDefaultState());
    }

    protected TileEntityCleaner createSelf() {
        return new TileEntityWaterCleaner();
    }

    private boolean isLava(BlockPos pos) {
        IBlockState blk = getEquivalentBlock(world.getBlockState(pos));
        return blk == Blocks.LAVA.getDefaultState() || blk == Blocks.COBBLESTONE.getDefaultState();
    }

    private boolean isNearbyLava() {
        return isLava(pos.east()) || isLava(pos.west())
            || isLava(pos.north()) || isLava(pos.south())
            || isLava(pos.up()) || isLava(pos.down());
    }

    @Override
    protected void deleteSelf(boolean ended) {
        if (ended) {
            if (isNearbyLava()) {
                world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
            }
            else {
                world.setBlockState(pos, Blocks.ICE.getDefaultState());
            }
        }
        else super.deleteSelf(false);
    }
}
