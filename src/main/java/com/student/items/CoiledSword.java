package com.student.items;

import net.minecraft.world.item.*;

public class CoiledSword extends SwordItem {
    public CoiledSword() {
        super(Tiers.NETHERITE, 5, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).fireResistant());
    }
}
