package fr.poseidonj.satisfactory.init;

import fr.poseidonj.satisfactory.Satisfactory;
import fr.poseidonj.satisfactory.block.miner.MinerContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainer {

    public static final DeferredRegister<ContainerType<?>> containers = DeferredRegister.create(ForgeRegistries.CONTAINERS, Satisfactory.modID);
    public static final RegistryObject<ContainerType<MinerContainer>> miner_container=containers.register("miner.container",()-> IForgeContainerType.create(MinerContainer::getClientContainer));
}