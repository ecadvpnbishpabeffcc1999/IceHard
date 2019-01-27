package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.BlockIceHard;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/1/23.
 */
public class ItemIceHardHoe extends ItemHoe {
    public static List<ItemIceHardHoe> items;
    int lv;

    public ItemIceHardHoe(String name, ToolMaterial mat, int lv) {
        super(mat);
        this.setRegistryName(name).setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.lv = lv;
    }

    static {
        items = new ArrayList<>();
        for (int i = 0; i < RegisterItem.toolSettings.length; i++) {
            ItemIceHardHoe s = new ItemIceHardHoe("icehard_hoe_"+BlockIceHard.iceHardNames[i], RegisterItem.toolSettings[i], i);
            items.add(s);
            GameRegistry.addSmelting(s, new ItemStack(s), 0.0f);
        }
    }

    public void throwBrokenTool(ItemStack itemStack, World world, EntityLivingBase entity) {
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            BlockPos p = player.getPosition();
            int d = player.dimension;
            ItemStack itm = new ItemStack(RegisterItem.brokenTool[this.lv]);
            EntityItem e = new EntityItem(world, p.getX(), p.getY(), p.getZ(), itm);
            e.dimension = d;
            world.spawnEntity(e);
        }
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase attacker) {
        itemStack.damageItem(1, attacker);
        if (itemStack.getCount() == 0) {
            this.throwBrokenTool(itemStack, attacker.world, attacker);
        }
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer user, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float offsetX, float offsetY, float offsetZ) {
        ItemStack item = user.getHeldItem(hand);
        int dmg = item.getItemDamage();
        EnumActionResult r = super.onItemUse(user, world, pos, hand, facing, offsetX, offsetY, offsetZ);
        if (r == EnumActionResult.PASS) {
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block == RegisterBlock.SAND || block == RegisterBlock.GRASS_BLOCK) {
                this.setBlock(item, user, world, pos, RegisterBlock.IceHardFarmland.getDefaultState());
            }
            r = EnumActionResult.SUCCESS;
        }
        int dmgNow = item.getItemDamage();
        if (dmgNow == 0 && dmgNow < dmg) {
            this.throwBrokenTool(item, world, user);
        }
        return r;
    }
}
