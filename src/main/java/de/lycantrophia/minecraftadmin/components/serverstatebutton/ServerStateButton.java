package de.lycantrophia.minecraftadmin.components.serverstatebutton;

import com.vaadin.ui.Button;

import de.lycantrophia.minecraftadmin.components.serverstatebutton.client.ServerStateButtonClientRpc;

// This is the server-side UI component that provides public API 
// for ServerStateButton
public class ServerStateButton extends Button { //AbstractComponent {

//	private int clickCount = 0;

	// To process events from the client, we implement ServerRpc
//	private ServerStateButtonServerRpc rpc = new ServerStateButtonServerRpc() {
//
//		@Override
//		public void clicked(MouseEventDetails mouseEventDetails) {
//			fireClick(mouseEventDetails);
//		}
//
//		@Override
//		public void disableOnClick() throws RuntimeException {
//			setEnabled(false);
//			// Makes sure the enabled=false state is noticed at once - otherwise
//			// a following setEnabled(true) call might have no effect. see
//			// ticket #10030
//			getUI().getConnectorTracker().getDiffState(Button.this)
//					.put("enabled", false);
//		}
//	};

//	public ServerStateButton() {
//
//
//		// To receive events from the client, we register ServerRpc
//		registerRpc(rpc);
//	}

	public void updateServerInfo(final String users, final String cpuLoad, final String memoryUsage)
	{
		this.getRpcProxy(ServerStateButtonClientRpc.class).updateServerInfo(users, cpuLoad, memoryUsage);
	}

	public void setServerName(final String name)
	{
		this.getRpcProxy(ServerStateButtonClientRpc.class).setServerName(name);
	}
}
