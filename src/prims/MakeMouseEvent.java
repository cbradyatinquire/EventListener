package prims;


import javax.swing.JComponent;

import listeners.MouseEventListener;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.app.App;
import org.nlogo.lite.InterfaceComponent;
import org.nlogo.window.GUIWorkspace;
import org.nlogo.window.View;

import events.EventsExtension;


public class MakeMouseEvent extends DefaultCommand  {

	String eventType = "MouseDown";	
	
	@Override
    public Syntax getSyntax() {
       int[] argTypes = {Syntax.StringType(), Syntax.StringType()};
       return  Syntax.commandSyntax(argTypes);
    }
	
	@Override
	public void perform(Argument[] args, Context ctxt)
			throws ExtensionException, LogoException {

		//String etype = args[0].getString(); //eventually use this to choose which type of listener to spawn.
		//for now, we only have MouseEvents (and only mousedown, actually).
		
		//set up basics.
		String commands = args[1].getString();
		
		//now we need to get the view that we will ask the listener to subscribe to events from
		org.nlogo.nvm.ExtensionContext context = (org.nlogo.nvm.ExtensionContext)ctxt;
		GUIWorkspace ws = (GUIWorkspace)(context.workspace());
		View view = ws.view;
		
		//finally, we need the object we'll call into
		//if we're in the App case, we can grab a class that can run our commandLater directly
		//if not, we can assume we're in an embedding API context, so walk up to find the InterfaceComponent
		boolean isapp = true;
		App theapp = null;
		InterfaceComponent interfacecomponent = null;

		try {
			theapp = App.app();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (theapp == null) {
			isapp = false;
			interfacecomponent = findInterfaceComponent((JComponent)ws.getWidgetContainer());
		}

		MouseEventListener newListener = new MouseEventListener( isapp, theapp, interfacecomponent, view, commands);
		EventsExtension.registerMouseListener(newListener);
	}
	
	
	public InterfaceComponent findInterfaceComponent( JComponent p ) {
		while (! (p instanceof InterfaceComponent) )
		{
			p = (JComponent)p.getParent();
		}
		InterfaceComponent ic = (InterfaceComponent)p;
		return ic;
	}


}
