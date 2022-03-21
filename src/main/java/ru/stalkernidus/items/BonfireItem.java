package ru.stalkernidus.items;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import ru.stalkernidus.blocks.BonfireEntity;
import ru.stalkernidus.blocks.BonfireNameScreen;

import static com.mojang.realmsclient.util.task.LongRunningTask.setScreen;
import static ru.stalkernidus.setup.Registration.BONFIRE;

public class BonfireItem extends BlockItem {
    public BonfireItem() {
        super(BONFIRE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack stack, BlockState state) {
        boolean flag = super.updateCustomBlockEntityTag(pos, level, player, stack, state);
        BonfireEntity bonfire = (BonfireEntity) level.getBlockEntity(pos);
        if (!level.isClientSide && !flag && player != null && bonfire != null) {
            bonfire.setTpPos(player.getOnPos());
            setScreen(new BonfireNameScreen(bonfire));
        }
        return flag;
    }
}
