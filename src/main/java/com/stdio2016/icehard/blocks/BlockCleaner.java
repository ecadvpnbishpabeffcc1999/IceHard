package com.stdio2016.icehard.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by User on 2018/8/7.
 */
public class BlockCleaner extends MyBlock implements ITileEntityProvider {
    public BlockCleaner(String name, Material mat, MapColor color) {
        super(name, mat, color);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state,
                                EntityLivingBase placer, ItemStack stack)
    {
        final TileEntity te = world.getTileEntity(pos);
        if(te != null && te instanceof TileEntityCleaner) {
            final TileEntityCleaner cleaner = (TileEntityCleaner) te;
            cleaner.setPos(pos);
        }
    }

    /*@Override
    public void neighborChanged(IBlockState b, World world, BlockPos pos, Block b2, BlockPos pos2) {
        if (!world.isRemote) {
            if (world.isBlockPowered(pos)) {
                world.scheduleUpdate(pos, this, 10);
            }
        }
    }*/

    /*@Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            final TileEntity te = world.getTileEntity(pos);
            if (te != null && te instanceof TileEntityCleaner) {
                final TileEntityCleaner cleaner = (TileEntityCleaner) te;
                //cleaner.clean(world, pos);
            }
        }
    }*/

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCleaner();
    }
}
