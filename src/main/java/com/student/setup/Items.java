package com.student.setup;

import com.student.items.Estus;
import com.student.items.CoiledSword;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Items {
    public static final Item Coiled_Sword = Registry.register(Registry.ITEM, new ResourceLocation("coiled_sword"), new CoiledSword());
    public static final Item Estus_Flask = Registry.register(Registry.ITEM, new ResourceLocation("estus_empty"), new BottleItem((new Item.Properties()).tab(CreativeModeTab.TAB_BREWING)));
    public static final Item Estus = Registry.register(Registry.ITEM, new ResourceLocation("estus_filled"), new Estus());
}

