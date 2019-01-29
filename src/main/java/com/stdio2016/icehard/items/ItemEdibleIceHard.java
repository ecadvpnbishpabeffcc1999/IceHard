package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by User on 2018/7/10.
 */
public class ItemEdibleIceHard extends ItemFood {
    public ItemEdibleIceHard(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setUnlocalizedName(name).setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.extinguish();
        }
    }
}
