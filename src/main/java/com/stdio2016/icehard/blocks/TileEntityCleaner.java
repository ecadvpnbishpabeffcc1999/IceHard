package com.stdio2016.icehard.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by User on 2018/8/7.
 */
public class TileEntityCleaner extends TileEntity {
    public Block blockToRemove;
    public int range;

    public TileEntityCleaner() {
        blockToRemove = null;
        range = 10;
    }

    public void clean(World world, BlockPos pos) {
        if (range <= 0) {
            if (world.getBlockState(pos).getBlock() instanceof BlockCleaner) {
                world.setBlockToAir(pos);
            }
            return ;
        }
        if (blockToRemove == null) {
            blockToRemove = world.getBlockState(pos.add(0,-1,0)).getBlock();
        }
        else {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            clear(world, x, y, z - 1);
            clear(world, x, y, z + 1);
            clear(world, x, y - 1, z);
            clear(world, x, y + 1, z);
            clear(world, x - 1, y, z);
            clear(world, x + 1, y, z);
            if (world.getBlockState(pos).getBlock() instanceof BlockCleaner) {
                world.setBlockToAir(pos);
            }
        }
    }

    private void clear(World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (world.getBlockState(pos).getBlock() == blockToRemove) {
            world.setBlockState(pos, RegisterBlock.some.getDefaultState());
            TileEntityCleaner te = new TileEntityCleaner();
            te.blockToRemove = blockToRemove;
            te.range = range - 1;
            world.setTileEntity(pos, te);
            world.scheduleUpdate(pos, RegisterBlock.some,10);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound p_deserializeNBT_1_) {
        super.readFromNBT(p_deserializeNBT_1_);
        range = getTileData().getInteger("range");
        String blockId = getTileData().getString("block");
        blockToRemove = Block.getBlockFromName(blockId);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (blockToRemove != null) {
            String blockId = blockToRemove.getRegistryName().toString();
            getTileData().setString("block", blockId);
        }
        getTileData().setInteger("range", range);
        return super.writeToNBT(nbt);
    }
}
