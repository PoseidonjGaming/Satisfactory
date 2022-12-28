package fr.perso.satisfactory.init;

import fr.perso.satisfactory.Main;
import fr.perso.satisfactory.recipes.minerRecipe.MinerRecipe;
import fr.perso.satisfactory.recipes.minerRecipe.MinerRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipe {
    public static void registerRecipe() {

        Registry.register(Registry.RECIPE_SERIALIZER,new Identifier(Main.mod_id, MinerRecipe.Type.id), MinerRecipeSerializer.instance);


    }
}
