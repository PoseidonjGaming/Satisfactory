package fr.perso.satisfactory.recipes.minerRecipe;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MinerRecipe implements Recipe<SimpleInventory> {

    protected final Identifier id;
    protected final ItemStack output;
    protected final Ingredient input;

    public MinerRecipe(Identifier id, Ingredient input, ItemStack output){

        this.id = id;
        this.output = output;
        this.input = input;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {

        boolean canMine=false;
        if(input.test(inventory.getStack(0))){
            canMine=true;
        }

        return canMine;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MinerRecipeSerializer.instance;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.instance;
    }


    public static class Type implements RecipeType<MinerRecipe>{
        private  Type(){}

        public static final Type instance=new Type();
        public static final String id="miner";
    }
}
