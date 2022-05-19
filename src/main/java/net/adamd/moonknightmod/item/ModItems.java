package net.adamd.moonknightmod.item;

import net.adamd.moonknightmod.MoonKnightMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, MoonKnightMod.MOD_ID);

  public static final RegistryObject<Item> CRESCENT_DART = ITEMS.register("crescent_dart",
      () -> new CrescentDartItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

  public static final RegistryObject<Item> KEVLAR_HELMET = ITEMS.register("kevlar_helmet",
      () -> new ModArmorItem(ModArmorMaterials.KEVLAR, EquipmentSlot.HEAD,
          new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

  public static final RegistryObject<Item> KEVLAR_CHESTPLATE = ITEMS.register("kevlar_chestplate",
      () -> new ArmorItem(ModArmorMaterials.KEVLAR, EquipmentSlot.CHEST,
          new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

  public static final RegistryObject<Item> KEVLAR_LEGGINGS = ITEMS.register("kevlar_leggings",
      () -> new ArmorItem(ModArmorMaterials.KEVLAR, EquipmentSlot.LEGS,
          new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

  public static final RegistryObject<Item> KEVLAR_BOOTS = ITEMS.register("kevlar_boots",
      () -> new ArmorItem(ModArmorMaterials.KEVLAR, EquipmentSlot.FEET,
          new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
