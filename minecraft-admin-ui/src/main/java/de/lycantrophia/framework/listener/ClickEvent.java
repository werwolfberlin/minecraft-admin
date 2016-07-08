package de.lycantrophia.framework.listener;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Component;

public class  ClickEvent<T>  extends Component.Event {
    private final MouseEventDetails details;

    public ClickEvent(Component source) {
        super(source);
        this.details = null;
    }

    public ClickEvent(Component source, MouseEventDetails details) {
        super(source);
        this.details = details;
    }

    public T getButton() {
        //noinspection unchecked
        return (T)this.getSource();
    }

    public int getClientX() {
        return null != this.details?this.details.getClientX():-1;
    }

    public int getClientY() {
        return null != this.details?this.details.getClientY():-1;
    }

    public int getRelativeX() {
        return null != this.details?this.details.getRelativeX():-1;
    }

    public int getRelativeY() {
        return null != this.details?this.details.getRelativeY():-1;
    }

    public boolean isAltKey() {
        return null != this.details && this.details.isAltKey();
    }

    public boolean isCtrlKey() {
        return null != this.details && this.details.isCtrlKey();
    }

    public boolean isMetaKey() {
        return null != this.details && this.details.isMetaKey();
    }

    public boolean isShiftKey() {
        return null != this.details && this.details.isShiftKey();
    }
}