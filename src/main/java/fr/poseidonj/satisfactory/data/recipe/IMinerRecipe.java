package fr.poseidonj.satisfactory.data.recipe;

import fr.poseidonj.satisfactory.Satisfactory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;


public interface IMinerRecipe extends IRecipe<IInventory> {
    ResourceLocation type_id= new ResourceLocation(Satisfactory.modID, "miner");
    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(type_id).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height){
        return true;
    };

    @Override
    default boolean isSpecial() {
        return true;
    }
}
