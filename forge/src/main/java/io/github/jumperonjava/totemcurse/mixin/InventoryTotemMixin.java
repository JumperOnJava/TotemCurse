package io.github.jumperonjava.totemcurse.mixin;

import com.natamus.inventorytotem_common_forge.events.TotemEvent;
import io.github.jumperonjava.totemcurse.TotemCurse;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TotemEvent.class)
public class InventoryTotemMixin{
    static {
        TotemCurse.LOGGER.info("Mixin static is loaded");
    }
    @Inject(method = "allowPlayerDeath", cancellable = true, at = @At(value = "INVOKE",shift = At.Shift.BEFORE,target = "Lnet/minecraft/server/level/ServerPlayer;setHealth(F)V"))
    private static void cancelIfCurse(ServerLevel world, ServerPlayer player, CallbackInfoReturnable<Boolean> cir){
        TotemCurse.cancelIfCurse(player,cir);
    }
    @Inject(method = "allowPlayerDeath", at=@At(shift = At.Shift.AFTER,value = "INVOKE",target = "Lnet/minecraft/server/level/ServerPlayer;removeAllEffects()Z"))
    private static void addCurse(ServerLevel world, ServerPlayer player, CallbackInfoReturnable<Boolean> cir){
        TotemCurse.addCurse(player);
    }
}