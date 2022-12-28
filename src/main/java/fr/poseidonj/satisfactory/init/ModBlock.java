package fr.poseidonj.satisfactory.init;

import fr.poseidonj.satisfactory.Satisfactory;
import fr.poseidonj.satisfactory.block.miner.MinerBlock;
import fr.poseidonj.satisfactory.utils.ModGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister<Block> blocks=DeferredRegister.create(ForgeRegistries.BLOCKS, Satisfactory.modID);

    public static final RegistryObject<Block> miner =createBlock("miner", MinerBlock::new);
    public static final RegistryObject<Block> aluminium_ore =createBlock("aluminium_ore", MinerBlock::new);


    private static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier){
        RegistryObject<Block> block= blocks.register(name, supplier);
        ModItems.items.register(name, ()->new BlockItem(block.get(), new Item.Properties().tab(ModGroups.itemGroup)));
        return block;

    }

    public static void register(IEventBus bus){
        blocks.register(bus);
    }
}
