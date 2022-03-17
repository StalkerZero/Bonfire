package com.student.setup;

import com.student.dsbonfire.TestMod;
import com.student.items.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.mod_Id);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.mod_Id);

    public static final RegistryObject<CoiledSword> Coiled_Sword = ITEMS.register("coiled_sword", CoiledSword::new);
    public static final RegistryObject<Bonfire> Bonfire = BLOCKS.register("bonfire", Bonfire::new);
    public static final RegistryObject<Item> Bonfire_Item = ITEMS.register("bonfire", () -> new BlockItem(Bonfire.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<BottleItem> Estus_Flask = ITEMS.register("estus_flask", () -> new BottleItem((new Item.Properties()).tab(CreativeModeTab.TAB_BREWING)));
    public static final RegistryObject<Estus> Estus = ITEMS.register("estus", Estus::new);

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }
}
