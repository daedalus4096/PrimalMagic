package com.verdantartifice.primalmagic.common.init;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.blocks.BlocksPM;
import com.verdantartifice.primalmagic.common.crafting.RecipeTypesPM;
import com.verdantartifice.primalmagic.common.crafting.WandTransformBlock;
import com.verdantartifice.primalmagic.common.crafting.WandTransformBlockTag;
import com.verdantartifice.primalmagic.common.crafting.WandTransforms;
import com.verdantartifice.primalmagic.common.items.ItemsPM;
import com.verdantartifice.primalmagic.common.research.CompoundResearchKey;
import com.verdantartifice.primalmagic.common.research.SimpleResearchKey;
import com.verdantartifice.primalmagic.common.tags.BlockTagsForgeExt;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

/**
 * Point of registration for mod recipe types, as well as other crafting related things.
 * 
 * @author Daedalus4096
 */
public class InitRecipes {
    public static void initRecipeTypes() {
        RecipeTypesPM.ARCANE_CRAFTING = IRecipeType.register(PrimalMagic.MODID + ":arcane_crafting");
        RecipeTypesPM.RITUAL = IRecipeType.register(PrimalMagic.MODID + ":ritual");
        RecipeTypesPM.RUNECARVING = IRecipeType.register(PrimalMagic.MODID + ":runecarving");
        RecipeTypesPM.CONCOCTING = IRecipeType.register(PrimalMagic.MODID + ":concocting");
    }
    
    public static void initWandTransforms() {
        WandTransforms.register(new WandTransformBlockTag(BlockTagsForgeExt.BOOKSHELVES, new ItemStack(ItemsPM.GRIMOIRE.get()), CompoundResearchKey.from(SimpleResearchKey.parse("t_got_dream"))));
        WandTransforms.register(new WandTransformBlock(Blocks.CRAFTING_TABLE, new ItemStack(BlocksPM.ARCANE_WORKBENCH.get()), CompoundResearchKey.from(SimpleResearchKey.parse("FIRST_STEPS@1"))));
        WandTransforms.register(new WandTransformBlock(Blocks.FURNACE, new ItemStack(BlocksPM.ESSENCE_FURNACE.get()), CompoundResearchKey.from(SimpleResearchKey.parse("BASIC_ALCHEMY"))));
    }
}
