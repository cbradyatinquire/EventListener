package prims;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

import events.EventsExtension;

public class ClearAllListeners extends DefaultCommand {

	@Override
	public void perform(Argument[] arg0, Context arg1)
			throws ExtensionException, LogoException {
		EventsExtension.clearAllListeners();
	}

}
