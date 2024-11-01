package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;
import com.Twins730.future_things.block.blockentity.HologramProjectorBlockEntity;
import com.Twins730.future_things.client.blockentityrenderer.HologramProjectorBlockEntityRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntitySetup {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FutureThings.MOD_ID);

    public static final Supplier<BlockEntityType<HologramProjectorBlockEntity>> HOLOGRAM_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("hologram_block_entity", () ->
            BlockEntityType.Builder.of(HologramProjectorBlockEntity::new, BlockSetup.HOLOGRAM_PROJECTOR.get()).build(null));

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntitySetup.HOLOGRAM_BLOCK_ENTITY.get(), HologramProjectorBlockEntityRenderer::new);
    }

}
