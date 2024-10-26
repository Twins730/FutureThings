package com.Twins730.future_things.client.blockentityrenderer;

import com.Twins730.future_things.block.blockentity.HologramProjectorBlockEntity;
import com.Twins730.future_things.setup.ShadersSetup;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.phys.AABB;
import net.neoforged.fml.earlydisplay.ElementShader;
import net.neoforged.neoforge.client.RenderTypeHelper;
import org.joml.Matrix4f;

import static net.minecraft.client.renderer.texture.OverlayTexture.pack;


public class HologramProjectorBlockEntityRenderer implements BlockEntityRenderer<HologramProjectorBlockEntity> {

    private final EntityRenderDispatcher entityRenderer;
    private Entity entity;

    public HologramProjectorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.getEntityRenderer();

    }

    @Override
    public void render(HologramProjectorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if(Minecraft.getInstance().player != null) {

            if(entity == null){
                this.entity = EntityType.RAVAGER.create(blockEntity.getLevel());
                this.entity.setYHeadRot(0);
                this.entity.setXRot(0);
            }

            poseStack.pushPose();
            poseStack.translate(0.5F, 3.0f, 0.5F);
            poseStack.scale(1f,1f,1f);
            poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));

            if(entityRenderer.getRenderer(entity) instanceof LivingEntityRenderer) {
                EntityModel<LivingEntity> model = ((LivingEntityRenderer) entityRenderer.getRenderer(entity)).getModel();
                model.setupAnim((LivingEntity) entity, 0,0,0,0,0);

                model.renderToBuffer(poseStack, bufferSource.getBuffer(this.renderType(entityRenderer.getRenderer(entity).getTextureLocation(entity))), 226, pack(0, 10));
            }
            poseStack.popPose();

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

    private void renderCube(HologramProjectorBlockEntity blockEntity, Matrix4f pose, VertexConsumer consumer) {
        float f = this.getOffsetDown();
        float f1 = this.getOffsetUp();
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderFace(blockEntity, pose, consumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, f, f, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, f1, f1, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderFace(
            HologramProjectorBlockEntity blockEntity,
            Matrix4f pose,
            VertexConsumer consumer,
            float x0,
            float x1,
            float y0,
            float y1,
            float z0,
            float z1,
            float z2,
            float z3,
            Direction direction
    ) {
        if (true) {
            consumer.addVertex(pose, x0, y0, z0);
            consumer.addVertex(pose, x1, y0, z1);
            consumer.addVertex(pose, x1, y1, z2);
            consumer.addVertex(pose, x0, y1, z3);
        }
    }

    protected float getOffsetUp() {
        return 0.75F;
    }

    protected float getOffsetDown() {
        return 0.375F;
    }

    protected RenderType renderType(ResourceLocation location) {
        return ShadersSetup.hologram(location);
     //   return RenderType.endPortal();
    }

}
