package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemSetup {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FutureThings.MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("future_things", () ->
            CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() ->
                    ItemSetup.EXAMPLE_ITEM.get().getDefaultInstance()).displayItems(ItemSetup::addItems).build());

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FutureThings.MODID);

    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(BlockSetup.EXAMPLE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()/*.food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build())*/));


    public static void addItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(ItemSetup.EXAMPLE_ITEM.get());
        output.accept(ItemSetup.EXAMPLE_BLOCK_ITEM.get());
    }
}
