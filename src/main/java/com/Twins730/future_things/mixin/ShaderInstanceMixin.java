package com.Twins730.future_things.mixin;

import com.Twins730.future_things.FutureThings;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.shaders.Shader;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ShaderInstance.class)
public abstract class ShaderInstanceMixin implements Shader, AutoCloseable {

    @Shadow @Nullable public abstract Uniform getUniform(String name);

    @Unique
    public Uniform futureThings$TIME;

    @Unique
    private float futureThings$tick;

    @Inject(method = "<init>(Lnet/minecraft/server/packs/resources/ResourceProvider;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/VertexFormat;)V", at = @At("TAIL"))
    private void init(ResourceProvider p_173336_, ResourceLocation shaderLocation, VertexFormat p_173338_, CallbackInfo ci){
        this.futureThings$TIME = this.getUniform("Time");
   }

    @Inject(method = "setDefaultUniforms", at = @At("TAIL"))
    private void setDefaultUniforms(CallbackInfo ci){
        if(futureThings$TIME!=null) {
            this.futureThings$TIME.set(FutureThings.PI_TIME);
        }
    }



}
