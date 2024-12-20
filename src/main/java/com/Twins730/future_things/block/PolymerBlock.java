package com.Twins730.future_things.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class PolymerBlock extends HorizontalConnectedBlock {
    public PolymerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        boolean north = level.getBlockState(pos.north()).getBlock() instanceof PolymerBlock;
        boolean east = level.getBlockState(pos.east()).getBlock() instanceof PolymerBlock;
        boolean south = level.getBlockState(pos.south()).getBlock() instanceof PolymerBlock;
        boolean west = level.getBlockState(pos.west()).getBlock() instanceof PolymerBlock;

        return getState(north, south, east, west, state);
    }
}
