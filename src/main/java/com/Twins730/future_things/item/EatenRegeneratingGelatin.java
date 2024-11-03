package com.Twins730.future_things.item;

import com.Twins730.future_things.setup.ItemSetup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EatenRegeneratingGelatin extends Item {

    private int regenCountdownTicks;

    public EatenRegeneratingGelatin(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        int maxCountDownTicks = 1600;
        if(regenCountdownTicks > maxCountDownTicks){
            this.regenCountdownTicks = 0;
            if (entity instanceof Player) {
                ((Player) entity).getInventory().items.set(slotId, ItemSetup.REGENERATING_GELATIN.get().getDefaultInstance());
            }
        }
        this.regenCountdownTicks++;
        super.inventoryTick(stack, level, entity, slotId, isSelected);

    }
}
