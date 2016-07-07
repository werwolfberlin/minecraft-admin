package de.lycantrophia.minecraftadmin.frontend;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import de.lycantrophia.minecraftadmin.frontend.design.ServerHeartbeatDesign;
import de.lycantrophia.minecraftadmin.mediation.MinecraftServer;

/**
 * Created by Werwolf on 04.07.2016.
 */
public class MinecraftServerView extends TabSheet implements View {

    private MinecraftServer minecraftServer;

    public MinecraftServerView(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        addTab(new ServerHeartbeatDesign(), "Heartbeat");
        addTab(new Panel(), "Map");
        addTab(new Panel(), "Server");
        addTab(new Panel(), "Plugins");
        addTab(new Panel(), "Worlds");
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
