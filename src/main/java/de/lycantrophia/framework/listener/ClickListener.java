package de.lycantrophia.framework.listener;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface ClickListener<T> extends Serializable {

    static Method getButtonClickMethod(Class<?> listenerClazz, Class<?> eventClazz)
    {
        return ReflectTools.findMethod(listenerClazz, "buttonClick", eventClazz);
    }

    void buttonClick(ClickEvent<T> event);
}
