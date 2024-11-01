package com.Twins730.future_things.setup;

import com.Twins730.future_things.FutureThings;
import com.Twins730.future_things.menu.HologramMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuSetup {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, FutureThings.MOD_ID);


    public static final Supplier<MenuType<HologramMenu>> HOLOGRAM_MENU = MENUS.register("hologram_menu", () -> new MenuType(HologramMenu::new, FeatureFlags.DEFAULT_FLAGS));


}
