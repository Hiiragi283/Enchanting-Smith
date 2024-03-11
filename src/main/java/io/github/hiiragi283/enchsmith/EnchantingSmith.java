package io.github.hiiragi283.enchsmith;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class EnchantingSmith implements ModInitializer {
    @NotNull
    public static final String MOD_ID = "enchanting_smith";
    @NotNull
    public static final String MOD_NAME = "Enchanting Smith";
    @NotNull
    private static final Logger logger = LogManager.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "upgrade"), UpgradeSmithingRecipe.Serializer.INSTANCE);
        logger.info("[{}] Initialized!", MOD_NAME);
    }
}