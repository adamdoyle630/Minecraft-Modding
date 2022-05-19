package net.adamd.moonknightmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
  public static final CreativeModeTab MOD_TAB = new CreativeModeTab("moonknighttab") {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ModItems.CRESCENT_DART.get());
    }
  };
}
