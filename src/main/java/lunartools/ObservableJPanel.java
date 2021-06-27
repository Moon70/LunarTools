package lunartools;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * To use the observer pattern together with a JPanel.
 * <br>It´s not possible to extend both JPanel and Observable, because you can´t extend two classes in Java.
 * <br>Extend this class instead of JPanel, it contains an Observable object and offers its methods.
 * 
 * @author Thomas Mattel
 */
public class ObservableJPanel extends JPanel{

	private class InternalObservable extends Observable{
		public void clearChanged(){
			super.clearChanged();
		}

		public void setChanged(){
			super.setChanged();
		}
	}

	private InternalObservable observable=new InternalObservable();

	protected void clearChanged(){
		observable.clearChanged();
	}

	public void addObserver(Observer observer){
		observable.addObserver(observer);
	}

	public int countObservers(){
		return observable.countObservers();
	}

	public void deleteObserver(Observer observer){
		observable.deleteObserver(observer);
	}

	public void deleteObserver(){
		observable.deleteObservers();
	}

	public boolean hasChanged(){
		return observable.hasChanged();
	}

	public void notifyObservers(){
		observable.notifyObservers();
	}

	public void notifyObservers(Object object){
		observable.notifyObservers(object);
	}

	protected void setChanged(){
		observable.setChanged();
	}

}
