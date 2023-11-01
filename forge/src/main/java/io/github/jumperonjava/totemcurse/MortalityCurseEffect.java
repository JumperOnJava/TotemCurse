package io.github.jumperonjava.totemcurse;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MortalityCurseEffect extends MobEffect {
    protected MortalityCurseEffect() {
        super(MobEffectCategory.HARMFUL, 0xFFFC0A3B);
    }
}
