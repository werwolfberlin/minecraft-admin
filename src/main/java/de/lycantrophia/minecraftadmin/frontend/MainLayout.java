package de.lycantrophia.minecraftadmin.frontend;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import de.lycantrophia.ServerStateButton;
import de.lycantrophia.minecraftadmin.frontend.design.MainLayoutDesign;
import de.lycantrophia.minecraftadmin.mediation.MinecraftServer;

/**
 * Created by Werwolf on 07.07.2016.
 */
public class MainLayout extends MainLayoutDesign {

    private final Navigator navigator;

    public MainLayout(final UI ui) {

        navigator = new Navigator(ui, contentLayout);
    }

    public void addMinecraftServer(MinecraftServer minecraftServer) {
        final String name = minecraftServer.getName();
        final ServerStateButton navButton = new ServerStateButton();
        navButton.setServerName(name);
        navButton.setMaxRam(4096);
        navButton.updateServerInfo("2", 0.62, 3659);
        navButton.setWidth(140, Unit.PIXELS);
        navButton.setHeight(140, Unit.PIXELS);
        navButton.addClickListener(event -> navigator.navigateTo(name));
        navigationLayout.addComponent(navButton);
        navigator.addView(name, new MinecraftServerView(minecraftServer));
    }

    public void setDefaultView(String defaultView) {
        navigator.navigateTo(defaultView);
    }
}
