package com.Twins730.future_things;

import com.Twins730.future_things.setup.BlockSetup;
import com.Twins730.future_things.setup.ItemSetup;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(FutureThings.MOD_ID)
public class FutureThings {

    public static final String MOD_ID = "future_things";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Mod container
    public FutureThings(IEventBus modEventBus, ModContainer container) {
        BlockSetup.BLOCKS.register(modEventBus);
        ItemSetup.ITEMS.register(modEventBus);
        ItemSetup.CREATIVE_MODE_TABS.register(modEventBus);

       // NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        FutureThings.LOGGER.info("Future things has been setup successfully.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(ItemSetup.EXAMPLE_BLOCK_ITEM.get());
    }




}
