package fr.perso.satisfactory.block_entity.minerEntity;

import fr.perso.satisfactory.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class MinerTier2Entity extends MinerEntity {
    public MinerTier2Entity(BlockPos pos, BlockState state) {
        super(pos, state);
        maxProgress=10;
        entityType= ModBlockEntities.getEntity(1);
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Miner Tier 2");
    }
}
