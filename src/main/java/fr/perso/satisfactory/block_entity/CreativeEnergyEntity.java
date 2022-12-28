package fr.perso.satisfactory.block_entity;

import fr.perso.satisfactory.block_entity.minerEntity.MinerEntity;
import fr.perso.satisfactory.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CreativeEnergyEntity extends BlockEntity {

    public final int capacityDelivery=500000;

    public CreativeEnergyEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.getEntity(3), pos, state);
    }

}
