package com.verdantartifice.primalmagic.client.renderers.itemstack;

import java.awt.Color;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.client.renderers.tile.AncientManaFontTER;
import com.verdantartifice.primalmagic.common.blocks.mana.AncientManaFontBlock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Custom item stack renderer for ancient mana fonts.
 * 
 * @author Daedalus4096
 * @see {@link com.verdantartifice.primalmagic.common.blocks.mana.AncientManaFontBlock}
 */
@OnlyIn(Dist.CLIENT)
public class AncientManaFontISTER extends ItemStackTileEntityRenderer {
    private static final ModelResourceLocation MRL = new ModelResourceLocation(new ResourceLocation(PrimalMagic.MODID, "ancient_font_earth"), "");
    
    protected void addVertex(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float r, float g, float b, float u, float v) {
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
                .color(r, g, b, 1.0F)
                .tex(u, v)
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }
    
    @Override
    public void func_239207_a_(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Item item = itemStack.getItem();
        if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AncientManaFontBlock) {
            Minecraft mc = Minecraft.getInstance();
            ItemRenderer itemRenderer = mc.getItemRenderer();
            
            Color sourceColor = new Color(((AncientManaFontBlock)((BlockItem)item).getBlock()).getSource().getColor());
            float r = sourceColor.getRed() / 255.0F;
            float g = sourceColor.getGreen() / 255.0F;
            float b = sourceColor.getBlue() / 255.0F;
            float ds = 0.1875F;

            @SuppressWarnings("deprecation")
            TextureAtlasSprite sprite = mc.getModelManager().getAtlasTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).getSprite(AncientManaFontTER.TEXTURE);
            IVertexBuilder builder = buffer.getBuffer(RenderType.getSolid());
            
            // Draw the font base
            IBakedModel model = mc.getModelManager().getModel(MRL);
            itemRenderer.renderModel(model, itemStack, combinedLight, combinedOverlay, matrixStack, builder);
            
            // Draw the font core
            matrixStack.push();
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(45.0F)); // Tilt the core onto its diagonal
            matrixStack.rotate(Vector3f.XP.rotationDegrees(45.0F)); // Tilt the core onto its diagonal
            
            // Draw the south face of the core
            this.addVertex(builder, matrixStack, -ds, ds, ds, r, g, b, sprite.getMinU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, -ds, -ds, ds, r, g, b, sprite.getMinU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, ds, -ds, ds, r, g, b, sprite.getMaxU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, ds, ds, ds, r, g, b, sprite.getMaxU(), sprite.getMaxV());
            
            // Draw the north face of the core
            this.addVertex(builder, matrixStack, -ds, ds, -ds, r, g, b, sprite.getMinU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, ds, ds, -ds, r, g, b, sprite.getMaxU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, ds, -ds, -ds, r, g, b, sprite.getMaxU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, -ds, -ds, -ds, r, g, b, sprite.getMinU(), sprite.getMinV());
            
            // Draw the east face of the core
            this.addVertex(builder, matrixStack, ds, ds, -ds, r, g, b, sprite.getMinU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, ds, ds, ds, r, g, b, sprite.getMaxU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, ds, -ds, ds, r, g, b, sprite.getMaxU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, ds, -ds, -ds, r, g, b, sprite.getMinU(), sprite.getMinV());
            
            // Draw the west face of the core
            this.addVertex(builder, matrixStack, -ds, -ds, ds, r, g, b, sprite.getMaxU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, -ds, ds, ds, r, g, b, sprite.getMaxU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, -ds, ds, -ds, r, g, b, sprite.getMinU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, -ds, -ds, -ds, r, g, b, sprite.getMinU(), sprite.getMinV());
            
            // Draw the top face of the core
            this.addVertex(builder, matrixStack, ds, ds, -ds, r, g, b, sprite.getMaxU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, -ds, ds, -ds, r, g, b, sprite.getMinU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, -ds, ds, ds, r, g, b, sprite.getMinU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, ds, ds, ds, r, g, b, sprite.getMaxU(), sprite.getMaxV());
            
            // Draw the bottom face of the core
            this.addVertex(builder, matrixStack, ds, -ds, -ds, r, g, b, sprite.getMaxU(), sprite.getMinV());
            this.addVertex(builder, matrixStack, ds, -ds, ds, r, g, b, sprite.getMaxU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, -ds, -ds, ds, r, g, b, sprite.getMinU(), sprite.getMaxV());
            this.addVertex(builder, matrixStack, -ds, -ds, -ds, r, g, b, sprite.getMinU(), sprite.getMinV());
            
            matrixStack.pop();
        }
    }
}
