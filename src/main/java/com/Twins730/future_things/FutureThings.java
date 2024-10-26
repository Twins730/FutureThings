package com.Twins730.future_things;

import com.Twins730.future_things.setup.BlockEntitySetup;
import com.Twins730.future_things.setup.BlockSetup;
import com.Twins730.future_things.setup.ItemSetup;

import com.Twins730.future_things.setup.ShadersSetup;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.logging.LogUtils;

import net.minecraft.Util;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
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

    // Mod container
    public FutureThings(IEventBus modEventBus, ModContainer container) {
        BlockSetup.BLOCKS.register(modEventBus);
        ItemSetup.ITEMS.register(modEventBus);
        ItemSetup.CREATIVE_MODE_TABS.register(modEventBus);
        BlockEntitySetup.BLOCK_ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(BlockEntitySetup::registerEntityRenderers);

        //NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        FutureThings.LOGGER.info("Future things has been setup successfully.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(ItemSetup.HOLOGRAM_PROJECTOR_ITEM.get());
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
