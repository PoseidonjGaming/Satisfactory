package fr.poseidonj.satisfactory;

import fr.poseidonj.satisfactory.init.*;
import fr.poseidonj.satisfactory.block.miner.MinerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("satisfactory")
public class Satisfactory {

    public static final String modID="satisfactory";
    public Satisfactory(){

        IEventBus bus= FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.registerItem(bus);
        ModBlock.register(bus);
        ModEntity.tileEntity.register(bus);
        ModContainer.containers.register(bus);
        ModRecipeTypes.register(bus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);


    }

    private void client(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ScreenManager.register(ModContainer.miner_container.get(), MinerScreen::new);
        });
    }

    private void setup(FMLCommonSetupEvent event) {

        /*ModOreGen features= new ModOreGen();
        features.init();
        MinecraftForge.EVENT_BUS.register(features);*/


    }
}
