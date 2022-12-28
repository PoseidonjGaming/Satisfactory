package fr.perso.satisfactory.init;

import fr.perso.satisfactory.Main;
import fr.perso.satisfactory.screen.MinerScreen;
import fr.perso.satisfactory.screenHandler.MinerScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreens {
   public static ScreenHandlerType<MinerScreenHandler> minerScreenHandler;

   public static void registerAll() {
      minerScreenHandler =ScreenHandlerRegistry.registerSimple(new Identifier(Main.mod_id, "miner_screen"), MinerScreenHandler::new);
   }

   public static void registerClientAll(){
      ScreenRegistry.register(minerScreenHandler, MinerScreen::new);
   }
}
