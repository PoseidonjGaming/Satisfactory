package fr.perso.satisfactory.init;

import fr.perso.satisfactory.Main;
import fr.perso.satisfactory.block.CreativeEnergy;
import fr.perso.satisfactory.block.miner.MinerTier1;
import fr.perso.satisfactory.block.miner.MinerTier2;
import fr.perso.satisfactory.block.miner.MinerTier3;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModBlocks {
    private static final ItemGroup groupe= Main.persoGroup;
    private static final List<List> blocks=new ArrayList<List>(){
        {
            add(Arrays.asList("copper_ore",new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f,3.0f), UniformIntProvider.create(10,15))));
        }
        {
            add(Arrays.asList("aluminium_ore",new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f,3.0f), UniformIntProvider.create(10,15))));
        }
        {
            add(Arrays.asList("caterium_ore",new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f,3.0f), UniformIntProvider.create(10,15))));
        }
        {
            add(Arrays.asList("miner_t1",new MinerTier1(FabricBlockSettings.of(Material.METAL).strength(6.0f,6.0f))));
        }
        {
            add(Arrays.asList("miner_t2",new MinerTier2(FabricBlockSettings.of(Material.METAL).strength(6.0f,6.0f))));
        }
        {
            add(Arrays.asList("miner_t3",new MinerTier3(FabricBlockSettings.of(Material.METAL).strength(6.0f,6.0f))));
        }
        {
            add(Arrays.asList("creative_energy",new CreativeEnergy(FabricBlockSettings.of(Material.METAL).strength(1.0f,1.0f))));
        }
    };

    public static Block getBlock(int id){
        return (Block) blocks.get(id).get(1);
    }

    public static void registerAll(){
        for(int i=0; i<blocks.size();i++){
            registerBlock(blocks.get(i).get(0).toString(),(Block)blocks.get(i).get(1));
        }
    }

    private static void registerBlock(String name,Block block){
        Registry.register(Registry.BLOCK,new Identifier(Main.mod_id,name),block);
        ModItems.registerItemBlock(name,block);
    }
}
