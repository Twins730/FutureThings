package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class ShadersSetup {

    public static final RenderStateShard.ShaderStateShard ENTITY_HOLOGRAM_SHADER = new RenderStateShard.ShaderStateShard(FutureThings.ClientEvents::getEntityHologramShader);

    public static final Function<ResourceLocation, RenderType> HOLOGRAM_SHADER = Util.memoize(
            ((location) -> {
                RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                        .setShaderState(ShadersSetup.ENTITY_HOLOGRAM_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(location, false, false))
                        .setLightmapState(RenderStateShard.LIGHTMAP)
                        .setOverlayState(RenderStateShard.OVERLAY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .createCompositeState(false);
                return RenderType.create("future_things_hologram", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, false, rendertype$compositestate);
            }
    ));

    public static RenderType hologram(ResourceLocation location) {
        return HOLOGRAM_SHADER.apply(location);
    }

}
