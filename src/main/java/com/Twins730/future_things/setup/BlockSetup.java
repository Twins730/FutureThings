package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;

import com.Twins730.future_things.block.HologramProjectorBlock;
import com.Twins730.future_things.block.HorizontalConnectedBlock;
import com.Twins730.future_things.block.PolymerBlock;
import com.Twins730.future_things.block.PolymerWindow;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BlockSetup {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, FutureThings.MOD_ID);

    public static final Supplier<Block> HOLOGRAM_PROJECTOR = BLOCKS.register("hologram_projector", () -> new HologramProjectorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final Supplier<Block> POLYMER_BLOCK = BLOCKS.register("polymer_block", () -> new PolymerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> POLYMER_FLOOR = BLOCKS.register("polymer_floor", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> POLYMER_WINDOW = BLOCKS.register("polymer_window", () -> new PolymerWindow(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).noOcclusion()));
    public static final Supplier<Block> POLYMER_LAMP = BLOCKS.register("polymer_lamp", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).noOcclusion().emissiveRendering((state, level, pos) -> true)));


}
