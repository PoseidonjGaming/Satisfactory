package fr.poseidonj.satisfactory.block.miner;

import fr.poseidonj.satisfactory.init.ModBlock;
import fr.poseidonj.satisfactory.init.ModContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class MinerContainer extends Container {

    private final IWorldPosCallable worldPosCallable;



    public MinerContainer(int id, PlayerInventory playerInv, IItemHandler storageInv, PlayerEntity player, BlockPos pos) {
        super(ModContainer.miner_container.get(), id);
        worldPosCallable= IWorldPosCallable.create(player.level,pos);

        this.addSlot(new Slot((IInventory) storageInv, 0,18,18));
        this.addSlot(new Slot((IInventory) storageInv, 1,18*2,18));



        for(int i=0; i<=3; i++){
            if(i==0){
                for(int j=0; j<9; j++){
                    this.addSlot(new Slot(playerInv,i*10+j,8+j*18,187));
                }
            }else{
                for(int j=0; j<9; j++){
                    this.addSlot(new Slot(playerInv,i*10+j, 8+j*18,111+i*18));
                }
            }


        }



    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int id) {
        if(id>=9){
            moveItemStackTo(this.getItems().get(id),9,35,false);
        }
        else{
            moveItemStackTo(this.getItems().get(id),0,8,false);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(worldPosCallable,player, ModBlock.miner.get());
    }

    public static MinerContainer getClientContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer){
        return new MinerContainer(id,playerInventory, new ItemStackHandler(3),playerInventory.player, BlockPos.ZERO);
    }

    public static IContainerProvider getServerProvider(MinerEntity entity, BlockPos pos){
        return (id,playerInventory, serverPlayer)->new MinerContainer(id, playerInventory, entity.getInventory(), playerInventory.player,pos);
    }


}
