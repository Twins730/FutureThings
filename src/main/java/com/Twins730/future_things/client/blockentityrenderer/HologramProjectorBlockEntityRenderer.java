package com.Twins730.future_things.client.blockentityrenderer;

import com.Twins730.future_things.FutureThings;
import com.Twins730.future_things.block.blockentity.HologramProjectorBlockEntity;
import com.Twins730.future_things.setup.ShadersSetup;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.phys.AABB;


import java.util.Random;

import static net.minecraft.client.renderer.texture.OverlayTexture.pack;

public class HologramProjectorBlockEntityRenderer implements BlockEntityRenderer<HologramProjectorBlockEntity> {
    private final EntityRenderDispatcher entityRenderer;
    private Entity entity;
    private float spin = 0;

    public HologramProjectorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.getEntityRenderer();
    }

    @Override
    public void render(HologramProjectorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if(Minecraft.getInstance().player != null) {
            if(blockEntity.getItem(0).getItem() instanceof SpawnEggItem) {
                this.entity =  ((SpawnEggItem) blockEntity.getItem(0).getItem()).getDefaultType().create(blockEntity.getLevel());
                if(entity instanceof Mob){
                    ((Mob)this.entity).setBaby(false);
                    this.entity.setXRot(0);
                    this.entity.setYRot(0);
                }
                if(spin > 360){
                    this.spin = 0;
                }
                this.spin += 0.05f;

                poseStack.pushPose();
                poseStack.translate(0.5F, 2.5f + Math.sin(FutureThings.PI_TIME / 2) / 50, 0.5F);
                poseStack.scale(1f, 1f, 1f);
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));
                poseStack.mulPose(Axis.YP.rotationDegrees(spin));

                if (entityRenderer.getRenderer(entity) instanceof LivingEntityRenderer) {
                    EntityModel<LivingEntity> model = ((LivingEntityRenderer) entityRenderer.getRenderer(entity)).getModel();
                    model.setupAnim((LivingEntity) entity, 0, 0, 0, 0, 0);
                    model.renderToBuffer(poseStack, bufferSource.getBuffer(this.renderType(entityRenderer.getRenderer(entity).getTextureLocation(entity))), 226, pack(0, 10));
                }
                poseStack.popPose();
            }
        }
    }

    @Override
    public AABB getRenderBoundingBox(HologramProjectorBlockEntity blockEntity) {
        return AABB.INFINITE;
    }

    @Override
    public boolean shouldRenderOffScreen(HologramProjectorBlockEntity blockEntity) {
        return true;
    }

    protected RenderType renderType(ResourceLocation location) {
        return ShadersSetup.hologram(location);
    }

}
