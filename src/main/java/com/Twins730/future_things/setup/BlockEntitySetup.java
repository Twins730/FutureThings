package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;
import com.Twins730.future_things.block.blockentity.HologramProjectorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntitySetup {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FutureThings.MOD_ID);

    public static final Supplier<BlockEntityType<HologramProjectorBlockEntity>> HOLOGRAM_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("my_block_entity", () ->
            BlockEntityType.Builder.of(HologramProjectorBlockEntity::new, BlockSetup.EXAMPLE_BLOCK.get()).build(null));

}
