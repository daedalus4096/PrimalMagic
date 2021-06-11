package com.verdantartifice.primalmagic.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Definition of an enchantment that expands a digging tool's area of effect.
 * 
 * @author Daedalus4096
 */
public class ReverberationEnchantment extends AbstractRuneEnchantment {
    public ReverberationEnchantment(Enchantment.Rarity rarity, EquipmentSlotType... slots) {
        super(rarity, EnchantmentType.DIGGER, slots);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + ((enchantmentLevel - 1) * 10);
    }
    
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }
    
    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        // TODO Disallow coexistence with Disintegration
        return super.canApplyTogether(ench);
    }
}
