package io.github.jumperonjava.totemcurse;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class EmptyIterable implements Iterable{
        @NotNull
        @Override
        public Iterator<ItemStack> iterator() {
            return null;
        }
}
