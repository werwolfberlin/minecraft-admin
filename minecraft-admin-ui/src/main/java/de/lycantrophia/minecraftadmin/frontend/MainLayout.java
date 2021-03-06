package de.lycantrophia.minecraftadmin.frontend;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import de.lycantrophia.addon.serverstatebutton.ServerStateButton;
import de.lycantrophia.minecraftadmin.frontend.design.MainLayoutDesign;
import de.lycantrophia.minecraftadmin.mediation.MinecraftServer;

public class MainLayout extends MainLayoutDesign {

    private static final int BUTTON_SIZE = 135;
    private static final int MAX_RAM = 4096;

    private final Navigator navigator;

    public MainLayout(final UI ui) {

        navigator = new Navigator(ui, contentLayout);
    }

    public void addMinecraftServer(MinecraftServer minecraftServer) {
        final String name = minecraftServer.getName();
        final ServerStateButton navButton = new ServerStateButton(name.length() > 4);
        navButton.setServerName(name);
        navButton.setMaxRam(MAX_RAM);
        navButton.updateServerInfo("" + (int)(Math.random() * 20), Math.random(), (int)(Math.random() * MAX_RAM));
        navButton.setWidth(BUTTON_SIZE, Unit.PIXELS);
        navButton.setHeight(BUTTON_SIZE, Unit.PIXELS);
        navButton.addClickListener(event -> navigator.navigateTo(name));
        navButton.addStyleName("v-button");
        navButton.addStyleName("flat");
        navButton.addStyleName("server-button-adaption");
        navigationLayout.addComponent(navButton);
        navigator.addView(name, new MinecraftServerView(minecraftServer));
    }

    public void setDefaultView(String defaultView) {
        navigator.navigateTo(defaultView);
    }
}
