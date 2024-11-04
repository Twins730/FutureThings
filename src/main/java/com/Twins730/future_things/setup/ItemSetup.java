package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;
import com.Twins730.future_things.item.BioChipItem;
import com.Twins730.future_things.item.BioChipRecord;
import com.Twins730.future_things.item.EatenRegeneratingGelatin;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemSetup {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FutureThings.MOD_ID);

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("future_things", () ->
            CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() ->
                    ItemSetup.REGENERATING_GELATIN.get().getDefaultInstance()).displayItems(ItemSetup::addItems).build());

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, FutureThings.MOD_ID);

    public static final Supplier<Item> HOLOGRAM_PROJECTOR_ITEM = ITEMS.register("hologram_projector", () ->
            new BlockItem(BlockSetup.HOLOGRAM_PROJECTOR.get(), new Item.Properties()));

    public static final Supplier<Item> POLYMER_BLOCK_ITEM = ITEMS.register("polymer_block", () ->
            new BlockItem(BlockSetup.POLYMER_BLOCK.get(), new Item.Properties()));

    public static final Supplier<Item> POLYMER_WINDOW_ITEM = ITEMS.register("polymer_window", () ->
            new BlockItem(BlockSetup.POLYMER_WINDOW.get(), new Item.Properties()));

    public static final Supplier<Item> POLYMER_FLOOR_ITEM = ITEMS.register("polymer_floor", () ->
            new BlockItem(BlockSetup.POLYMER_FLOOR.get(), new Item.Properties()));

    public static final Supplier<Item> POLYMER_LAMP_ITEM = ITEMS.register("polymer_lamp", () ->
            new BlockItem(BlockSetup.POLYMER_LAMP.get(), new Item.Properties()));

    public static final Supplier<Item> EATEN_REGENERATING_GELATIN = ITEMS.register("eaten_regenerating_gelatin", () ->
            new EatenRegeneratingGelatin(new Item.Properties()));

    public static final Supplier<Item> REGENERATING_GELATIN = ITEMS.register("regenerating_gelatin", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(4).saturationModifier(2f).usingConvertsTo(EATEN_REGENERATING_GELATIN.get()).build()).stacksTo(1)));

    public static final Supplier<BioChipItem> BIO_CHIP = ITEMS.register("bio_chip", () ->
            new BioChipItem(new BioChipItem.Properties().component(DataComponentSetup.BIO_CHIP_DATA.value(), new BioChipRecord("")).stacksTo(1)));

    public static final Supplier<Item> POLYMER_BRICK = ITEMS.register("polymer_brick", () ->
            new Item(new Item.Properties()));

    public static void addItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(ItemSetup.POLYMER_BLOCK_ITEM.get());
        output.accept(ItemSetup.POLYMER_WINDOW_ITEM.get());
        output.accept(ItemSetup.POLYMER_FLOOR_ITEM.get());
        output.accept(ItemSetup.POLYMER_LAMP_ITEM.get());
        output.accept(ItemSetup.HOLOGRAM_PROJECTOR_ITEM.get());
        output.accept(ItemSetup.REGENERATING_GELATIN.get());
        output.accept(ItemSetup.POLYMER_BRICK.get());
        output.accept(ItemSetup.BIO_CHIP.get());
    }
}
