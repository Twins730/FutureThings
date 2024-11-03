package com.Twins730.future_things.setup;


import com.Twins730.future_things.FutureThings;
import com.Twins730.future_things.item.BioChipRecord;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentSetup {

    public static final Codec<BioChipRecord> BIO_CHIP_RECORD_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.STRING.fieldOf("entity_type")
                    .forGetter(BioChipRecord::entity_type)).apply(instance, BioChipRecord::new));

    public static final StreamCodec<ByteBuf, BioChipRecord> BIO_CHIP_RECORD_STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, BioChipRecord::entity_type, BioChipRecord::new);

     public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, FutureThings.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BioChipRecord>> BIO_CHIP_DATA = DATA_COMPONENTS.registerComponentType("bio_chip_data", builder ->
            builder.persistent(BIO_CHIP_RECORD_CODEC).networkSynchronized(BIO_CHIP_RECORD_STREAM_CODEC));

}
