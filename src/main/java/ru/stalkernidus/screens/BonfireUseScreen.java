package ru.stalkernidus.screens;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
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
        int size = bonfires.size();

        if (size>4){
            this.addRenderableWidget(new Button(
                    width+150,
                    height+84,
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
                    height+84,
                    50,
                    20,
                    new TranslatableComponent("bonfire.use.prev"),
                    (button) -> {
                        this.page--;
                        this.changePage();
                    }
            ));
            ((Button)this.renderables.get(1)).visible = false;
        }

        this.addRenderableWidget(new Button(
                width,
                height+110,
                200,
                20,
                CommonComponents.GUI_CANCEL,
                (button) -> { this.onClose(); }
        ));

        for (int i=0; i<size; i+=4) {
            for(int j=0; j<4; j++) {
                if (bonfires.size()-1<i+j) break;
                BonfireEntity bonfire = bonfires.get(i+j);
                BlockPos pos = bonfire.getBlockPos();
                this.addRenderableWidget(new Button(
                        width,
                        height+j*21,
                        200,
                        20,
                        new TextComponent(
                                bonfire.getName() +
                                        " (x=" + pos.getX() +
                                        "; y=" + pos.getY() +
                                        "; z=" + pos.getZ() + ")"),
                        (button) -> {
                            this.teleport(bonfire);
                        }
                ));
                if(i>0) ((Button)this.renderables.get(3+i+j)).visible = false;
            }
        }
    }

    private void changePage(){
        List<BonfireEntity> bonfires = bonfire.getBonfiresForUse(player);
        int size = bonfires.size();

        for (int i=0; i<size; i++){
            if(page*4<=i && i<page*4+4) ((Button)renderables.get(3+i)).visible = true;
            else ((Button)renderables.get(3+i)).visible = false;
        }

        ((Button)renderables.get(0)).visible = size>4*(page+1);
        ((Button)renderables.get(1)).visible = page > 0;
    }

    public void tick() {
        if (!this.bonfire.getType().isValid(this.bonfire.getBlockState())) {
            this.onClose();
        }
    }

    private void teleport(BonfireEntity bonfire) {
        BlockPos tpPos = bonfire.getTpPos();
        BlockPos bonfirePos = bonfire.getBlockPos();

        this.player.teleportTo(tpPos.getX(), tpPos.getY()+1, tpPos.getZ());
        player.lookAt(
                EntityAnchorArgument.Anchor.EYES,
                new Vec3(bonfirePos.getX()+0.5, bonfirePos.getY()+0.2, bonfirePos.getZ()+0.5)
        );

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
