package com.stdio2016.icehard.items;

import com.stdio2016.icehard.blocks.BlockIceHardHardLog;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2019/1/29.
 */
public class ItemCopperTap extends MyItem {
    public ItemCopperTap(String name, int maxUse) {
        super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(maxUse);

    }

    public void throwBrokenTool(ItemStack itemStack, World world, EntityLivingBase entity) {
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            BlockPos p = player.getPosition();
            int d = player.dimension;
            ItemStack itm = new ItemStack(RegisterItem.brokenTool[0]);
            EntityItem e = new EntityItem(world, p.getX(), p.getY(), p.getZ(), itm);
            e.dimension = d;
            world.spawnEntity(e);
        }
    }

    public void throwItemAt(ItemStack itemStack, World world, BlockPos pos, int dim) {
        ItemStack itm = itemStack.copy();
        EntityItem e = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itm);
        e.dimension = dim;
        e.setPickupDelay(10);
        world.spawnEntity(e);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer user, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float offsetX, float offsetY, float offsetZ) {
        ItemStack item = user.getHeldItem(hand);
        int dmg = item.getItemDamage();
        EnumActionResult r = EnumActionResult.PASS;
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block == RegisterBlock.IceHardHardLog && state.getValue(BlockIceHardHardLog.HAS_TAP) == 1) {
            r = EnumActionResult.SUCCESS;
            if (!world.isRemote) {
                world.setBlockState(pos, state.withProperty(BlockIceHardHardLog.HAS_TAP, 0));
                this.throwItemAt(new ItemStack(ItemEnergyPile.item), world, pos.offset(facing), user.dimension);
                item.damageItem(1, user);
                int dmgNow = item.getItemDamage();
                if (dmgNow == 0 && dmgNow < dmg) {
                    this.throwBrokenTool(item, world, user);
                }
            }
        }
        return r;
    }
}
