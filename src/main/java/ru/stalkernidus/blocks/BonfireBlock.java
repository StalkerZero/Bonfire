package ru.stalkernidus.blocks;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import ru.stalkernidus.entities.BonfireEntity;
import ru.stalkernidus.screens.BonfireUseScreen;
import ru.stalkernidus.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.Map;

import static com.mojang.realmsclient.util.task.LongRunningTask.setScreen;
import static ru.stalkernidus.setup.Registration.ESTUS_FLASK;

public class BonfireBlock extends CampfireBlock implements EntityBlock {

    public BonfireBlock() {
        super(true, 1,
                BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)
                        .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0).noOcclusion());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide) {
            player.clearFire();
            player.removeEffect(MobEffects.POISON);
//            List<MobEffect> effects = player.getActiveEffectsMap().keySet().stream().toList();
//            for(MobEffect e : effects) if(!e.isBeneficial()) player.removeEffect(e);
            Inventory inv = player.getInventory();
            short flasks = 0;
            if (inv.contains(new ItemStack(ESTUS_FLASK.get()))) {
                for (ItemStack e : inv.items) {
                    if (e.getItem() == ESTUS_FLASK.get()) {
                        flasks += e.getCount();
                        inv.removeItem(e);
                    }
                }
                for (; flasks > 0; flasks -= 8) {
                    inv.add(
                            PotionUtils.setPotion(
                                    new ItemStack(Registration.ESTUS.get(), flasks > 8 ? 8 : flasks),
                                    Potions.STRONG_HEALING
                            )
                    );
                }
            }
            if (player.getHealth() < player.getMaxHealth()) player.setHealth(player.getMaxHealth());
            setScreen(new BonfireUseScreen((BonfireEntity) level.getBlockEntity(pos), player));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BonfireEntity(pos, state);
    }
}
