package com.dannbrown.creativeenderchests.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderChestBlock.class)
public class EnderChestBlockMixin {
  @Inject(method = "use", at = @At("HEAD"), cancellable = true)
  private void cancelUse(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
    // if the player is in creative mode, cancel the use of the ender chest and notify the player
    if(player.isCreative()) {
      player.displayClientMessage(Component.translatable("block.minecraft.ender_chest.creative_use"), true);
      cir.setReturnValue(InteractionResult.SUCCESS);
    }
  }
}
