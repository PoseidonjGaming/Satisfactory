package fr.perso.satisfactory;

import fr.perso.satisfactory.init.ModBlockEntities;
import fr.perso.satisfactory.init.ModBlocks;
import fr.perso.satisfactory.init.ModItems;
import fr.perso.satisfactory.init.ModScreens;
import fr.perso.satisfactory.world_gen.ModOre;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {

    public static final String mod_id="satisfactory";
    public static final ItemGroup persoGroup= FabricItemGroupBuilder.build(new Identifier("perso","general"),()->new ItemStack(ModItems.icone));

    @Override
    public void onInitialize() {
        ModItems.registerAll();
        ModBlocks.registerAll();
        ModOre.generateOre();
        ModBlockEntities.registerAll();
        ModScreens.registerAll();

    }
}
