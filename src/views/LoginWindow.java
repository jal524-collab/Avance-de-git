package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LoginWindow extends JFrame { 
	public LoginWindow() {
	    // 1. Obtener la instancia correcta del Toolkit
	    Toolkit tk = Toolkit.getDefaultToolkit(); 
	    Image icono = tk.getImage("src/img/icono.png");
	    setIconImage(icono);

	    // 2. Configuración esencial de la ventana
	    setTitle("Login Amazon");
	    setSize(500, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null); // Esto la centra en pantalla

	    // 3. Agregar SOLO el panel de Login (comenta el de GridBag por ahora)
	    LoginView panelito = new LoginView();
	    add(panelito);
	    
	   
	    
	    setVisible(true);
	}
	
}
