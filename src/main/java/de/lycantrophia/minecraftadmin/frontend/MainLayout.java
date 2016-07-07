package de.lycantrophia.minecraftadmin.frontend;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import de.lycantrophia.minecraftadmin.components.serverstatebutton.ServerStateButton;
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
        navButton.updateServerInfo("0", "35%", "3659 MB");
        navButton.setWidth(100, Unit.PERCENTAGE);
        navButton.addClickListener(event -> navigator.navigateTo(name));
        navigationLayout.addComponent(navButton);
        navigator.addView(name, new MinecraftServerView(minecraftServer));
    }

    public void setDefaultView(String defaultView) {
        navigator.navigateTo(defaultView);
    }
}
