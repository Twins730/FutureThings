package com.Twins730.future_things.block.blockentity;

import com.Twins730.future_things.menu.HologramMenu;
import com.Twins730.future_things.setup.BlockEntitySetup;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class HologramProjectorBlockEntity extends BlockEntity implements Container, MenuProvider, Nameable {

    @Nullable
    private Component name;
    public static final int SIZE = 1;
    private final NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    public HologramProjectorBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntitySetup.HOLOGRAM_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("CustomName", 8)) {
            this.name = parseCustomNameSafe(tag.getString("CustomName"), registries);
        }
        ContainerHelper.loadAllItems(tag, items, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (this.name != null) {
            tag.putString("CustomName", Component.Serializer.toJson(this.name, registries));
        }
        ContainerHelper.saveAllItems(tag, items, registries);
    }

    @Override
    public Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    private Component getDefaultName() {
        return Component.translatable("container.future_things.hologram_block_entity");
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @javax.annotation.Nullable
    @Override
    public Component getCustomName() {
        return this.name;
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.getItems()) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true, registries);
        return compoundtag;
    }

    private NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.getItems().get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), slot, amount);
        if (!itemstack.isEmpty()) {
            this.markUpdated();
        }

        return itemstack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stack = ContainerHelper.takeItem(this.getItems(), slot);
        this.markUpdated();
        return stack;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.getItems().set(slot, stack);
        stack.limitSize(this.getMaxStackSize(stack));
        this.markUpdated();
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.getItems().clear();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new HologramMenu(containerId, playerInventory, this);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.name = componentInput.get(DataComponents.CUSTOM_NAME);
        componentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getItems());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CUSTOM_NAME, this.name);
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("CustomName");
        tag.remove("Lock");
        tag.remove("Items");
    }
}
