package com.stdio2016.icehard.items;

import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stdio2016 on 2017/6/19.
 */

public class RegisterItem {
    public static MyItem copperNugget, copper;
    public static List<Item> items = new ArrayList<>();
    public static final Item.ToolMaterial[] toolSettings = new Item.ToolMaterial[BlockIceHard.MaxLevel];
    public static MyItem[] brokenTool = new MyItem[BlockIceHard.MaxLevel];

    public static void preInit(FMLPreInitializationEvent event) {
        items.add(ItemEnergyPile.item);
        copper = new MyItem("copper");
        items.add(copper);
        copperNugget = new MyItem("copper_nugget");
        items.add(copperNugget);
        items.addAll(ItemIceHardSword.items);
        items.addAll(ItemIceHardPickaxe.items);
        items.addAll(ItemIceHardAxe.items);
        for (int i = 0; i < BlockIceHard.MaxLevel; i++) {
            brokenTool[i] = new MyItem("broken_tool_" + BlockIceHard.iceHardNames[i]);
            items.add(brokenTool[i]);
        }
    }

    public static void init(FMLInitializationEvent event) {
        OreDictionary.registerOre("ingotCopper", copper);
        OreDictionary.registerOre("listAllwater", Items.WATER_BUCKET);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> reg = event.getRegistry();
        for (Item i : items) {
            reg.register(i);
        }
    }

    public static Item.ToolMaterial addToolSetting(int lv, int maxUses, int damage) {
        return EnumHelper.addToolMaterial("ICEHARD_" + BlockIceHard.iceHardNames[lv].toUpperCase(),
                3, maxUses - 1, lv * 2 + 4, damage - 2, lv * 2 + 5);
    }

    static {
        /*
        * hoe damage = 1 (fixed)
        * pickaxe damage +0
        * shovel damage +0.5
        * sword damage +2
        * axe damage = 7 or 9
        * */
        //  lv,  max uses,  damage
        toolSettings[0] = addToolSetting(0, 33, 2);
        toolSettings[1] = addToolSetting(1, 65, 3);
        toolSettings[2] = addToolSetting(2, 129, 4);
        toolSettings[3] = addToolSetting(3, 257, 5);
        toolSettings[4] = addToolSetting(4, 385, 6);
        toolSettings[5] = addToolSetting(5, 513, 7);
        toolSettings[6] = addToolSetting(6, 1023, 9);
    }
}
