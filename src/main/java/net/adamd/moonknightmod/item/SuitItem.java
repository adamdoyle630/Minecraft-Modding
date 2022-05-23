package net.adamd.moonknightmod.item;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

public class SuitItem extends Item {

  private static SuitItem suitItem = null;

  public static SuitItem get() {
    if (suitItem == null) {
      suitItem = new SuitItem(new Item.Properties().stacksTo(1));
    }
    return suitItem;
  }

  private SuitItem(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public @NotNull InteractionResult useOn(UseOnContext pContext) {
    if (pContext.getLevel().isClientSide()) {
      Player player = pContext.getPlayer();
    }
    return super.useOn(pContext);
  }

  public static boolean isDarkEnoughToUse(
      ServerLevelAccessor pLevel, BlockPos pPos, Random pRandom) {
    if (pLevel.getBrightness(LightLayer.SKY, pPos) > pRandom.nextInt(32)) {
      return false;
    } else {
      return pLevel.getBrightness(LightLayer.BLOCK, pPos) <= 0;
    }
  }
}
