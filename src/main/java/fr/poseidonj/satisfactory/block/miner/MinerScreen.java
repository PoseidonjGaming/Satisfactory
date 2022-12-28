package fr.poseidonj.satisfactory.block.miner;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.poseidonj.satisfactory.Satisfactory;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MinerScreen extends ContainerScreen<MinerContainer> {

    private static final ResourceLocation texture=new ResourceLocation(Satisfactory.modID,"textures/gui/smelter_gui.png");

   

    public MinerScreen(MinerContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.imageWidth=176;
        this.imageHeight=255;
        this.inventoryLabelY=115;
        this.titleLabelY=51;
        this.height=20;
        


    }

    @Override
    protected void renderBg(MatrixStack stack, float mouseX, int mouseY, int partialTicks) {
        if(this.minecraft==null){
            return;
        }
        RenderSystem.color4f(1f,1f,1f,1f);
        this.minecraft.getTextureManager().bind(texture);
        int startX=(width-176)/2;
        int startY=(height-168)/2;
        this.blit(stack,startX,startY,0,0,imageWidth,imageHeight);

    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack,mouseX,mouseY);
    }

}
