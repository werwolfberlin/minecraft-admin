package de.lycantrophia.client;

import com.vaadin.shared.communication.ClientRpc;

// ClientRpc is used to pass events from server to client
// For sending information about the changes to component state, use State instead
public interface ServerStateButtonClientRpc extends ClientRpc {
	void setServerName(String serverName);
	void setMaxRam(int maxRam);
}