package ru.stalkernidus.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

public class CoiledSword extends SwordItem {
    public CoiledSword() {
        super(Tiers.NETHERITE, 5, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        target.setSecondsOnFire(2);
        return super.hurtEnemy(itemStack, target, attacker);
    }
}
