package com.stdio2016.icehard.proxy;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.BlockIceHard;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by User on 2018/1/20.
 */
public class ClientOnly extends CommonProxy {
    public final String MODID = IceHardMod.MODID;
    @Override
    public void init(FMLInitializationEvent ev) {
        super.init(ev);
        for (Item i: RegisterBlock.items) {
            addImageForItem(i);
        }
    }

    private void addImageForItem(Item item) {
        ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        String path = this.MODID+":"+item.getUnlocalizedName().substring(5);
        ModelResourceLocation res = new ModelResourceLocation(path, "inventory");
        modelMesher.register(item, 0, res);
    }
}
