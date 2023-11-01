package io.github.jumperonjava.totemcurse.mixin;

import net.minecraft.client.gui.screens.TitleScreen;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "init",at = @At("HEAD"))
    void init(CallbackInfo ci){
        LOGGER.info("LOADED TITLE SCREEN MIXIN");
    }
}
