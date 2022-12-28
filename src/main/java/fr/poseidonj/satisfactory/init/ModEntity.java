package fr.poseidonj.satisfactory.init;

import fr.poseidonj.satisfactory.Satisfactory;
import fr.poseidonj.satisfactory.block.miner.MinerEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntity {

    public static final DeferredRegister<TileEntityType<?>> tileEntity=DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Satisfactory.modID);

    public static final RegistryObject<TileEntityType<MinerEntity>> miner_tileEntity=tileEntity.register("miner",()->TileEntityType.Builder.of(MinerEntity::new, ModBlock.miner.get()).build(null));
}
