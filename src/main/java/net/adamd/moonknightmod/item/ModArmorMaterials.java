package net.adamd.moonknightmod.item;

import java.util.function.Supplier;
import net.adamd.moonknightmod.MoonKnightMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {
  KEVLAR("kevlar", 37, new int[]{10, 10, 10, 10}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 10.0F, 0.2F,
      () -> Ingredient.of(Items.NETHERITE_INGOT)); // change to kevlar material

  private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
  private final String name;
  private final int durabilityMultiplier;
  private final int[] slotProtections;
  private final int enchantmentValue;
  private final SoundEvent sound;
  private final float toughness;
  private final float knockbackResistance;
  private final LazyLoadedValue<Ingredient> repairIngredient;

  ModArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections,
      int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance,
      Supplier<Ingredient> repairIngredient) {
    this.name = name;
    this.durabilityMultiplier = durabilityMultiplier;
    this.slotProtections = slotProtections;
    this.enchantmentValue = enchantmentValue;
    this.sound = sound;
    this.toughness = toughness;
    this.knockbackResistance = knockbackResistance;
    this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
  }

  public int getDurabilityForSlot(EquipmentSlot pSlot) {
    return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
  }

  public int getDefenseForSlot(EquipmentSlot pSlot) {
    return this.slotProtections[pSlot.getIndex()];
  }

  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  public SoundEvent getEquipSound() {
    return this.sound;
  }

  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
  }

  public String getName() {
    return MoonKnightMod.MOD_ID + ":" + this.name;
  }

  public float getToughness() {
    return this.toughness;
  }

  /**
   * Gets the percentage of knockback resistance provided by armor of the material.
   */
  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }
}
