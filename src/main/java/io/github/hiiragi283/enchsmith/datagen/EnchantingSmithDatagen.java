package io.github.hiiragi283.enchsmith.datagen;

import io.github.hiiragi283.enchsmith.EnchantingSmith;
import io.github.hiiragi283.enchsmith.UpgradeSmithingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class EnchantingSmithDatagen {

    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new Recipe(generator));
        }
    }

    private static final class Recipe extends RecipeProvider {
        public Recipe(DataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void buildShapelessRecipes(@NotNull Consumer<IFinishedRecipe> exporter) {
            // Protection
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.IRON_BLOCK,
                    Enchantments.ALL_DAMAGE_PROTECTION,
                    EnchantingSmith.location("smithing/protection"),
                    exporter
            );
            // Fire Protection
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.NETHER_BRICKS,
                    Enchantments.FIRE_PROTECTION,
                    EnchantingSmith.location("smithing/fire_protection"),
                    exporter
            );
            // Feather Falling
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.FEATHER,
                    Enchantments.FALL_PROTECTION,
                    EnchantingSmith.location("smithing/feather_falling"),
                    exporter
            );
            // Blast Falling
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.OBSIDIAN,
                    Enchantments.BLAST_PROTECTION,
                    EnchantingSmith.location("smithing/blast_falling"),
                    exporter
            );
            // Projectile Falling
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.SHIELD,
                    Enchantments.PROJECTILE_PROTECTION,
                    EnchantingSmith.location("smithing/projectile_falling"),
                    exporter
            );
            // Aqua Affinity
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.HEART_OF_THE_SEA,
                    Enchantments.AQUA_AFFINITY,
                    EnchantingSmith.location("smithing/aqua_affinity"),
                    exporter
            );
            // Thorns
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.CACTUS,
                    Enchantments.THORNS,
                    EnchantingSmith.location("smithing/thorns"),
                    exporter
            );
            // Depth Strider
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.NAUTILUS_SHELL,
                    Enchantments.DEPTH_STRIDER,
                    EnchantingSmith.location("smithing/depth_strider"),
                    exporter
            );
            // Frost Walker
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.BLUE_ICE,
                    Enchantments.FROST_WALKER,
                    EnchantingSmith.location("smithing/frost_walker"),
                    exporter
            );
            // Soul Speed
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.SOUL_SOIL,
                    Enchantments.SOUL_SPEED,
                    EnchantingSmith.location("smithing/soul_speed"),
                    exporter
            );

            // Sharpness
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.QUARTZ_BLOCK,
                    Enchantments.SHARPNESS,
                    EnchantingSmith.location("smithing/sharpness"),
                    exporter
            );
            // Smite
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.GLOWSTONE,
                    Enchantments.SMITE,
                    EnchantingSmith.location("smithing/smite"),
                    exporter
            );
            // Bane of arthropods
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.PUFFERFISH,
                    Enchantments.BANE_OF_ARTHROPODS,
                    EnchantingSmith.location("smithing/bane_of_arthropods"),
                    exporter
            );
            // Knockback
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.PISTON,
                    Enchantments.KNOCKBACK,
                    EnchantingSmith.location("smithing/knockback"),
                    exporter
            );
            // Fire Aspect
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.FLINT_AND_STEEL,
                    Enchantments.FIRE_ASPECT,
                    EnchantingSmith.location("smithing/fire_aspect"),
                    exporter
            );
            // Looting
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.LAPIS_BLOCK,
                    Enchantments.MOB_LOOTING,
                    EnchantingSmith.location("smithing/looting"),
                    exporter
            );
            // Sweeping
            UpgradeSmithingRecipeBuilder.createSimple(
                    ItemTags.SIGNS,
                    Enchantments.SWEEPING_EDGE,
                    EnchantingSmith.location("smithing/sweeping"),
                    exporter
            );

            // Efficiency
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.REDSTONE_BLOCK,
                    Enchantments.BLOCK_EFFICIENCY,
                    EnchantingSmith.location("smithing/efficiency"),
                    exporter
            );
            // Silk Touch
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.PHANTOM_MEMBRANE,
                    Enchantments.SILK_TOUCH,
                    EnchantingSmith.location("smithing/silk_touch"),
                    exporter
            );
            // Unbreaking
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.DIAMOND,
                    Enchantments.UNBREAKING,
                    EnchantingSmith.location("smithing/unbreaking"),
                    exporter
            );
            // Fortune
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.LAPIS_BLOCK,
                    Enchantments.BLOCK_FORTUNE,
                    EnchantingSmith.location("smithing/fortune"),
                    exporter
            );

            // Power
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.BLAZE_POWDER,
                    Enchantments.POWER_ARROWS,
                    EnchantingSmith.location("smithing/power"),
                    exporter
            );
            // Punch
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.PISTON,
                    Enchantments.PUNCH_ARROWS,
                    EnchantingSmith.location("smithing/punch"),
                    exporter
            );
            // flame
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.FIRE_CHARGE,
                    Enchantments.FLAMING_ARROWS,
                    EnchantingSmith.location("smithing/flame"),
                    exporter
            );
            // Infinity
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.END_CRYSTAL,
                    Enchantments.INFINITY_ARROWS,
                    EnchantingSmith.location("smithing/infinity"),
                    exporter
            );

            // Luck of the Sea
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.LAPIS_BLOCK,
                    Enchantments.FISHING_LUCK,
                    EnchantingSmith.location("smithing/luck_of_the_sea"),
                    exporter
            );
            // Lure
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.REDSTONE_BLOCK,
                    Enchantments.FISHING_SPEED,
                    EnchantingSmith.location("smithing/lure"),
                    exporter
            );

            // Loyalty
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.LEAD,
                    Enchantments.LOYALTY,
                    EnchantingSmith.location("smithing/loyalty"),
                    exporter
            );
            // Impaling
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.PRISMARINE_SHARD,
                    Enchantments.IMPALING,
                    EnchantingSmith.location("smithing/impaling"),
                    exporter
            );
            // Riptide
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.FIREWORK_ROCKET,
                    Enchantments.RIPTIDE,
                    EnchantingSmith.location("smithing/riptide"),
                    exporter
            );
            // Channeling
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.BLAZE_ROD,
                    Enchantments.CHANNELING,
                    EnchantingSmith.location("smithing/channeling"),
                    exporter
            );

            // Multishot
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.CROSSBOW,
                    Enchantments.MULTISHOT,
                    EnchantingSmith.location("smithing/multishot"),
                    exporter
            );
            // Quick Charge
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.REDSTONE_BLOCK,
                    Enchantments.QUICK_CHARGE,
                    EnchantingSmith.location("smithing/quick_charge"),
                    exporter
            );
            // Piercing
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.ARROW,
                    Enchantments.PIERCING,
                    EnchantingSmith.location("smithing/piercing"),
                    exporter
            );

            // Mending
            UpgradeSmithingRecipeBuilder.createSimple(
                    Items.NETHER_STAR,
                    Enchantments.MENDING,
                    EnchantingSmith.location("smithing/mending"),
                    exporter
            );
        }
    }

}