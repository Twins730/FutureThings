package com.Twins730.future_things.setup;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.function.Function;

public class ShadersSetup {

    @Nullable
    private static ShaderInstance entityHologramShader;

    public static final RenderStateShard.ShaderStateShard ENTITY_HOLOGRAM_SHADER = new RenderStateShard.ShaderStateShard(()-> entityHologramShader);

    public static final Function<ResourceLocation, RenderType> HOLOGRAM_SHADER = Util.memoize(
            p_286173_ -> {
                RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                        .setShaderState(ShadersSetup.ENTITY_HOLOGRAM_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(p_286173_, false, false))
                        .setLightmapState(RenderStateShard.LIGHTMAP)
                        .setOverlayState(RenderStateShard.OVERLAY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .createCompositeState(true);
                return RenderType.create("hologram_cutout", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, false, rendertype$compositestate);
            }
    );

    public static void registerShaders(RegisterShadersEvent event){
        try {
            event.registerShader(new ShaderInstance(event.getResourceProvider(), "rendertype_hologram_cutout", DefaultVertexFormat.NEW_ENTITY), shaderInstance -> entityHologramShader = shaderInstance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static RenderType hologram(ResourceLocation location) {
        return HOLOGRAM_SHADER.apply(location);
    }

}
