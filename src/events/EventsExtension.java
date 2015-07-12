package events;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

import prims.MakeMouseEvent;

public class EventsExtension extends DefaultClassManager {

	@Override
	public void load(PrimitiveManager pm) throws ExtensionException {

		pm.addPrimitive("mouse-event", new MakeMouseEvent() );
	}

}
