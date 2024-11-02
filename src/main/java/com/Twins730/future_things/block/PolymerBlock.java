package com.Twins730.future_things.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class PolymerBlock extends Block {

    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

    public PolymerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, Type.NONE));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        boolean north = level.getBlockState(pos.north()).getBlock() instanceof PolymerBlock;
        boolean east = level.getBlockState(pos.east()).getBlock() instanceof PolymerBlock;
        boolean south = level.getBlockState(pos.south()).getBlock() instanceof PolymerBlock ;
        boolean west = level.getBlockState(pos.west()).getBlock() instanceof PolymerBlock;

        //Thanks to MrCrayFish for figuring these out for me.
        if (!north && !east && !south && !west) {
            return state.setValue(TYPE, Type.NONE);
        }
        if (!north && !east && south && !west) {
            return state.setValue(TYPE, Type.NORTH);
        }
        if (north && !east && !south && !west) {
            return state.setValue(TYPE, Type.SOUTH);
        }
        if (!north && !east && !south && west) {
            return state.setValue(TYPE, Type.EAST);
        }
        if (!north && east && !south && !west) {
            return state.setValue(TYPE, Type.WEST);
        }
        if (north && !east && south && !west) {
            return state.setValue(TYPE, Type.HORIZONTAL_NORTH_SOUTH);
        }
        if (!north && east && !south && west) {
            return state.setValue(TYPE, Type.HORIZONTAL_EAST_WEST);
        }
        if (!north && east && south && !west) {
            return state.setValue(TYPE, Type.CORNER_EAST_SOUTH);
        }
        if (!north && !east && south && west) {
            return state.setValue(TYPE, Type.CORNER_WEST_SOUTH);
        }
        if (north && east && !south && !west) {
            return state.setValue(TYPE, Type.CORNER_NORTH_EAST);
        }
        if (north && !east && !south && west) {
            return state.setValue(TYPE, Type.CORNER_NORTH_WEST);
        }
        if (!north && east && south && west) {
            return state.setValue(TYPE, Type.THREE_NORTH);
        }
        if (north && !east && south && west) {
            return state.setValue(TYPE, Type.THREE_EAST);
        }
        if (north && east && !south && west) {
            return state.setValue(TYPE, Type.THREE_SOUTH);
        }
        if (north && east && south && !west) {
            return state.setValue(TYPE, Type.THREE_WEST);
        }
        return state.setValue(TYPE, Type.CENTER);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TYPE, Type.NONE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    public enum Type implements StringRepresentable {

        NONE("none"),
        NORTH("north"),
        EAST("east"),
        SOUTH("south"),
        WEST("west"),
        HORIZONTAL_EAST_WEST("horizontal_east_west"),
        HORIZONTAL_NORTH_SOUTH("horizontal_north_south"),
        CORNER_EAST_SOUTH("corner_east_south"),
        CORNER_WEST_SOUTH("corner_west_south"),
        CORNER_NORTH_EAST("corner_north_east"),
        CORNER_NORTH_WEST("corner_north_west"),
        THREE_NORTH("three_north"),
        THREE_EAST("three_east"),
        THREE_SOUTH("three_south"),
        THREE_WEST("three_west"),
        CENTER("center");

        private final String name;

        private Type(String p_i49335_3_) {
            this.name = p_i49335_3_;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }
    }
}
