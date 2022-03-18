package ru.stalkernidus.test;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import ru.stalkernidus.setup.Registration;

import java.util.Map;

public class MySignItem extends SignItem {
    public MySignItem() {
        super(new Item.Properties().stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), Registration.MyStandingSign.get(), Registration.MyWallSign.get());
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos p_43130_, Level p_43131_, @Nullable Player p_43132_, ItemStack p_43133_, BlockState p_43134_) {
        System.out.println("TEST 0");
        return super.updateCustomBlockEntityTag(p_43130_, p_43131_, p_43132_, p_43133_, p_43134_);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext p_43255_) {
        System.out.println("TEST 1");
        return super.getPlacementState(p_43255_);
    }

    @Override
    public void registerBlocks(Map<Block, Item> p_43252_, Item p_43253_) {
        System.out.println("TEST 2");
        super.registerBlocks(p_43252_, p_43253_);
    }

    @Override
    public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
        System.out.println("TEST 3");
        super.removeFromBlockToItemMap(blockToItemMap, itemIn);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_40581_) {
        System.out.println("TEST 4");
        return super.useOn(p_40581_);
    }

    @Override
    public InteractionResult place(BlockPlaceContext p_40577_) {
        System.out.println("TEST 5");
        return super.place(p_40577_);
    }

    @Nullable
    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext p_40609_) {
        System.out.println("TEST 6");
        return super.updatePlacementContext(p_40609_);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext p_40611_, BlockState p_40612_) {
        System.out.println("TEST 7");
        return super.canPlace(p_40611_, p_40612_);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext p_40578_, BlockState p_40579_) {
        System.out.println("TEST 8");
        return super.placeBlock(p_40578_, p_40579_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        System.out.println("TEST 10");
        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
        System.out.println("TEST 11");
        return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
    }

    @Override
    public boolean useOnRelease(ItemStack p_41464_) {
        System.out.println("TEST 12");
        return super.useOnRelease(p_41464_);
    }
}
