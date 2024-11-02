package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;

import com.Twins730.future_things.block.HologramProjectorBlock;
import com.Twins730.future_things.block.PolymerBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BlockSetup {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, FutureThings.MOD_ID);

    public static final Supplier<Block> HOLOGRAM_PROJECTOR = BLOCKS.register("hologram_projector", () -> new HologramProjectorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noOcclusion()));
    public static final Supplier<Block> POLYMER_BLOCK = BLOCKS.register("polymer_block", () -> new PolymerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final Supplier<Block> POLYMER_FLOOR = BLOCKS.register("polymer_floor", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

}
