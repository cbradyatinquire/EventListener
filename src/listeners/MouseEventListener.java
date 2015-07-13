package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.nlogo.api.CompilerException;
import org.nlogo.app.App;
import org.nlogo.lite.InterfaceComponent;
import org.nlogo.window.View;

import events.EventsExtension;

public class MouseEventListener implements MouseListener {

	String callbackCommands;
	View myView;
	
	boolean isApp;
	App theApp;
	InterfaceComponent iComponent;
	
	
	public MouseEventListener(boolean inapp, App app, InterfaceComponent ic, 
			View view, String commands) {
		
		isApp = inapp;
		theApp = app;
		iComponent = ic;
		callbackCommands = commands;
		myView = view;
		myView.addMouseListener(this);
	}
	
	public void unsubscribe() {
		myView.removeMouseListener(this);
		EventsExtension.unregisterMouseListener(this);
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

	@Override
	public void mousePressed(MouseEvent e) {
		if (isApp) {
			try {
				theApp.commandLater(callbackCommands);
			} catch (CompilerException ce) {
				ce.printStackTrace();
			}
		} else {
			try {
				iComponent.commandLater(callbackCommands);
			} catch (CompilerException ce) {
				ce.printStackTrace();
			}
		}
		unsubscribe();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
