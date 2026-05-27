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
import utils.PasswordUtils;
import views.FormularioRegistro;
import views.LoginWindow;
import views.MainView;

public class Main {

	public static void main(String[] args) {
			
		String pass = PasswordUtils.hashPassword("12345");
		System.out.println(pass);
		
		String empleado = PasswordUtils.hashPassword("54321");
		System.out.println(empleado);
		//new HomeControl(new MainView());
		new LoginControlador(new LoginWindow().getLoginView());
		//new RegistroControl(new FormularioRegistro());
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