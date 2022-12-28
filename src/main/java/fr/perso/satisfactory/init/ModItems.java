package fr.perso.satisfactory.init;

import fr.perso.satisfactory.Main;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModItems {
    public static final ItemGroup groupe= Main.persoGroup;


    public static final List<List> items=new ArrayList<List>(){
        {
            add(Arrays.asList("copper_ingot",new Item(new Item.Settings().group(groupe))));
        }
        {
            add(Arrays.asList("aluminium_ingot",new Item(new Item.Settings().group(groupe))));
        }
        {
            add(Arrays.asList("caterium_ingot",new Item(new Item.Settings().group(groupe))));
        }
        {
            add(Arrays.asList("steel_ingot",new Item(new Item.Settings().group(groupe))));
        }
        {
            add(Arrays.asList("concrete",new Item(new Item.Settings().group(groupe))));
        }
    };
    public static final Item icone= (Item) items.get(0).get(1);
    public static  void registerAll(){
       for(int i=0; i<items.size(); i++){
            registerItem(items.get(i).get(0).toString(), (Item) items.get(i).get(1));
       }
    }



    private static void registerItem(String name,Item item){
        Registry.register(Registry.ITEM, new Identifier(Main.mod_id,name),item);
    }

    public static void registerItemBlock(String name, Block block){
        Registry.register(Registry.ITEM,new Identifier(Main.mod_id,name), new BlockItem(block,new FabricItemSettings().group(groupe)));
    }
}
