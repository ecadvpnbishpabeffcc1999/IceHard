package com.stdio2016.icehard.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

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

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (willHarvest) return true;
        return super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState block, TileEntity te, ItemStack tool) {
        super.harvestBlock(world, player, pos, block, te, tool);
        world.setBlockToAir(pos);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> list, IBlockAccess  world, BlockPos pos, IBlockState blockstate, int fortune){
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileEntityCleaner) {
            final TileEntityCleaner cleaner = (TileEntityCleaner) te;
            if (cleaner.blocksToRemove != null && cleaner.blocksToRemove.size() > 0) {
                // is working, don't drop anything
                return ;
            }
        }
        super.getDrops(list, world, pos, blockstate, fortune);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCleaner();
    }
}
