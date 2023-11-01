package io.github.jumperonjava.totemcurse.mixin;

import io.github.jumperonjava.totemcurse.TotemCurse;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class TotemMixin {
    @Inject(method = "checkTotemDeathProtection", cancellable = true, at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V"))
    private void cancelIfCurse(DamageSource arg, CallbackInfoReturnable<Boolean> cir) {
        TotemCurse.cancelIfCurse((LivingEntity)(Object)this, cir);
    }

    @Inject(method = "checkTotemDeathProtection", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"))
    private void addCurse(DamageSource arg, CallbackInfoReturnable<Boolean> cir) {
        TotemCurse.addCurse((LivingEntity)(Object)this);
    }
}
