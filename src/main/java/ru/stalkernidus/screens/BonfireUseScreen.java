package ru.stalkernidus.screens;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.stalkernidus.entities.BonfireEntity;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class BonfireUseScreen extends Screen {
    private final BonfireEntity bonfire;
    private int page = 0;
    private final Player player;

    public BonfireUseScreen(BonfireEntity bonfire, Player player) {
        super(new TranslatableComponent("bonfire.use"));
        this.bonfire = bonfire;
        this.player = player;
        bonfire.getCanUse().add(player.getUUID());
    }

    @Override
    protected void init() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        List<BonfireEntity> bonfires = this.bonfire.getBonfiresForUse(player);
        int width = this.width / 2 - 100;
        int height = this.height / 4 + 8;
        int size = Math.min(4, bonfires.size());

        for (int i=0; i < size; i++){
            BonfireEntity bonfire = bonfires.get(i);
            BlockPos pos = bonfire.getBlockPos();
            this.addRenderableWidget(new Button(
                    width,
                    height,
                    200,
                    20,
                    new TextComponent(
                            bonfire.getName()+
                                    " (x="+pos.getX()+
                                    "; y="+pos.getY()+
                                    "; z="+pos.getZ()+")"),
                    (button) -> { this.teleport(bonfire); }
            ));
            height+=21;
        }

        if (bonfires.size()>4){
            this.addRenderableWidget(new Button(
                    width+150,
                    height,
                    50,
                    20,
                    new TranslatableComponent("bonfire.use.next"),
                    (button) -> {
                        this.page++;
                        this.changePage();
                    }
            ));

            this.addRenderableWidget(new Button(
                    width,
                    height,
                    50,
                    20,
                    new TranslatableComponent("bonfire.use.prev"),
                    (button) -> {
                        this.page--;
                        this.changePage();
                    }
            ));
            ((Button)this.renderables.get(5)).visible = false;
            height+=21;
        }

        this.addRenderableWidget(new Button(
                width,
                height+5,
                200,
                20,
                CommonComponents.GUI_CANCEL,
                (button) -> { this.onClose(); }
        ));
    }

    private void changePage(){
        List<BonfireEntity> bonfires = this.bonfire.getBonfiresForUse(player);
        int size = Math.min(4, bonfires.size()-4*this.page);
        int width = this.width / 2 - 100;
        int height = this.height / 4 + 8;

        for (int i=0; i<size; i++){
            BonfireEntity bonfire = bonfires.get(i+4*this.page);
            BlockPos pos = bonfire.getBlockPos();
            this.renderables.set(i, new Button(
                    width,
                    height,
                    200,
                    20,
                    new TextComponent(
                            bonfire.getName()+
                                    " (x="+pos.getX()+
                                    "; y="+pos.getY()+
                                    "; z="+pos.getZ()+")"),
                    (button) -> { this.teleport(bonfire); }
            ));
            height+=21;
        }

        if (size<4){
            for (;size<4;size++){
                ((Button)this.renderables.get(size)).visible = false;
            }
        }

        ((Button)this.renderables.get(4)).visible = !(bonfires.size()-4*(this.page+1)<1);
        ((Button)this.renderables.get(5)).visible = this.page > 0;
    }

    public void tick() {
        if (!this.bonfire.getType().isValid(this.bonfire.getBlockState())) {
            this.onClose();
        }
    }

    private void teleport(BonfireEntity bonfire) {
        BlockPos pos = bonfire.getTpPos();
        this.player.teleportTo(pos.getX(), pos.getY()+1, pos.getZ());
        this.onClose();
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen((Screen)null);
    }

    @Override
    public boolean keyPressed(int i1, int i2, int i3) {
        if (i1 == 256) {
            this.onClose();
            return true;
        }
        return false;
    }
}
