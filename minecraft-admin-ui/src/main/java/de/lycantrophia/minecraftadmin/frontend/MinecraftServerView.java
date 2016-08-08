package de.lycantrophia.minecraftadmin.frontend;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import de.lycantrophia.minecraftadmin.mediation.MinecraftServer;

class MinecraftServerView extends TabSheet implements View {

    private MinecraftServer minecraftServer;

    MinecraftServerView(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        addTab(new CssLayout(), minecraftServer.getName());

        final ServerHeartbeat heartbeat = new ServerHeartbeat();
        addTab(heartbeat, "Heartbeat");
        addTab(new Panel(), "Map");
        addTab(new Panel(), "Server");
        addTab(new Panel(), "Plugins");
        addTab(new Panel(), "Worlds");

        Tab nameTab = getTab(0);
        nameTab.setEnabled(false);
        nameTab.getComponent().setWidth(100, Unit.PIXELS);
        nameTab.setStyleName("name-tab-style");
        nameTab.getComponent().addStyleName("name-tab-style");


        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if (event.getParameters() == null
                || event.getParameters().isEmpty()) {
            System.out.println("MinecraftServerView#enter() - " + minecraftServer.getName() + " - Nothing to see here, just pass along.");
        }
        else {

            System.out.println(event.getParameters());
        }
    }
}
