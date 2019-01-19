package com.stdio2016.icehard.blocks;

import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by User on 2018/8/7.
 */
public class TileEntityCleaner extends TileEntity implements ITickable {
    public ArrayList<IBlockState> blocksToRemove;
    public int range;
    public int delay;
    public int power;
    public BlockPos center;
    public int id;

    public TileEntityCleaner() {
        blocksToRemove = null;
        range = 20;
        delay = 0;
        power = range * range / 3;
        center = new BlockPos(0,0,0);
        id = 0;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        clean(world, pos);
    }

    public void clean(World world, BlockPos pos) {
        if (blocksToRemove == null || blocksToRemove.size() == 0) {
            if (world.isBlockIndirectlyGettingPowered(pos) <= 0) return;
            center = pos;
            id = new Random().nextInt();
            findClearBlocks();
        }
        else {
            delay--;
            if (delay > 0) return;
            double d = pos.distanceSq(center);
            if (d > power || range <= 0) {
                deleteSelf(true);
                return;
            }
            clear(world, pos.north());
            clear(world, pos.south());
            clear(world, pos.east());
            clear(world, pos.west());
            clear(world, pos.up());
            clear(world, pos.down());
            deleteSelf(false);
        }
    }

    protected void findClearBlocks() {
        blocksToRemove = new ArrayList<>();
        BlockPos p = pos.up();
        while (true) {
            IBlockState blk = getEquivalentBlock(world.getBlockState(p));
            if (blk == null) break;
            blocksToRemove.add(blk);
            p = p.up();
        }
    }

    protected void clear(World world, BlockPos pos) {
        IBlockState b = getEquivalentBlock(world.getBlockState(pos));
        if (BlockCleanerManager.isStopped(id)) {
            range = -1;
            BlockCleanerManager.addMoreStoppedCleaner(id);
            return ;
        }
        if (b == RegisterBlock.stopper.getDefaultState()) {
            range = -1;
            BlockCleanerManager.addStoppedCleaner(id);
            return ;
        }
        if (blocksToRemove.contains(b)) {
            world.setBlockState(pos, RegisterBlock.cleaner.getDefaultState(), 2);
            TileEntityCleaner te = createSelf();
            te.blocksToRemove = blocksToRemove;
            te.id = id;
            te.range = range - 1;
            te.power = power;
            te.center = center;
            te.delay = getDelayForBlock(b);
            world.setTileEntity(pos, te);
        }
    }

    public int getDelayForBlock(IBlockState blk) {
        return 25;
    }

    protected TileEntityCleaner createSelf() {
        return new TileEntityCleaner();
    }

    protected IBlockState getEquivalentBlock(IBlockState a) {
        Block b = a.getBlock();
        if (b == Blocks.LAVA || b == Blocks.FLOWING_LAVA) {
            return Blocks.LAVA.getDefaultState();
        }
        if (b == Blocks.WATER || b == Blocks.FLOWING_WATER) {
            return Blocks.WATER.getDefaultState();
        }
        if (b == Blocks.GRASS_PATH || b == Blocks.GRASS || b == Blocks.MYCELIUM) {
            return Blocks.DIRT.getDefaultState();
        }
        if (b == Blocks.AIR) {
            return null;
        }
        Collection<IProperty<?>> col = a.getPropertyKeys();
        int meta = b.damageDropped(a);
        return b.getStateFromMeta(meta);
    }

    protected void deleteSelf(boolean ended) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
    }

    @Override
    public void readFromNBT(NBTTagCompound p_deserializeNBT_1_) {
        super.readFromNBT(p_deserializeNBT_1_);
        range = getTileData().getInteger("range");
        power = getTileData().getInteger("power");
        center = new BlockPos(
            getTileData().getInteger("centerX"),
            getTileData().getInteger("centerY"),
            getTileData().getInteger("centerZ")
        );
        id = getTileData().getInteger("id");
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
        getTileData().setInteger("power", power);
        getTileData().setInteger("centerX", center.getX());
        getTileData().setInteger("centerY", center.getY());
        getTileData().setInteger("centerZ", center.getZ());
        getTileData().setInteger("id", id);
        return super.writeToNBT(nbt);
    }
}
