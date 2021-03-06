package com.verdantartifice.primalmagic.datagen;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.datagen.affinities.AffinityProvider;
import com.verdantartifice.primalmagic.datagen.loot_modifiers.LootModifierProvider;
import com.verdantartifice.primalmagic.datagen.loot_tables.BlockLootTables;
import com.verdantartifice.primalmagic.datagen.loot_tables.EntityLootTables;
import com.verdantartifice.primalmagic.datagen.recipes.Recipes;
import com.verdantartifice.primalmagic.datagen.research.ResearchProvider;
import com.verdantartifice.primalmagic.datagen.tags.BlockTagsProvider;
import com.verdantartifice.primalmagic.datagen.tags.EntityTypeTagsProvider;
import com.verdantartifice.primalmagic.datagen.tags.ItemTagsProvider;
import com.verdantartifice.primalmagic.datagen.theorycrafting.ProjectProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * Handlers for events related to data file generation.
 * 
 * @author Daedalus4096
 */
@Mod.EventBusSubscriber(modid=PrimalMagic.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        // Add all of the mod's data providers to the generator for processing
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new Recipes(generator));
        generator.addProvider(new BlockLootTables(generator));
        generator.addProvider(new EntityLootTables(generator));
        generator.addProvider(new BlockTagsProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(new ItemTagsProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(new EntityTypeTagsProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(new AffinityProvider(generator));
        generator.addProvider(new ResearchProvider(generator));
        generator.addProvider(new ProjectProvider(generator));
        generator.addProvider(new LootModifierProvider(generator));
    }
}
