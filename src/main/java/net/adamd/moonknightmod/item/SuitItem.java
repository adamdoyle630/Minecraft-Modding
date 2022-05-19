package net.adamd.moonknightmod.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class SuitItem extends Item {

  public SuitItem(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public InteractionResult useOn(UseOnContext pContext) {
    if (pContext.getLevel().isClientSide()) {
      Player player = pContext.getPlayer();
    }
    return super.useOn(pContext);
  }

}
