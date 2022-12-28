package fr.perso.satisfactory.block.miner;

import fr.perso.satisfactory.block_entity.minerEntity.MinerEntity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier1Entity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier2Entity;
import fr.perso.satisfactory.block_entity.minerEntity.MinerTier3Entity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Miner extends BlockWithEntity implements BlockEntityProvider {


    public Miner(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient()){
            NamedScreenHandlerFactory screenHandlerFactory= state.createScreenHandlerFactory(world, pos);
            if(screenHandlerFactory!=null){
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock()!= newState.getBlock()){
            BlockEntity entity=world.getBlockEntity(pos);
            if(entity instanceof MinerTier1Entity || entity instanceof MinerTier2Entity || entity instanceof MinerTier3Entity){
                ItemScatterer.spawn(world, pos, (Inventory) entity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state,world,pos,newState,moved);
        }
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinerEntity(pos, state);
    }
}
