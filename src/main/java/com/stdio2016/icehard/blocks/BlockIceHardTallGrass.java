package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by User on 2019/1/27.
 */
public class BlockIceHardTallGrass extends BlockBush implements IShearable {
    public ItemBlock item;
    public static AxisAlignedBB Aabb = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);
    public BlockIceHardTallGrass(String name) {
        super(Material.VINE);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setSoundType(SoundType.PLANT);
        this.item = new ItemBlock(this);
        this.item.setRegistryName(name).setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return Aabb;
    }

    @Override
    public Item getItemDropped(IBlockState block, Random rnd, int fortune) {
        return null;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState block, TileEntity te, ItemStack tool) {
        if(!world.isRemote && tool.getItem() == Items.SHEARS) {
            // use shears to get grass
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(world, pos, new ItemStack(this.item, 1));
        } else {
            super.harvestBlock(world, player, pos, block, te, tool);
        }

    }

    @Override
    public void getDrops(NonNullList<ItemStack> list, IBlockAccess  world, BlockPos pos, IBlockState blockstate, int fortune) {
        if (RANDOM.nextInt(8) == 1) {
            list.add(new ItemStack(this.item) /* TODO: seed */);
        }
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XYZ;
    }

    public boolean isShearable(ItemStack stack, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public NonNullList<ItemStack> onSheared(ItemStack stack, IBlockAccess world, BlockPos pos, int i) {
        return NonNullList.withSize(1, new ItemStack(this.item, 1));
    }
}
