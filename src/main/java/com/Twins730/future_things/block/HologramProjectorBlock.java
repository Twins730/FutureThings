package com.Twins730.future_things.block;

import com.Twins730.future_things.block.blockentity.HologramProjectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HologramProjectorBlock extends Block implements EntityBlock {

    public HologramProjectorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HologramProjectorBlockEntity(pos, state);
    }
}
