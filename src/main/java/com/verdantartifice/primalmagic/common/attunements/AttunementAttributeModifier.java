package com.verdantartifice.primalmagic.common.attunements;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.verdantartifice.primalmagic.common.sources.Source;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

/**
 * Definition of an attunement-linked attribute modifier.  Used to modify entity attributes when
 * crossing certain attunement thresholds.
 * 
 * @author Daedalus4096
 */
public class AttunementAttributeModifier {
    protected final Source source;
    protected final AttunementThreshold threshold;
    protected final Attribute attribute;
    protected final AttributeModifier modifier;
    
    public AttunementAttributeModifier(@Nonnull Source source, AttunementThreshold threshold, @Nonnull Attribute attribute, @Nonnull String uuidStr, double modValue, @Nonnull AttributeModifier.Operation modOperation) {
        this.source = source;
        this.threshold = threshold;
        this.attribute = attribute;
        this.modifier = new AttributeModifier(UUID.fromString(uuidStr), this::getModifierName, modValue, modOperation);
    }
    
    @Nonnull
    public String getModifierName() {
        return this.source.getTag() + Integer.toString(this.threshold.getValue());
    }
    
    @Nonnull
    public Source getSource() {
        return this.source;
    }
    
    public AttunementThreshold getThreshold() {
        return this.threshold;
    }
    
    @Nonnull
    public Attribute getAttribute() {
        return this.attribute;
    }
    
    @Nonnull
    public AttributeModifier getModifier() {
        return this.modifier;
    }
    
    public void applyToEntity(@Nullable LivingEntity entity) {
        if (entity != null && !entity.world.isRemote) {
        	ModifiableAttributeInstance instance = entity.getAttribute(this.getAttribute());
            if (instance != null) {
                instance.removeModifier(this.getModifier());
                instance.applyPersistentModifier(this.getModifier());
            }
        }
    }
    
    public void removeFromEntity(@Nullable LivingEntity entity) {
        if (entity != null && !entity.world.isRemote) {
        	ModifiableAttributeInstance instance = entity.getAttribute(this.getAttribute());
            if (instance != null) {
                instance.removeModifier(this.getModifier());
            }
        }
    }
}
