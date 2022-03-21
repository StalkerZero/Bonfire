package ru.stalkernidus.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import static ru.stalkernidus.setup.Registration.ESTUS_FLASK;

public class Estus extends PotionItem {

    public Estus() {
        super((new Item.Properties()).stacksTo(8).tab(CreativeModeTab.TAB_BREWING));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player)livingEntity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }

        if (!level.isClientSide) {
            for(MobEffectInstance mobeffectinstance : PotionUtils.getMobEffects(stack)) {
                if (mobeffectinstance.getEffect().isInstantenous()) {
                    mobeffectinstance.getEffect().applyInstantenousEffect(player, player, livingEntity, mobeffectinstance.getAmplifier(), 1.0D);
                } else {
                    livingEntity.addEffect(new MobEffectInstance(mobeffectinstance));
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(ESTUS_FLASK.get());
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(ESTUS_FLASK.get()));
            }
        }

        level.gameEvent(livingEntity, GameEvent.DRINKING_FINISH, livingEntity.eyeBlockPosition());
        return stack;
    }

    @Override
    public void fillItemCategory(CreativeModeTab modeTab, NonNullList<ItemStack> itemStacks) {
        if (!this.allowdedIn(modeTab)) return;
        itemStacks.add(PotionUtils.setPotion(new ItemStack(this), Potions.STRONG_HEALING));
    }

}
