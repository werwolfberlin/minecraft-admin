package de.lycantrophia.minecraftadmin.components.serverstatebutton.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class ServerStateButtonWidget extends FlexTable {

	private final Label serverName =  new Label();
	private final Label usersLabel = new Label();
	private final Label cpuLoadLabel = new Label();
	private final Label memUsageLabel = new Label();

	public ServerStateButtonWidget() {
		setBorderWidth(0);
		setCellSpacing(0);
		setCellPadding(0);
		// CSS class-name should not be v- prefixed
		setStyleName("server-state-button");

		final FlexCellFormatter cellFormatter = getFlexCellFormatter();
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		setWidget(0, 0, serverName);

		setText(1, 0, "Users:");
		setWidget(1, 1, usersLabel);

		setText(1, 0, "CPU:");
		setWidget(1, 1, cpuLoadLabel);

		setText(2, 0, "Memory:");
		setWidget(2, 1, memUsageLabel);
	}

	public void updateServerInfo(final String users, final String cpuLoad, final String memoryUsage) {
		usersLabel.setText(users);
		cpuLoadLabel.setText(cpuLoad);
		memUsageLabel.setText(memoryUsage);
	}

	public void setServerName(String name) {
		serverName.setText(name);
	}
}