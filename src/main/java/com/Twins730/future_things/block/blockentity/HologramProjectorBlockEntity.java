package com.Twins730.future_things.block.blockentity;

import com.Twins730.future_things.setup.BlockEntitySetup;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HologramProjectorBlockEntity extends BlockEntity {

    public HologramProjectorBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntitySetup.HOLOGRAM_BLOCK_ENTITY.get(), pos, blockState);
    }
}
