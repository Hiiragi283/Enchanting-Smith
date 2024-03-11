package io.github.hiiragi283.enchsmith.datagen;

import io.github.hiiragi283.enchsmith.EnchantingSmith;
import io.github.hiiragi283.enchsmith.SmithingUpgradeRecipeJsonBuilder;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Consumer;

public final class EnchantingSmithDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(Recipe::new);
    }

    public static final class Recipe extends FabricRecipeProvider {
        public Recipe(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> exporter) {
            // Protection
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.IRON_BLOCK,
                    Enchantments.PROTECTION,
                    EnchantingSmith.id("smithing/protection"),
                    exporter
            );
            // Fire Protection
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.NETHER_BRICKS,
                    Enchantments.FIRE_PROTECTION,
                    EnchantingSmith.id("smithing/fire_protection"),
                    exporter
            );
            // Feather Falling
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.FEATHER,
                    Enchantments.FEATHER_FALLING,
                    EnchantingSmith.id("smithing/feather_falling"),
                    exporter
            );
            // Blast Falling
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.OBSIDIAN,
                    Enchantments.BLAST_PROTECTION,
                    EnchantingSmith.id("smithing/blast_falling"),
                    exporter
            );
            // Projectile Falling
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.SHIELD,
                    Enchantments.PROJECTILE_PROTECTION,
                    EnchantingSmith.id("smithing/projectile_falling"),
                    exporter
            );
            // Aqua Affinity
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.HEART_OF_THE_SEA,
                    Enchantments.AQUA_AFFINITY,
                    EnchantingSmith.id("smithing/aqua_affinity"),
                    exporter
            );
            // Thorns
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.CACTUS,
                    Enchantments.THORNS,
                    EnchantingSmith.id("smithing/thorns"),
                    exporter
            );
            // Depth Strider
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.NAUTILUS_SHELL,
                    Enchantments.DEPTH_STRIDER,
                    EnchantingSmith.id("smithing/depth_strider"),
                    exporter
            );
            // Frost Walker
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.BLUE_ICE,
                    Enchantments.FROST_WALKER,
                    EnchantingSmith.id("smithing/frost_walker"),
                    exporter
            );
            // Soul Speed
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.SOUL_SOIL,
                    Enchantments.SOUL_SPEED,
                    EnchantingSmith.id("smithing/soul_speed"),
                    exporter
            );
            // Swift Sneak
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.SUGAR,
                    Enchantments.SWIFT_SNEAK,
                    EnchantingSmith.id("smithing/swift_sneak"),
                    exporter
            );

            // Sharpness
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.QUARTZ_BLOCK,
                    Enchantments.SHARPNESS,
                    EnchantingSmith.id("smithing/sharpness"),
                    exporter
            );
            // Smite
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.GLOWSTONE,
                    Enchantments.SMITE,
                    EnchantingSmith.id("smithing/smite"),
                    exporter
            );
            // Bane of arthropods
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.PUFFERFISH,
                    Enchantments.BANE_OF_ARTHROPODS,
                    EnchantingSmith.id("smithing/bane_of_arthropods"),
                    exporter
            );
            // Knockback
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.PISTON,
                    Enchantments.KNOCKBACK,
                    EnchantingSmith.id("smithing/knockback"),
                    exporter
            );
            // Fire Aspect
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.FLINT_AND_STEEL,
                    Enchantments.FIRE_ASPECT,
                    EnchantingSmith.id("smithing/fire_aspect"),
                    exporter
            );
            // Looting
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.LAPIS_BLOCK,
                    Enchantments.LOOTING,
                    EnchantingSmith.id("smithing/looting"),
                    exporter
            );
            // Sweeping
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    ItemTags.SIGNS,
                    Enchantments.SWEEPING,
                    EnchantingSmith.id("smithing/sweeping"),
                    exporter
            );

            // Efficiency
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.REDSTONE_BLOCK,
                    Enchantments.EFFICIENCY,
                    EnchantingSmith.id("smithing/efficiency"),
                    exporter
            );
            // Silk Touch
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.PHANTOM_MEMBRANE,
                    Enchantments.SILK_TOUCH,
                    EnchantingSmith.id("smithing/silk_touch"),
                    exporter
            );
            // Unbreaking
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.DIAMOND,
                    Enchantments.UNBREAKING,
                    EnchantingSmith.id("smithing/unbreaking"),
                    exporter
            );
            // Fortune
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.LAPIS_BLOCK,
                    Enchantments.FORTUNE,
                    EnchantingSmith.id("smithing/fortune"),
                    exporter
            );

            // Power
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.BLAZE_POWDER,
                    Enchantments.POWER,
                    EnchantingSmith.id("smithing/power"),
                    exporter
            );
            // Punch
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.PISTON,
                    Enchantments.PUNCH,
                    EnchantingSmith.id("smithing/punch"),
                    exporter
            );
            // flame
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.FIRE_CHARGE,
                    Enchantments.FLAME,
                    EnchantingSmith.id("smithing/flame"),
                    exporter
            );
            // Infinity
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.END_CRYSTAL,
                    Enchantments.INFINITY,
                    EnchantingSmith.id("smithing/infinity"),
                    exporter
            );

            // Luck of the Sea
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.LAPIS_BLOCK,
                    Enchantments.LUCK_OF_THE_SEA,
                    EnchantingSmith.id("smithing/luck_of_the_sea"),
                    exporter
            );
            // Lure
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.REDSTONE_BLOCK,
                    Enchantments.LURE,
                    EnchantingSmith.id("smithing/lure"),
                    exporter
            );

            // Loyalty
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.LEAD,
                    Enchantments.LOYALTY,
                    EnchantingSmith.id("smithing/loyalty"),
                    exporter
            );
            // Impaling
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.PRISMARINE_SHARD,
                    Enchantments.IMPALING,
                    EnchantingSmith.id("smithing/impaling"),
                    exporter
            );
            // Riptide
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.FIREWORK_ROCKET,
                    Enchantments.RIPTIDE,
                    EnchantingSmith.id("smithing/riptide"),
                    exporter
            );
            // Channeling
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.LIGHTNING_ROD,
                    Enchantments.CHANNELING,
                    EnchantingSmith.id("smithing/channeling"),
                    exporter
            );

            // Multishot
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.CROSSBOW,
                    Enchantments.MULTISHOT,
                    EnchantingSmith.id("smithing/multishot"),
                    exporter
            );
            // Quick Charge
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.REDSTONE_BLOCK,
                    Enchantments.QUICK_CHARGE,
                    EnchantingSmith.id("smithing/quick_charge"),
                    exporter
            );
            // Piercing
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.ARROW,
                    Enchantments.PIERCING,
                    EnchantingSmith.id("smithing/piercing"),
                    exporter
            );

            // Mending
            SmithingUpgradeRecipeJsonBuilder.createSimple(
                    Items.NETHER_STAR,
                    Enchantments.MENDING,
                    EnchantingSmith.id("smithing/mending"),
                    exporter
            );
        }
    }
}