package com.Twins730.future_things.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BioChipItem extends Item {

    private EntityType<?> entity;

    public BioChipItem(Properties properties) {
        super(properties);
    }


    public EntityType<?> getEntity(){
        if(entity == null){
            return EntityType.ALLAY;
        }
        return entity;
    }

}
