package com.stdio2016.icehard.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
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
        OreDictionary.registerOre("ingotCopper", copper);
        items.add(copper);
        copperNugget = new MyItem("copper_nugget");
        items.add(copperNugget);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> reg = event.getRegistry();
        for (Item i : items) {
            reg.register(i);
        }
    }
}
