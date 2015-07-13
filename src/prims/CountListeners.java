package prims;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

import events.EventsExtension;

public class CountListeners extends DefaultReporter {

	@Override
	public Object report(Argument[] arg0, Context arg1)
			throws ExtensionException, LogoException {
		return new Double( EventsExtension.MouseListeners.size() );
	}

}
