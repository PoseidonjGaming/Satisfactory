package fr.perso.satisfactory.screenHandler;

import fr.perso.satisfactory.init.ModScreens;
import fr.perso.satisfactory.inventory.slot.InputMinerSlot;
import fr.perso.satisfactory.inventory.slot.ResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MinerScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate property;

    public MinerScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory, new SimpleInventory(2), new ArrayPropertyDelegate(3));
    }

    public MinerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate property) {
        super(ModScreens.minerScreenHandler, syncId);
        checkSize(inventory,2);
        this.inventory=inventory;
        inventory.onOpen(playerInventory.player);
        this.property=property;

        this.addSlot(new InputMinerSlot(inventory, 0,70,7));
        this.addSlot(new ResultSlot(inventory,1,90,7));
        addPlayerHotbar(playerInventory);
        addPlayerInventory(playerInventory);
        addProperties(property);

    }




    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = new ItemStack(slot.getStack().getItem(), 1);

            if (index < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 85 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 143));
        }
    }

    /*public int getScaledProgress() {
        int progress = this.property.get(0);
        int maxProgress = this.property.get(1);  // Max Progress
        int progressArrowSize = 35; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }*/
}
