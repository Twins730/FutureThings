package com.Twins730.future_things;

import com.Twins730.future_things.setup.BlockSetup;
import com.Twins730.future_things.setup.ItemSetup;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

@Mod(FutureThings.MODID)
public class FutureThings {

    public static final String MODID = "future_things";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FutureThings(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        BlockSetup.BLOCKS.register(modEventBus);
        ItemSetup.ITEMS.register(modEventBus);
        ItemSetup.CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        FutureThings.LOGGER.info("Future things has been setup successfully.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(ItemSetup.EXAMPLE_BLOCK_ITEM);
    }
}
