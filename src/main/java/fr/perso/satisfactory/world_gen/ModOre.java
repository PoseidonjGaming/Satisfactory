package fr.perso.satisfactory.world_gen;

import fr.perso.satisfactory.init.ModBlocks;
import fr.perso.satisfactory.world_gen.util.OreFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModOre {
    private static List<List> ores=new ArrayList<List>(){
        {
            add(Arrays.asList(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,((Block)ModBlocks.getBlock(0)).getDefaultState())), "copper_ore", 9, 15, 80, 384));
        }
        {
            add(Arrays.asList(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,((Block)ModBlocks.getBlock(0)).getDefaultState())), "aluminium_ore", 9, 15, 80, 384));
        }
    };

    public static void generateOre(){
        for(int i=0; i<ores.size(); i++){
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                    OreFeature.placedFeature(ores.get(i).get(1).toString(),(List<OreFeatureConfig.Target>) ores.get(i).get(0),(int) ores.get(i).get(2),(int) ores.get(i).get(3),(int) ores.get(i).get(3),(int) ores.get(i).get(4)).getKey().get());
        }
    }

    //ORE_IRON_UPPER = PlacedFeatures.register("ore_iron_upper", OreConfiguredFeatures.ORE_IRON, modifiersWithCount(90,HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));

}
