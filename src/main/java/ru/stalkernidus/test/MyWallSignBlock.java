package ru.stalkernidus.test;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import ru.stalkernidus.setup.Registration;

public class MyWallSignBlock extends WallSignBlock {
    public MyWallSignBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(Registration.MyStandingSign.get()), WoodType.OAK);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_58071_) {
        System.out.println("TEST 34");
        return super.getStateForPlacement(p_58071_);
    }

    @Override
    public BlockState updateShape(BlockState p_58083_, Direction p_58084_, BlockState p_58085_, LevelAccessor p_58086_, BlockPos p_58087_, BlockPos p_58088_) {
        System.out.println("TEST 35");
        return super.updateShape(p_58083_, p_58084_, p_58085_, p_58086_, p_58087_, p_58088_);
    }

    @Override
    public BlockState rotate(BlockState p_58080_, Rotation p_58081_) {
        System.out.println("TEST 36");
        return super.rotate(p_58080_, p_58081_);
    }

    @Override
    public BlockState mirror(BlockState p_58077_, Mirror p_58078_) {
        System.out.println("TEST 37");
        return super.mirror(p_58077_, p_58078_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_58090_) {
        System.out.println("TEST 38");
        super.createBlockStateDefinition(p_58090_);
    }
}
