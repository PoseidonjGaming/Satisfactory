package fr.perso.satisfactory.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.perso.satisfactory.Main;
import fr.perso.satisfactory.block_entity.minerEntity.MinerEntity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier3Entity;
import fr.perso.satisfactory.screenHandler.MinerScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MinerScreen extends HandledScreen<MinerScreenHandler> {

    public static final Identifier texture=new Identifier(Main.mod_id,"textures/gui/smelter_gui.png");

    @Override
    protected void init() {
        super.init();

    }

    public MinerScreen(MinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,texture);
        int x=(width-176)/2;
        int y=(height-168)/2;
        drawTexture(matrices,x,y,0,0,176,168);

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices,mouseX,mouseY);
    }


}
