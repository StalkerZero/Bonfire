package ru.stalkernidus.items;

/*import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;*/
import net.minecraft.world.item.*;

public class CoiledSword extends SwordItem {
    public CoiledSword() {
        super(Tiers.NETHERITE, 5, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).fireResistant());
    }

   /* @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.))
        return super.hurtEnemy(itemStack, target, attacker);
    }*/
}
