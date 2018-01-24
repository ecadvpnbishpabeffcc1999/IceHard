package com.stdio2016.icehard.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

    public static void preInit(FMLPreInitializationEvent event) {
        items.add(ItemEnergyPile.item);
        copper = new MyItem("copper");
        items.add(copper);
        copperNugget = new MyItem("copper_nugget");
        items.add(copperNugget);
        items.addAll(ItemIceHardSword.items);
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
}
