package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/1/23.
 */
public class ItemIceHardSword extends ItemSword {
    public static final ToolMaterial[] settings = new ToolMaterial[BlockIceHard.iceHardNames.length];
    public static List<ItemIceHardSword> items;

    public ItemIceHardSword(String name, ToolMaterial mat) {
        super(mat);
        this.setRegistryName(name).setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
    }

    public static ToolMaterial addSwordSetting(int lv, int maxUses, int damage) {
        return EnumHelper.addToolMaterial("ICEHARD_SWORD_" + BlockIceHard.iceHardNames[lv].toUpperCase(),
                3, maxUses, 8.0f, damage - 4, lv * 2 + 5);
    }

    static {
        //  lv,  max uses,  damage
        settings[0] = addSwordSetting(0, 250, 4);
        settings[1] = addSwordSetting(1, 500, 5);
        settings[2] = addSwordSetting(2, 750, 6);
        settings[3] = addSwordSetting(3, 1000, 7);
        settings[4] = addSwordSetting(4, 1250, 8);
        settings[5] = addSwordSetting(5, 1500, 9);
        settings[6] = addSwordSetting(6, 1750, 11);

        items = new ArrayList<>();
        for (int i = 0; i < settings.length; i++) {
            items.add(new ItemIceHardSword("icehard_sword_"+BlockIceHard.iceHardNames[i], settings[i]));
        }
    }
}
