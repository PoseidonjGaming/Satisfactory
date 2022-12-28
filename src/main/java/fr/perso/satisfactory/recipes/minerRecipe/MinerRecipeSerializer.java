package fr.perso.satisfactory.recipes.minerRecipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MinerRecipeSerializer implements RecipeSerializer<MinerRecipe> {

    public class MinerRecipeJsonFormat{
        JsonObject input;
        String output;
    }

    public static final MinerRecipeSerializer instance= new MinerRecipeSerializer();

    public MinerRecipeSerializer(){}


    @Override
    public MinerRecipe read(Identifier id, JsonObject json) {

        MinerRecipeJsonFormat minerRecipeJsonFormat=new Gson().fromJson(json, MinerRecipeJsonFormat.class);
        if(minerRecipeJsonFormat.input==null || minerRecipeJsonFormat.output==null){
            throw new JsonSyntaxException("Missing some attributes");
        }

        Ingredient input= Ingredient.fromJson(minerRecipeJsonFormat.input);
        Item outputItem= Registry.ITEM.getOrEmpty(new Identifier(minerRecipeJsonFormat.output)).get();

        ItemStack output= new ItemStack(outputItem, 1);

        return new MinerRecipe(id, input, output);
    }

    @Override
    public MinerRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input=Ingredient.fromPacket(buf);
        ItemStack output=buf.readItemStack();

        return new MinerRecipe(id, input, output);
    }

    @Override
    public void write(PacketByteBuf buf, MinerRecipe recipe) {

        recipe.getInput().write(buf);
        buf.writeItemStack(recipe.getOutput());

    }
}
