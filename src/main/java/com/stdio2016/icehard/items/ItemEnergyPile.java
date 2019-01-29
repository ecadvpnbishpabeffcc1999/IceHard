package com.stdio2016.icehard.items;

import com.stdio2016.icehard.blocks.BlockHaicerdCrop;
import com.stdio2016.icehard.blocks.BlockIceHardGrass;
import com.stdio2016.icehard.blocks.BlockIceHardSapling;
import com.stdio2016.icehard.blocks.IBlockIceHard;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by User on 2018/1/23.
 */
public class ItemEnergyPile extends MyItem {
    public static Item item = new ItemEnergyPile();
    public static final String name = "energypile";

    protected ItemEnergyPile() {
        super(name);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return 200; // smelt just 1 item
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer user, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float offsetX, float offsetY, float offsetZ) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof BlockIceHardGrass) {
            if (!world.isRemote) {
                ((BlockIceHardGrass) state.getBlock()).spreadTallGrass(world, pos, world.rand);
                ItemStack stack = user.getHeldItem(hand);
                if (stack != null) stack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }
        if (block instanceof IGrowable && block instanceof IBlockIceHard) {
            IGrowable crop = (IGrowable) block;
            if (crop.canGrow(world, pos, state, world.isRemote)) {
                if (!world.isRemote) {
                    crop.grow(world, world.rand, pos, state);
                    ItemStack stack = user.getHeldItem(hand);
                    if (stack != null) stack.shrink(1);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return super.onItemUse(user, world, pos, hand, facing, offsetX, offsetY, offsetZ);
    }
}
