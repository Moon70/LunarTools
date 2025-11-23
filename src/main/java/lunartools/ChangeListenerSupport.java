package lunartools;

import java.util.List;

/**
 * Adds ChangeListener support to a class.
 * <br>The class has to implement <code>getListeners()</code> and provide the listeners ArrayList:
 * 
 * <code><pre>
 * private final List<ChangeListener> listeners = new ArrayList<>();
 * 	
 * public List<ChangeListener> getListeners() {
 *  return listeners;
 * }
 * </pre></code>
 */
public interface ChangeListenerSupport {

	List<ChangeListener> getListeners();

	default public void addChangeListener(ChangeListener listener) {
		synchronized (getListeners()) {
			getListeners().add(listener);
		}
	}

	default public void removeChangeListener(ChangeListener listener) {
		synchronized (getListeners()) {
			getListeners().remove(listener);
		}
	}

	default void notifyListeners(Object object) {
		synchronized (getListeners()) {
			for (ChangeListener listener:getListeners()) {
				listener.changed(object);
			}
		}
	}

	interface ChangeListener {
		void changed(Object object);
	}

}
