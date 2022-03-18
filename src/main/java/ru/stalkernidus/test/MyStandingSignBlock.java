package ru.stalkernidus.test;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MyStandingSignBlock extends StandingSignBlock {
    public MyStandingSignBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WoodType.OAK);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_56993_) {
        System.out.println("TEST 14");
        return super.getStateForPlacement(p_56993_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57012_) {
        System.out.println("TEST 18");
        super.createBlockStateDefinition(p_57012_);
    }

    @Override
    public boolean isPossibleToRespawnInThis() {
        System.out.println("TEST 20");
        return super.isPossibleToRespawnInThis();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        System.out.println("TEST 21");
        return super.newBlockEntity(p_154556_, p_154557_);
    }

    @Override
    public InteractionResult use(BlockState p_56278_, Level p_56279_, BlockPos p_56280_, Player p_56281_, InteractionHand p_56282_, BlockHitResult p_56283_) {
        System.out.println("TEST 22");
        return super.use(p_56278_, p_56279_, p_56280_, p_56281_, p_56282_, p_56283_);
    }

    @Override
    public boolean triggerEvent(BlockState p_49226_, Level p_49227_, BlockPos p_49228_, int p_49229_, int p_49230_) {
        System.out.println("TEST 26");
        return super.triggerEvent(p_49226_, p_49227_, p_49228_, p_49229_, p_49230_);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState p_49234_, Level p_49235_, BlockPos p_49236_) {
        System.out.println("TEST 27");
        return super.getMenuProvider(p_49234_, p_49235_, p_49236_);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
        System.out.println("TEST 28");
        super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }

    @Override
    public void setPlacedBy(Level p_49847_, BlockPos p_49848_, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack p_49851_) {
        System.out.println("TEST 29");
        super.setPlacedBy(p_49847_, p_49848_, p_49849_, p_49850_, p_49851_);
    }

}
