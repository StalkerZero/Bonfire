package ru.stalkernidus.setup;

import ru.stalkernidus.BonfireMod;
import com.stalkernidus.items.*;
import ru.stalkernidus.items.CoiledSword;
import ru.stalkernidus.test.MySignItem;
import ru.stalkernidus.test.MyStandingSignBlock;
import ru.stalkernidus.test.MyWallSignBlock;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.stalkernidus.items.Estus;

public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BonfireMod.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BonfireMod.MOD_ID);

    public static final RegistryObject<CoiledSword> Coiled_Sword = ITEMS.register("coiled_sword",
            CoiledSword::new);

    public static final RegistryObject<ru.stalkernidus.blocks.Bonfire> Bonfire = BLOCKS.register("bonfire",
            ru.stalkernidus.blocks.Bonfire::new);

    public static final RegistryObject<Item> Bonfire_Item = ITEMS.register("bonfire",
            () -> new BlockItem(Bonfire.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<BottleItem> Estus_Flask = ITEMS.register("estus_flask",
            () -> new BottleItem((new Item.Properties()).tab(CreativeModeTab.TAB_BREWING)));

    public static final RegistryObject<ru.stalkernidus.items.Estus> Estus = ITEMS.register("estus", Estus::new);

    public static final RegistryObject<MyStandingSignBlock> MyStandingSign = BLOCKS.register("my_sign",
            MyStandingSignBlock::new);

    public static final RegistryObject<MyWallSignBlock> MyWallSign = BLOCKS.register("my_wall_sign",
            MyWallSignBlock::new);

    public static final RegistryObject<MySignItem> MySign = ITEMS.register("my_sign",
            MySignItem::new);

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }
}
