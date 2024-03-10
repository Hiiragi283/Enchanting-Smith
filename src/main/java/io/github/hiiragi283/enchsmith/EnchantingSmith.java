package io.github.hiiragi283.enchsmith;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(EnchantingSmith.MOD_ID)
public final class EnchantingSmith {

    @NotNull
    public static final String MOD_ID = "enchanting_smith";
    @NotNull
    public static final String MOD_NAME = "Enchanting Smith";
    @NotNull
    private static final Logger logger = LogManager.getLogger(MOD_NAME);

    public EnchantingSmith() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(IRecipeSerializer.class, this::onRegister);
    }

    private void onRegister(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().register(UpgradeSmithingRecipe.Serializer.INSTANCE);
        logger.info("[{}] Initialized!", MOD_NAME);
    }
}