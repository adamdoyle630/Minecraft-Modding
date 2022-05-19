package net.adamd.moonknightmod.item;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ModArmorItem extends ArmorItem {

  private static final List<MobEffectInstance> MOB_EFFECTS = new ArrayList<>() {{
    add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 8400, 3));
    add(new MobEffectInstance(MobEffects.REGENERATION, 8400, 3));
    add(new MobEffectInstance(MobEffects.ABSORPTION, 8400, 3));
    add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 8400, 3));
    add(new MobEffectInstance(MobEffects.NIGHT_VISION, 8400, 0));
  }};

  private static final Map<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
      (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
          .put(ModArmorMaterials.KEVLAR,
              MOB_EFFECTS).build();

  public ModArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
    super(material, slot, settings);
  }

  @Override
  public void onArmorTick(ItemStack stack, Level world, Player player) {
    if (!world.isClientSide()) {
      if (hasFullSuitOfArmorOn(player)) {
        evaluateArmorEffects(player);
      }
    }
  }

  private void evaluateArmorEffects(Player player) {
    for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
      ArmorMaterial mapArmorMaterial = entry.getKey();
      List<MobEffectInstance> mapStatusEffects = entry.getValue();

      if (hasCorrectArmorOn(mapArmorMaterial, player)) {
        addStatusEffectsForMaterial(player, mapArmorMaterial, mapStatusEffects);
      }
    }
  }

  private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
      MobEffectInstance mapStatusEffect) {
    boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

    if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
      player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
          mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

      //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
      //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
      //}
    }
  }

  private void addStatusEffectsForMaterial(Player player, ArmorMaterial mapArmorMaterial,
      List<MobEffectInstance> mapStatusEffects) {
    for (MobEffectInstance mobEffect : mapStatusEffects) {
      boolean hasPlayerEffect = player.hasEffect(mobEffect.getEffect());

      if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
        player.addEffect(new MobEffectInstance(mobEffect.getEffect(), mobEffect.getDuration(),
            mobEffect.getAmplifier()));
      }
    }
  }

  private boolean hasFullSuitOfArmorOn(Player player) {
    ItemStack boots = player.getInventory().getArmor(0);
    ItemStack leggings = player.getInventory().getArmor(1);
    ItemStack breastplate = player.getInventory().getArmor(2);
    ItemStack helmet = player.getInventory().getArmor(3);

    return !helmet.isEmpty() && !breastplate.isEmpty()
        && !leggings.isEmpty() && !boots.isEmpty();
  }

  private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
    for (ItemStack armorStack : player.getInventory().armor) {
      if (!(armorStack.getItem() instanceof ArmorItem)) {
        return false;
      }
    }

    ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
    ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
    ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
    ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

    return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
        leggings.getMaterial() == material && boots.getMaterial() == material;
  }
}
