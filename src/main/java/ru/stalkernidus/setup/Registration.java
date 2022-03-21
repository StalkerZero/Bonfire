package ru.stalkernidus.setup;

import net.minecraft.world.level.block.entity.BlockEntityType;
import ru.stalkernidus.blocks.*;
import ru.stalkernidus.items.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, BonfireMod.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, BonfireMod.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITIES, BonfireMod.MOD_ID);

    public static final RegistryObject<CoiledSword> COILED_SWORD =ITEMS.register("coiled_sword",CoiledSword::new);

    public static final RegistryObject<BonfireBlock> BONFIRE = BLOCKS.register("bonfire", BonfireBlock::new);

    public static final RegistryObject<Item> BONFIRE_ITEM = ITEMS.register("bonfire", BonfireItem::new);

    public static final RegistryObject<BlockEntityType<BonfireEntity>> BONFIRE_ENTITY = BLOCK_ENTITY.register(
            "bonfire", () -> BlockEntityType.Builder.of(BonfireEntity::new, BONFIRE.get()).build(null));

    public static final RegistryObject<BottleItem> ESTUS_FLASK = ITEMS.register("estus_flask",
            () -> new BottleItem((new Item.Properties()).tab(CreativeModeTab.TAB_BREWING)));

    public static final RegistryObject<Estus> ESTUS = ITEMS.register("estus", Estus::new);

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCK_ENTITY.register(bus);
    }
}
