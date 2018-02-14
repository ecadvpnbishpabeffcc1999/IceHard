package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
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

    public ItemIceHardAxe(String name, ToolMaterial mat, int lv) {
        super(ToolMaterial.WOOD);
        this.toolMaterial = mat;
        this.setMaxDamage(mat.getMaxUses());
        this.efficiencyOnProperMaterial = mat.getEfficiencyOnProperMaterial();
        this.damageVsEntity = axeDmg[lv];
        this.attackSpeed = axeSpeed[lv] - 4f;
        this.setRegistryName(name).setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
    }

    static {
        items = new ArrayList<>();
        for (int i = 0; i < RegisterItem.toolSettings.length; i++) {
            ItemIceHardAxe s = new ItemIceHardAxe("icehard_axe_"+BlockIceHard.iceHardNames[i], RegisterItem.toolSettings[i], i);
            items.add(s);
            GameRegistry.addSmelting(s, new ItemStack(s), 0.0f);
        }
    }
}
