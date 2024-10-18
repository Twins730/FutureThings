package com.Twins730.future_things.mixin;

import com.Twins730.future_things.mixinInterface.IEntityHologram;

import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public class EntityMixin implements IEntityHologram {

    @Unique
    private boolean futureThings$isHologram;

    @Override
    public boolean futureThings$isHologram() {
        return futureThings$isHologram;
    }

    @Override
    public void futureThings$setHologram(boolean hologram) {
        this.futureThings$isHologram = hologram;
    }
}
