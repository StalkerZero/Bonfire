package com.student.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
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

import static com.student.setup.Registration.Estus_Flask;

public class Estus extends PotionItem {

    public Estus() {
        super((new Item.Properties()).stacksTo(8).tab(CreativeModeTab.TAB_BREWING));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_42984_, Level p_42985_, LivingEntity p_42986_) {
        Player player = p_42986_ instanceof Player ? (Player)p_42986_ : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, p_42984_);
        }

        if (!p_42985_.isClientSide) {
            for(MobEffectInstance mobeffectinstance : PotionUtils.getMobEffects(p_42984_)) {
                if (mobeffectinstance.getEffect().isInstantenous()) {
                    mobeffectinstance.getEffect().applyInstantenousEffect(player, player, p_42986_, mobeffectinstance.getAmplifier(), 1.0D);
                } else {
                    p_42986_.addEffect(new MobEffectInstance(mobeffectinstance));
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                p_42984_.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (p_42984_.isEmpty()) {
                return new ItemStack(Estus_Flask.get());
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(Estus_Flask.get()));
            }
        }

        p_42985_.gameEvent(p_42986_, GameEvent.DRINKING_FINISH, p_42986_.eyeBlockPosition());
        return p_42984_;
    }

    @Override
    public void fillItemCategory(CreativeModeTab modeTab, NonNullList<ItemStack> itemStacks) {
        if (!this.allowdedIn(modeTab)) return;
        itemStacks.add(PotionUtils.setPotion(new ItemStack(this), Potions.STRONG_HEALING));
    }

}
