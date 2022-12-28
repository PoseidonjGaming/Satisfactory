package fr.poseidonj.satisfactory.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.poseidonj.satisfactory.init.ModBlock;
import fr.poseidonj.satisfactory.init.ModRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class MinerRecipe implements IMinerRecipe{

    private final ResourceLocation id;



    private final ItemStack output;
    private NonNullList<Ingredient> recipeItems;

    public MinerRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inv, World p_77569_2_) {

        if(recipeItems.get(0).test(inv.getItem(0))){
            return true;
        }
        return false;
    }

    @Override
    public ItemStack assemble(IInventory p_77572_1_) {
        return null;
    }

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.miner_serializer.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    public ItemStack getIcon(){
        return new ItemStack(ModBlock.miner.get());
    }


    public static class MinerRecipeType implements IRecipeType<MinerRecipe>{
        @Override
        public String toString() {
            return MinerRecipe.type_id.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MinerRecipe>{

        @Override
        public MinerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack output= ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json,"output"));
            JsonArray ingredients= JSONUtils.getAsJsonArray(json, "item");
            NonNullList<Ingredient> inputs= NonNullList.withSize(1, Ingredient.EMPTY);
            for(int i=0; i<ingredients.size(); i++){
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new MinerRecipe(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public MinerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs= NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i=0; i<inputs.size(); i++){
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output= buffer.readItem();
            return new MinerRecipe(recipeId, output, inputs);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, MinerRecipe minerRecipe) {

            buffer.writeInt(minerRecipe.getIngredients().size());
            for (Ingredient ingredient:
                    minerRecipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(minerRecipe.getResultItem(), false);

        }
    }

}
