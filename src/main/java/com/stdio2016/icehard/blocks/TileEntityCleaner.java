package com.stdio2016.icehard.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

/**
 * Created by User on 2018/8/7.
 */
public class TileEntityCleaner extends TileEntity implements ITickable {
    public ArrayList<IBlockState> blocksToRemove;
    public int range;
    public int delay;

    public TileEntityCleaner() {
        blocksToRemove = null;
        range = 20;
        delay = 0;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        clean(world, pos);
    }

    public void clean(World world, BlockPos pos) {
        if (range <= 0) {
            if (world.getBlockState(pos).getBlock() instanceof BlockCleaner) {
                world.setBlockToAir(pos);
            }
            return ;
        }
        if (blocksToRemove == null || blocksToRemove.size() == 0) {
            if (world.isBlockIndirectlyGettingPowered(pos) <= 0) return;
            blocksToRemove = new ArrayList<>();
            BlockPos p = pos.add(0, 1, 0);
            while (p.getY() <= 255) {
                IBlockState blk = getEquivalentBlock(world.getBlockState(p));
                if (blk == null) break;
                blocksToRemove.add(blk);
                p = p.add(0, 1, 0);
            }
        }
        else {
            delay++;
            if (delay < 40) return;
            delay = 0;
            clear(world, pos.north());
            clear(world, pos.south());
            clear(world, pos.east());
            clear(world, pos.west());
            clear(world, pos.up());
            clear(world, pos.down());
            if (world.getBlockState(pos).getBlock() instanceof BlockCleaner) {
                world.setBlockToAir(pos);
            }
        }
    }

    private void clear(World world, BlockPos pos) {
        IBlockState b = getEquivalentBlock(world.getBlockState(pos));
        if (blocksToRemove.contains(b)) {
            world.setBlockState(pos, RegisterBlock.some.getDefaultState());
            TileEntityCleaner te = new TileEntityCleaner();
            te.blocksToRemove = blocksToRemove;
            te.range = range - 1;
            world.setTileEntity(pos, te);
            world.scheduleUpdate(pos, RegisterBlock.some,10);
        }
    }

    private IBlockState getEquivalentBlock(IBlockState a) {
        if (a == Blocks.FLOWING_LAVA.getDefaultState()) {
            return Blocks.LAVA.getDefaultState();
        }
        if (a == Blocks.FLOWING_WATER.getDefaultState()) {
            return Blocks.WATER.getDefaultState();
        }
        if (a == Blocks.GRASS_PATH.getDefaultState() || a == Blocks.GRASS.getDefaultState() || a == Blocks.MYCELIUM.getDefaultState()) {
            return Blocks.DIRT.getDefaultState();
        }
        if (a == Blocks.AIR.getDefaultState()) {
            return null;
        }
        return a;
    }

    @Override
    public void readFromNBT(NBTTagCompound p_deserializeNBT_1_) {
        super.readFromNBT(p_deserializeNBT_1_);
        range = getTileData().getInteger("range");
        NBTTagList list = getTileData().getTagList("blocks", Constants.NBT.TAG_COMPOUND);
        blocksToRemove = new ArrayList<>();
        if (list.tagCount() > 0) {
            for (NBTBase comp : list) {
                NBTTagCompound block = (NBTTagCompound) comp;
                String name = block.getString("name");
                int meta = block.getInteger("meta");
                blocksToRemove.add(Block.getBlockFromName(name).getStateFromMeta(meta));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (blocksToRemove != null) {
            NBTTagList list = new NBTTagList();
            for (IBlockState block: blocksToRemove) {
                NBTTagCompound comp = new NBTTagCompound();
                comp.setString("name", block.getBlock().getRegistryName().toString());
                comp.setInteger("meta", block.getBlock().getMetaFromState(block));
                list.appendTag(comp);
            }
            getTileData().setTag("blocks", list);
        }
        getTileData().setInteger("range", range);
        return super.writeToNBT(nbt);
    }
}
