package prims;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.nlogo.api.Argument;
import org.nlogo.api.CompilerException;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.app.App;
import org.nlogo.lite.InterfaceComponent;
import org.nlogo.window.GUIWorkspace;
import org.nlogo.window.View;


public class MakeMouseEvent extends DefaultCommand implements MouseListener {

	
	String toExecute;
	String eventType = "MouseDown";
	
	Context runContext;
	InterfaceComponent myInterface;
	String header, inner, footer;
	
	boolean isApp = true;
	App theApp;
	InterfaceComponent interfaceComponent;
	View myView;
	
	@Override
    public Syntax getSyntax() {
       int[] argTypes = {Syntax.StringType(), Syntax.StringType()};
       return  Syntax.commandSyntax(argTypes);
    }
	
	@Override
	public void perform(Argument[] args, Context ctxt)
			throws ExtensionException, LogoException {

		String etype = args[0].getString(); //eventually put this in eventType;
		toExecute = args[1].getString();
		
		org.nlogo.nvm.ExtensionContext context = (org.nlogo.nvm.ExtensionContext)ctxt;
		runContext = context;
		
		GUIWorkspace ws = (GUIWorkspace)(context.workspace());
		myView = ws.view;
		myView.addMouseListener(this);
		
		
		try {
			theApp = App.app();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (theApp == null) {
			isApp = false;
			interfaceComponent = findInterfaceComponent((JComponent)ws.getWidgetContainer());
		}

	}
	
	
	public InterfaceComponent findInterfaceComponent( JComponent p ) {
		
		while (! (p instanceof InterfaceComponent) )
		{
			p = (JComponent)p.getParent();
		}
		InterfaceComponent ic = (InterfaceComponent)p;
		return ic;
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (isApp) {
			try {
				theApp.commandLater(toExecute);
			} catch (CompilerException e) {
				e.printStackTrace();
			}
		} else {
			try {
				interfaceComponent.commandLater(toExecute);
			} catch (CompilerException e) {
				e.printStackTrace();
			}
		}
		myView.removeMouseListener(this);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
