package events;

import java.util.concurrent.CopyOnWriteArrayList;

import listeners.MouseEventListener;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

import prims.ClearAllListeners;
import prims.CountListeners;
import prims.MakeMouseEvent;

public class EventsExtension extends DefaultClassManager {

	static public CopyOnWriteArrayList<MouseEventListener> MouseListeners = new CopyOnWriteArrayList<MouseEventListener>();
	
	static public void registerMouseListener( MouseEventListener mle ) {
		MouseListeners.add( mle );
	}
	
	static public void unregisterMouseListener( MouseEventListener mle ) {
		MouseListeners.remove( mle );
	}
	
	static public void clearAllListeners() {
		for (MouseEventListener mle : MouseListeners ) {
			mle.unsubscribe();
		}
		MouseListeners = new CopyOnWriteArrayList<MouseEventListener>();
	}
	
	
	@Override
	public void load(PrimitiveManager pm) throws ExtensionException {

		pm.addPrimitive("clear-listeners", new ClearAllListeners() );
		pm.addPrimitive("mouse-event", new MakeMouseEvent() );
		pm.addPrimitive("count-listeners", new CountListeners() );
	}

}
