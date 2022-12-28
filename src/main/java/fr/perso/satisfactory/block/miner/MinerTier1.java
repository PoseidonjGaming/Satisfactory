package fr.perso.satisfactory.block.miner;

import fr.perso.satisfactory.block_entity.minerEntity.MinerTier1Entity;
import fr.perso.satisfactory.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MinerTier1 extends Miner{

    public MinerTier1(Settings settings) {
        super(settings);

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinerTier1Entity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type,(BlockEntityType) ModBlockEntities.getEntity(0), MinerTier1Entity::tick);
    }
}
