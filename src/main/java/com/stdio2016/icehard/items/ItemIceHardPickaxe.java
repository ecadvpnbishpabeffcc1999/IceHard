package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/1/23.
 */
public class ItemIceHardPickaxe extends ItemPickaxe {
    public static List<ItemIceHardPickaxe> items;

    public ItemIceHardPickaxe(String name, ToolMaterial mat) {
        super(mat);
        this.setRegistryName(name).setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
    }

    static {
        items = new ArrayList<>();
        for (int i = 0; i < RegisterItem.toolSettings.length; i++) {
            ItemIceHardPickaxe s = new ItemIceHardPickaxe("icehard_pickaxe_"+BlockIceHard.iceHardNames[i], RegisterItem.toolSettings[i]);
            items.add(s);
            GameRegistry.addSmelting(s, new ItemStack(s), 0.0f);
        }
    }
}
