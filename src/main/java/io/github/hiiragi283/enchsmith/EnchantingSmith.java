package io.github.hiiragi283.enchsmith;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class EnchantingSmith implements ModInitializer {
    @NotNull
    public static final String MOD_ID = "enchanting_smith";
    @NotNull
    public static final String MOD_NAME = "Enchanting Smith";

    @NotNull
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    @NotNull
    private static final Logger logger = LogManager.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "upgrade"), SmithingUpgradeRecipe.Serializer.INSTANCE);
        logger.info("[{}] Initialized!", MOD_NAME);
    }
}