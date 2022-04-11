package ru.stalkernidus.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.stalkernidus.entities.BonfireEntity;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class BonfireNameScreen extends Screen {
    private final BonfireEntity bonfire;
    private int frame;
    private TextFieldHelper field;
    private WoodType woodType;
    private SignRenderer.SignModel signModel;
    private String name;
    private final Player player;

    public BonfireNameScreen(BonfireEntity bonfire, Player player) {
        super(new TranslatableComponent("bonfire.edit"));
        this.name = bonfire.getName();
        this.bonfire = bonfire;
        this.player = player;
    }

    @Override
    protected void init() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.addRenderableWidget(new Button(
                this.width / 2 - 100,
                this.height / 4 + 120,
                200,
                20,
                CommonComponents.GUI_DONE, (p_169820_) ->
                    { this.onDone(); }
        ));
        this.field = new TextFieldHelper(() -> this.name,
                (p_169824_) -> {
                    this.name = p_169824_;
                    this.bonfire.setName(p_169824_);
                },
                TextFieldHelper.createClipboardGetter(this.minecraft),
                TextFieldHelper.createClipboardSetter(this.minecraft),
                (p_169822_) -> this.minecraft.font.width(p_169822_) <= 90
        );
        BlockState blockstate = this.bonfire.getBlockState();
        this.woodType = SignRenderer.getWoodType(blockstate.getBlock());
        this.signModel = SignRenderer.createSignModel(this.minecraft.getEntityModels(), this.woodType);
    }

    public void removed() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
        ClientPacketListener clientpacketlistener = this.minecraft.getConnection();
        if (clientpacketlistener != null) {
            clientpacketlistener.send(new ServerboundSignUpdatePacket(this.bonfire.getBlockPos(), this.name, "", "", ""));
        }
    }

    public void tick() {
        ++this.frame;
        if (!this.bonfire.getType().isValid(this.bonfire.getBlockState())) {
            this.onDone();
        }
    }

    private void onDone() {
        if (this.name.equals(""))
            this.bonfire.setName(new TranslatableComponent("bonfire.unnamed").getString());
        this.bonfire.setTpPos(player.getOnPos());
        this.bonfire.setDimension(this.player.getLevel().dimension().location().getPath());
        this.bonfire.setChanged();
        this.bonfire.getCanUse().add(player.getUUID());
//        this.bonfire.getCanUse().add(UUID.randomUUID());
//        System.out.println("\nToString: "+this.bonfire.getCanUse().toString()+"\n");
        this.bonfire.getBonfires().add(this.bonfire);
        this.minecraft.setScreen((Screen)null);
    }

    public boolean charTyped(char p_99262_, int p_99263_) {
        return this.field.charTyped(p_99262_);
    }

    public void onClose() {
        this.onDone();
    }

    @Override
    public boolean keyPressed(int i1, int i2, int i3) {
        if (i1 == 257 && i2==28) {
            this.onDone();
            return true;
        }
        return this.field.keyPressed(i1) || super.keyPressed(i1, i2, i3);
    }

    public void render(PoseStack poseStack, int p_99272_, int p_99273_, float p_99274_) {
        Lighting.setupForFlatItems();
        this.renderBackground(poseStack);
        drawCenteredString(poseStack, this.font, this.title, this.width / 2, 40, 16777215);
        poseStack.pushPose();
        poseStack.translate((double)(this.width / 2), 0.0D, 50.0D);
        poseStack.scale(93.75F, -93.75F, 93.75F);
        poseStack.translate(0.0D, -1.3125D, 0.0D);
        BlockState blockstate = this.bonfire.getBlockState();
        boolean flag = blockstate.getBlock() instanceof StandingSignBlock;
        if (!flag) {
            poseStack.translate(0.0D, -0.3125D, 0.0D);
        }

        boolean flag1 = this.frame / 6 % 2 == 0;
        poseStack.pushPose();
        poseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        MultiBufferSource.BufferSource multibuffersource$buffersource = this.minecraft.renderBuffers().bufferSource();
        Material material = Sheets.getSignMaterial(this.woodType);
        VertexConsumer vertexconsumer = material.buffer(multibuffersource$buffersource, this.signModel::renderType);
        this.signModel.stick.visible = flag;
        this.signModel.root.render(poseStack, vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        poseStack.translate(0.0D, 0.33333334D, 0.046666667D);
        poseStack.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int i = DyeColor.BLACK.getTextColor();
        int j = this.field.getCursorPos();
        int k = this.field.getSelectionPos();
        int l = 5;
        Matrix4f matrix4f = poseStack.last().pose();

        String s = this.name;
        if (s != null) {
            if (this.font.isBidirectional()) {
                s = this.font.bidirectionalShaping(s);
            }

            float f3 = (float)(-this.minecraft.font.width(s) / 2);
            this.minecraft.font.drawInBatch(s, f3, (float)(10 - 5), i, false, matrix4f, multibuffersource$buffersource, false, 0, 15728880, false);
            if (j >= 0 && flag1) {
                int j1 = this.minecraft.font.width(s.substring(0, Math.max(Math.min(j, s.length()), 0)));
                int k1 = j1 - this.minecraft.font.width(s) / 2;
                if (j >= s.length()) {
                    this.minecraft.font.drawInBatch("_", (float)k1, (float)l, i, false, matrix4f, multibuffersource$buffersource, false, 0, 15728880, false);
                }
            }
        }

        multibuffersource$buffersource.endBatch();

        String s1 = this.name;
        if (s1 != null && j >= 0) {
            int j3 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
            int k3 = j3 - this.minecraft.font.width(s1) / 2;
            if (flag1 && j < s1.length()) {
                fill(poseStack, k3, l - 1, k3 + 1, l + 9, -16777216 | i);
            }

            if (k != j) {
                int l3 = Math.min(j, k);
                int l1 = Math.max(j, k);
                int i2 = this.minecraft.font.width(s1.substring(0, l3)) - this.minecraft.font.width(s1) / 2;
                int j2 = this.minecraft.font.width(s1.substring(0, l1)) - this.minecraft.font.width(s1) / 2;
                int k2 = Math.min(i2, j2);
                int l2 = Math.max(i2, j2);
                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder bufferbuilder = tesselator.getBuilder();
                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                RenderSystem.disableTexture();
                RenderSystem.enableColorLogicOp();
                RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
                bufferbuilder.vertex(matrix4f, (float)k2, (float)(l + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                bufferbuilder.vertex(matrix4f, (float)l2, (float)(l + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                bufferbuilder.vertex(matrix4f, (float)l2, (float)l, 0.0F).color(0, 0, 255, 255).endVertex();
                bufferbuilder.vertex(matrix4f, (float)k2, (float)l, 0.0F).color(0, 0, 255, 255).endVertex();
                bufferbuilder.end();
                BufferUploader.end(bufferbuilder);
                RenderSystem.disableColorLogicOp();
                RenderSystem.enableTexture();
            }
        }

        poseStack.popPose();
        Lighting.setupFor3DItems();
        super.render(poseStack, p_99272_, p_99273_, p_99274_);
    }
}
