package com.lgposse.game.models;

import java.io.Serializable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public abstract class Model implements Serializable {
	private static final long serialVersionUID = -7940669231735821911L;
	private final EventListenerList listenerList = new EventListenerList();
	private final ChangeEvent stateChangeEvent = new ChangeEvent(this);

	public void addChangeListener(ChangeListener l) {
	    listenerList.add(ChangeListener.class, l);
	}
	public void removeChangeListener(ChangeListener l) {
	    listenerList.remove(ChangeListener.class, l);
	}
	protected void fireChange() {
	    for (ChangeListener l: listenerList.getListeners(ChangeListener.class)) {
	        l.stateChanged(stateChangeEvent);
	    }
	}
}
