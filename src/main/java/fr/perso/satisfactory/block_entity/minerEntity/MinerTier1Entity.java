package fr.perso.satisfactory.block_entity.minerEntity;

import fr.perso.satisfactory.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class MinerTier1Entity extends MinerEntity {
    public MinerTier1Entity(BlockPos pos, BlockState state) {
        super(pos, state);
        maxProgress=20;


    }
    @Override
    public Text getDisplayName() {
        return new LiteralText("Miner Tier 1");
    }
}
