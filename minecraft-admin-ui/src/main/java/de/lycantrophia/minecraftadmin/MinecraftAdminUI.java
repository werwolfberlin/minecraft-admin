package de.lycantrophia.minecraftadmin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.lycantrophia.minecraftadmin.frontend.MainLayout;
import de.lycantrophia.minecraftadmin.mediation.MinecraftServer;

import javax.servlet.annotation.WebServlet;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("de.lycantrophia.minecraftadmin.MinecraftAdminWidgetset")
public class MinecraftAdminUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Minecraft Admin");
        final MainLayout content = new MainLayout(this);

        //load running servers from backend and create a view for each.
        final Collection<MinecraftServer> minecraftServers = new LinkedHashSet<>();
        minecraftServers.add(new MinecraftServer("Hugo"));
        minecraftServers.add(new MinecraftServer("Jens"));

        String defaultView = "";
        for (MinecraftServer minecraftServer : minecraftServers) {
            content.addMinecraftServer(minecraftServer);
            if(defaultView.isEmpty()) defaultView = minecraftServer.getName();
        }
        content.setDefaultView(defaultView);

        setContent(content);
    }

    @WebServlet(urlPatterns = "/*", name = "MinecraftAdminServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MinecraftAdminUI.class, productionMode = false)
    public static class MinecraftAdminServlet extends VaadinServlet {
    }

}
