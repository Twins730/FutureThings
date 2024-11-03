package com.Twins730.future_things.item;

import com.Twins730.future_things.setup.DataComponentSetup;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BioChipItem extends Item {

    public BioChipItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.get(DataComponentSetup.BIO_CHIP_DATA) != null) {
            tooltipComponents.add(Component.translatable("future_things.bio_chip.stored_bio").append(Component.translatable(stack.get(DataComponentSetup.BIO_CHIP_DATA).entity_type())));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public String getEntityID(ItemStack stack){
        if(stack.get(DataComponentSetup.BIO_CHIP_DATA) != null){
            return stack.get(DataComponentSetup.BIO_CHIP_DATA).entity_type();
        }
        return "";
    }

}
