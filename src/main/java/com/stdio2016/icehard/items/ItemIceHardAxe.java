package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/1/23.
 */
public class ItemIceHardAxe extends ItemAxe {
    public static List<ItemIceHardAxe> items;
    public static final float axeDmg[] = {7, 9, 9, 9, 10, 10, 11};
    public static final float axeSpeed[] = {0.8f, 0.8f, 0.9f, 1f, 1f, 1.1f, 1.2f};
    int lv;

    public ItemIceHardAxe(String name, ToolMaterial mat, int lv) {
        super(ToolMaterial.WOOD);
        this.toolMaterial = mat;
        this.setMaxDamage(mat.getMaxUses());
        this.efficiencyOnProperMaterial = mat.getEfficiencyOnProperMaterial();
        this.damageVsEntity = axeDmg[lv];
        this.attackSpeed = axeSpeed[lv] - 4f;
        this.setRegistryName(name).setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.lv = lv;
    }

    static {
        items = new ArrayList<>();
        for (int i = 0; i < RegisterItem.toolSettings.length; i++) {
            ItemIceHardAxe s = new ItemIceHardAxe("icehard_axe_"+BlockIceHard.iceHardNames[i], RegisterItem.toolSettings[i], i);
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
    public boolean onBlockDestroyed(ItemStack itemStack, World world, IBlockState block, BlockPos pos, EntityLivingBase entity) {
        if(!world.isRemote && block.getBlockHardness(world, pos) != 0.0f) {
            itemStack.damageItem(1, entity);
            if (itemStack.getCount() == 0) {
                this.throwBrokenTool(itemStack, world, entity);
            }
        }
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase attacker) {
        itemStack.damageItem(2, attacker);
        if (itemStack.getCount() == 0) {
            this.throwBrokenTool(itemStack, attacker.world, attacker);
        }
        return true;
    }
}
