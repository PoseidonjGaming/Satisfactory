package fr.perso.satisfactory.init;

import fr.perso.satisfactory.block_entity.CreativeEnergyEntity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier1Entity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier2Entity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier3Entity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModBlockEntities {

    private static final List<List> entities=new ArrayList<List>(){
        {
            add(Arrays.asList("miner_t1_entity", FabricBlockEntityTypeBuilder.create(MinerTier1Entity::new ,ModBlocks.getBlock(3)).build(null)));
        }
        {
            add(Arrays.asList("miner_t2_entity", FabricBlockEntityTypeBuilder.create(MinerTier2Entity::new ,ModBlocks.getBlock(4)).build(null)));
        }
        {
            add(Arrays.asList("miner_t3_entity", FabricBlockEntityTypeBuilder.create(MinerTier3Entity::new ,ModBlocks.getBlock(5)).build(null)));
        }
        {
            add(Arrays.asList("creative_energy_entity", FabricBlockEntityTypeBuilder.create(CreativeEnergyEntity::new, ModBlocks.getBlock(6)).build(null)));
        }
    };

    public static void registerAll(){
        for (int i=0; i<entities.size(); i++){
            Registry.register(Registry.BLOCK_ENTITY_TYPE,entities.get(i).get(0).toString(),(BlockEntityType<?>) entities.get(i).get(1));
        }
    }

    public static BlockEntityType<?> getEntity(int id){
        return (BlockEntityType<?>) entities.get(id).get(1);
    }

}
