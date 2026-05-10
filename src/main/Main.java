package main;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.UIManager;

import controles.HomeControl;
import controles.LoginControlador;
import controles.RegistroControl;
import utils.AppFont;
import views.FormularioRegistro;
import views.LoginWindow;
import views.MainView;

public class Main {

	public static void main(String[] args) {
			
		
		//new HomeControl(new MainView());
		new LoginControlador(new LoginWindow().getLoginView());
		//new RegistroControl(new FormularioRegistro());
		//showOnScreen(1, ventanita);
	}
	
	public static void showOnScreen(int screen, JFrame frame) {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();

	    if (screen > -1 && screen < gd.length) {

	        Rectangle bounds = gd[screen].getDefaultConfiguration().getBounds();

	        int x = bounds.x + (bounds.width - frame.getWidth()) / 2;
	        int y = bounds.y + (bounds.height - frame.getHeight()) / 2;

	        frame.setLocation(x, y);

	    } else {
	        throw new RuntimeException("No se encontró la pantalla");
	    }
	}


}