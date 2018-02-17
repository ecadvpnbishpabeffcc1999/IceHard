package com.stdio2016.icehard.items;

import net.minecraft.item.ItemStack;

class ItemEnergyConvert extends MyItem {
  public ItemEnergyConvert(String name) {
    super(name);
  }

  @Override
  public boolean hasContainerItem(ItemStack itemStack) {
    return true;
  }

  @Override
  public ItemStack getContainerItem(ItemStack itemStack) {
    return new ItemStack(this, 1);
  }
}