package de.lycantrophia.minecraftadmin.frontend.design;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class ServerHeartBeatDesign extends VerticalSplitPanel {
	protected CssLayout layoutGraph;
	protected Button buttonStart;
	protected Button buttonRestart;
	protected Button buttonStop;
	protected Button buttonKill;
	protected TextArea textAreaServerLog;
	protected TextField textFieldServerCommand;
	protected Button buttonSendCommand;
	protected TextField textFieldNewCommand;
	protected Button buttonAdd;
	protected Tree treeCommandsList;
	protected Button buttonDelete;
	protected Button buttonCopy;

	public ServerHeartBeatDesign() {
		Design.read(this);
	}
}
