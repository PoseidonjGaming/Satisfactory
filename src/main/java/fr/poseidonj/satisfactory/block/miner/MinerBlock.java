package fr.poseidonj.satisfactory.block.miner;

import fr.poseidonj.satisfactory.init.ModEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class MinerBlock extends Block {


    public MinerBlock() {
        super(AbstractBlock.Properties.of(Material.METAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModEntity.miner_tileEntity.get().create();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult traceResult) {

        MinerEntity entity = (MinerEntity) world.getBlockEntity(pos);
        if(!world.isClientSide()){
            if(entity instanceof MinerEntity){
                IContainerProvider provider = MinerContainer.getServerProvider(entity, pos);
                INamedContainerProvider containerProvider=new SimpleNamedContainerProvider(provider,new TranslationTextComponent("test.screen"));
                playerEntity.openMenu(containerProvider);
            }


        }



        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos pos){
        return new INamedContainerProvider(){

            @Nullable
            @Override
            public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity playerEntity) {
                return new MinerContainer(id, playerInv, (IItemHandler) world,playerEntity,pos);
            }

            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("test.screen");
            }
        };
    }
}
