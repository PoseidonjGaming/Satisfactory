package fr.perso.satisfactory.inventory.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class InputMinerSlot extends Slot {
    public InputMinerSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
