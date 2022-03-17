package com.student.items;

import com.student.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.*;

import static com.student.setup.Registration.Estus_Flask;

public class Bonfire extends CampfireBlock {
    private static int counter = 0;
    private static BlockPos last = new BlockPos(0,0,0);
    private static Map<Long, String> bonfires = new HashMap<>();

    public Bonfire() {
        super(true, 1,
                BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)
                        .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0).noOcclusion());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Long pos = blockPlaceContext.getClickedPos().asLong();
        if (!bonfires.containsKey(pos)) {
            counter++;
            bonfires.put(pos, "#" + counter);
            last=BlockPos.of(pos);
        }
        return super.getStateForPlacement(blockPlaceContext);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean bool) {
        bonfires.remove(blockPos.asLong());
        last = BlockPos.of( (Long) bonfires.keySet().toArray()[bonfires.size()-1]);
        super.onRemove(blockState, level, blockPos, blockState1, bool);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Inventory inv = player.getInventory();
        short flasks = 0;
        if (inv.contains(new ItemStack(Estus_Flask.get()))){
            for (ItemStack e : inv.items){
                if (e.getItem()==Estus_Flask.get()){
                    flasks+=e.getCount();
                    inv.removeItem(e);
                }
            }
            for (;flasks>0;flasks-=8){
                inv.add(
                        PotionUtils.setPotion(
                                new ItemStack(Registration.Estus.get(), flasks>8 ? 8:flasks),
                                Potions.STRONG_HEALING
                        )
                );
            }
        }
        player.teleportTo(last.getX(), last.getY(), last.getZ());
        return InteractionResult.SUCCESS;
    }
}
