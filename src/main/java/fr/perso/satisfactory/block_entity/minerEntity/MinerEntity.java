package fr.perso.satisfactory.block_entity.minerEntity;

import fr.perso.satisfactory.block_entity.CreativeEnergyEntity;
import fr.perso.satisfactory.init.ModBlockEntities;
import fr.perso.satisfactory.inventory.ImplementedInventory;
import fr.perso.satisfactory.recipes.minerRecipe.MinerRecipe;
import fr.perso.satisfactory.screenHandler.MinerScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.Optional;

public class MinerEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    protected final PropertyDelegate propertyDelegate;
    protected int progress=0;
    protected static int maxProgress=0;
    private final DefaultedList<ItemStack> inventory=DefaultedList.ofSize(2,ItemStack.EMPTY);
    protected static BlockEntityType entityType = ModBlockEntities.getEntity(0);

    private final int nameplateCapacity=5;
    public static int capacity;
    protected static final int maxCapacity=10000;

    public MinerEntity(BlockPos pos, BlockState state) {
        super(entityType, pos, state);
        this.propertyDelegate=new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index){
                    case 0: return MinerEntity.this.progress;
                    case 1: return MinerEntity.this.maxProgress;
                    case 2: return MinerEntity.this.capacity;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0:  MinerEntity.this.progress=value; break;
                    case 1:  MinerEntity.this.maxProgress=value; break;
                    case 2:  MinerEntity.this.capacity =value; break;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }




    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);

        progress= nbt.getInt("miner.progress");
        maxProgress=nbt.getInt("miner.maxprogress");
        capacity = nbt.getInt("miner.power");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
        nbt.putInt("miner.progress",progress);
        nbt.putInt("miner.maxprogress",maxProgress);
        nbt.putInt("miner.power", capacity);
    }

    public static void tick(World world, BlockPos pos, BlockState state, MinerEntity entity){
       BlockEntity targetEtity= world.getBlockEntity(pos.up());
       if(targetEtity instanceof CreativeEnergyEntity){
           capacity+=((CreativeEnergyEntity) targetEtity).capacityDelivery;
       }

       if(hasRecipe(entity)){

       }
    }

    @Override
    public Text getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MinerScreenHandler(syncId, inv, this,this.propertyDelegate);
    }

    protected static boolean hasRecipe(MinerEntity entity){
        World world= entity.world;
        SimpleInventory inventory=new SimpleInventory(entity.inventory.size());

        for (int i=0; i<entity.inventory.size(); i++){
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<MinerRecipe> match= world.getRecipeManager().getFirstMatch(MinerRecipe.Type.instance, inventory, world);

        return match.isPresent();
    }

    //private








}
