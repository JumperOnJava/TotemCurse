package io.github.jumperonjava.totemcurse;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mod(TotemCurse.MODID)
public class TotemCurse {
    public static final String MODID = "totemcurse";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TotemCurse.MODID);
    public static final RegistryObject<MobEffect> MORTALITY_CURSE = EFFECTS.register("mortality_curse", MortalityCurseEffect::new);
    public static int effectLength = 600;
    public static TotemCurse INSTANCE;

    public TotemCurse() {
        INSTANCE = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        EFFECTS.register(modEventBus);
        var cfgfile = FMLPaths.CONFIGDIR.get().resolve("TotemCooldown.json").toFile();
        var config = FileReadWrite.read(cfgfile);
        if(config.equals("")){
            FileReadWrite.write(cfgfile, String.valueOf(effectLength));
            config = String.valueOf(effectLength);
        }
        effectLength = Integer.parseInt(config);
        LOGGER.info("Loaded totem curse mod");
    }

    public static void cancelIfCurse(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if(target.hasEffect(MORTALITY_CURSE.get()))
            cir.setReturnValue(true);
    }

    public static void addCurse(LivingEntity player) {
        player.addEffect(new MobEffectInstance(TotemCurse.MORTALITY_CURSE.get(), TotemCurse.effectLength));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
