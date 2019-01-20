package com.stdio2016.icehard;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

/**
 * Created by User on 2019/1/20.
 */
public class CraftCondition implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext var1, JsonObject var2) {
        String name = JsonUtils.getString(var2, "name");
        if (name.compareTo("CleanerCraftable") == 0)
            return new CleanerCraftable();
        return new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return false;
            }
        };
    }

    public static class CleanerCraftable implements BooleanSupplier {
        public boolean getAsBoolean() {
            return Settings.CleanerCraftable;
        }
    }
}
