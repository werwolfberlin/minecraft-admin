package de.lycantrophia.minecraftadmin.components.serverstatebutton.client;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import de.lycantrophia.minecraftadmin.components.serverstatebutton.ServerStateButton;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(ServerStateButton.class)
public class ServerStateButtonConnector extends AbstractComponentConnector {

	// ServerRpc is used to send events to server. Communication implementation
	// is automatically created here
	ServerStateButtonServerRpc rpc = RpcProxy.create(ServerStateButtonServerRpc.class, this);

	public ServerStateButtonConnector() {
		
		// To receive RPC events from server, we register ClientRpc implementation 
		registerRpc(ServerStateButtonClientRpc.class, new ServerStateButtonClientRpc(){

			@Override
			public void updateServerInfo(final String users, final String cpuLoad, final String memoryUsage) {
				getWidget().updateServerInfo(users, cpuLoad, memoryUsage);
			}

			@Override
			public void setServerName(final String name) {
				getWidget().setServerName(name);
			}
		});

		// We choose listed for mouse clicks for the widget
//		getWidget().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
//						.buildMouseEventDetails(event.getNativeEvent(),
//								getWidget().getElement());
//
//				// When the widget is clicked, the event is sent to server with ServerRpc
//				rpc.clicked(mouseDetails);
//			}
//		});

	}

	// We must implement getWidget() to cast to correct type 
	// (this will automatically create the correct widget type)
	@Override
	public ServerStateButtonWidget getWidget() {
		return (ServerStateButtonWidget) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public ServerButtonState getState() {
		return (ServerButtonState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// State is directly readable in the client after it is set in server
//		final String text = getState().text;
//		getWidget().setText(text);
	}

}
