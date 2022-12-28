package fr.poseidonj.satisfactory.init;

import fr.poseidonj.satisfactory.Satisfactory;
import fr.poseidonj.satisfactory.data.recipe.MinerRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
    private static final DeferredRegister<IRecipeSerializer<?>> recipes=DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Satisfactory.modID);

    public static final RegistryObject<MinerRecipe.Serializer> miner_serializer=recipes.register("miner", MinerRecipe.Serializer::new);

    public static IRecipeType<MinerRecipe> minerRecipe=new MinerRecipe.MinerRecipeType();

    public static void register(IEventBus bus){
        recipes.register(bus);
        Registry.register(Registry.RECIPE_TYPE, MinerRecipe.type_id, minerRecipe);
    }

}
