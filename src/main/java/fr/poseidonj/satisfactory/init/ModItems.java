package fr.poseidonj.satisfactory.init;

import fr.poseidonj.satisfactory.Satisfactory;
import fr.poseidonj.satisfactory.utils.ModGroups;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Satisfactory.modID);

    public static final RegistryObject<Item> aluminium_ingot = items.register("aluminium_ingot", ()-> new Item(new Item.Properties().tab(ModGroups.itemGroup)));
    private static final RegistryObject<Item> caterium_ingot = items.register("caterium_ingot",()-> new Item(new Item.Properties().tab(ModGroups.itemGroup)));
    private static final RegistryObject<Item> steel_ingot = items.register("steel_ingot",()-> new Item(new Item.Properties().tab(ModGroups.itemGroup)));

    public static void registerItem(IEventBus bus){
        items.register(bus);
    }
}
