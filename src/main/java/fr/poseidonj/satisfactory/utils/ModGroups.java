package fr.poseidonj.satisfactory.utils;

import fr.poseidonj.satisfactory.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroups {
    public static final ItemGroup itemGroup = new ItemGroup("items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.aluminium_ingot.get());
        }
    };
}
