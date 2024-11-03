package com.Twins730.future_things.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class PolymerWindow extends HorizontalConnectedBlock{

    public PolymerWindow(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        boolean north = level.getBlockState(pos.north()).getBlock() instanceof PolymerWindow;
        boolean east = level.getBlockState(pos.east()).getBlock() instanceof PolymerWindow;
        boolean south = level.getBlockState(pos.south()).getBlock() instanceof PolymerWindow;
        boolean west = level.getBlockState(pos.west()).getBlock() instanceof PolymerWindow;

        return getState(north, south, east, west, state);
    }
}
