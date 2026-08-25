/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.drakma.aeroflux.client.gui.TankGUIScreen;
import net.drakma.aeroflux.client.gui.InfuserGUIScreen;
import net.drakma.aeroflux.client.gui.CollectorGUIScreen;

@EventBusSubscriber(Dist.CLIENT)
public class AerofluxModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(AerofluxModMenus.COLLECTOR_GUI.get(), CollectorGUIScreen::new);
		event.register(AerofluxModMenus.TANK_GUI.get(), TankGUIScreen::new);
		event.register(AerofluxModMenus.INFUSER_GUI.get(), InfuserGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}