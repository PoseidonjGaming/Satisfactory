package fr.poseidonj.satisfactory.block.miner;

import fr.poseidonj.satisfactory.data.recipe.MinerRecipe;
import fr.poseidonj.satisfactory.init.ModBlock;
import fr.poseidonj.satisfactory.init.ModEntity;
import fr.poseidonj.satisfactory.init.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MinerEntity extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemHandler= createInventory();
    private final LazyOptional<IItemHandlerModifiable> inventory=LazyOptional.of(()->itemHandler);
    public MinerEntity() {
        super(ModEntity.miner_tileEntity.get());
    }

    @Override
    public CompoundNBT save(CompoundNBT compoundNBT) {
        compoundNBT.put("inventory",itemHandler.serializeNBT());
        return compoundNBT;
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT compoundNBT) {
        super.load(p_230337_1_, compoundNBT);
        itemHandler.deserializeNBT((CompoundNBT) compoundNBT.get("inventory"));
        this.setChanged();
    }

    @Override
    public void tick() {

        if(!level.isClientSide()){
            //craft();
            itemHandler.insertItem(1, ModBlock.aluminium_ore.get().asItem().getDefaultInstance(), false);
        }
    }

    public void craft(){
        Inventory inv=new Inventory(itemHandler.getSlots());
        for(int i=0; i<itemHandler.getSlots(); i++){
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<MinerRecipe> recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.minerRecipe, inv, level);

        recipe.ifPresent(minerRecipe -> {

            ItemStack output =minerRecipe.getResultItem();
            craftItem(output);


        });
    }

    private void craftItem(ItemStack output){
        itemHandler.extractItem(0,1,false);

        itemHandler.insertItem(1, output, false);
    }

    @Nonnull
    public ItemStackHandler createInventory(){
        return new ItemStackHandler(3){
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
               return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 256;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
    public IItemHandlerModifiable getInventory(){
        return inventory.orElseThrow(()->new IllegalStateException("Inventory not setup"));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return inventory.cast();
        }
        return super.getCapability(cap, side);

    }
}
