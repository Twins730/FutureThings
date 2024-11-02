package com.Twins730.future_things;

import ca.weblite.objc.Client;
import com.Twins730.future_things.menu.HologramScreen;
import com.Twins730.future_things.setup.*;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

@Mod(FutureThings.MOD_ID)
public class FutureThings {

    public static final String MOD_ID = "future_things";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static float PI_TIME = 0;

    // Mod container
    public FutureThings(IEventBus modEventBus, ModContainer container) {
        BlockSetup.BLOCKS.register(modEventBus);
        ItemSetup.ITEMS.register(modEventBus);
        ItemSetup.CREATIVE_MODE_TABS.register(modEventBus);
        BlockEntitySetup.BLOCK_ENTITY_TYPES.register(modEventBus);
        MenuSetup.MENUS.register(modEventBus);
        modEventBus.addListener(BlockEntitySetup::registerEntityRenderers);

        //NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerScreens);

        NeoForge.EVENT_BUS.addListener(this::clientTick);


        FutureThings.LOGGER.info("Future things has been setup successfully.");
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MenuSetup.HOLOGRAM_MENU.get(), HologramScreen::new);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(ItemSetup.HOLOGRAM_PROJECTOR_ITEM.get());
    }

    public void clientTick(ClientTickEvent.Post event){
            FutureThings.PI_TIME += 0.1f;
            if (PI_TIME > Math.PI * 2) {
                FutureThings.PI_TIME = 0;
            }
    }

    @EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientEvents {
        @Nullable
        private static ShaderInstance entityHologramShader;

        public static ShaderInstance getEntityHologramShader() {
            return Objects.requireNonNull(entityHologramShader, "getEntityHologramShader() was null!");
        }

        @SubscribeEvent
        public static void registerShaders(RegisterShadersEvent event) throws IOException {
            event.registerShader(new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath(MOD_ID, "rendertype_hologram_cutout"), DefaultVertexFormat.NEW_ENTITY), (p_172645_) -> {
                entityHologramShader = p_172645_;
            });
        }


    }


}
