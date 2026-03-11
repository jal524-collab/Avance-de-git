package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import views.ejemplos.BorderPanel;

public class LoginWindow extends JFrame { 
	public LoginWindow() {
	    // 1. Obtener la instancia correcta del Toolkit
	    Toolkit tk = Toolkit.getDefaultToolkit(); 
	    Image icono = tk.getImage("src/img/icono.png");
	    setIconImage(icono);

	    // 2. Configuración esencial de la ventana
	    setTitle("Login Amazon");
	    setSize(500, 600);
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    setLocationRelativeTo(null); 
	    
	    addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent e) {
	            cerrarVentana();
	        }
	    });
	    
	    // Esto la centra en pantalla

	    // 3. Agregar SOLO el panel de Login (comenta el de GridBag por ahora)
	    
	  /*  BorderPanel panel = new BorderPanel(this);
	    add(panel);
	    */
	   LoginView loginView = new LoginView(this);
	    add(loginView);
	    
	    setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		cerrarVentana();
		
	}
	
	private void cerrarVentana() {
		
		int option = JOptionPane.showConfirmDialog(
	            this, 
	            "Seguro que quieres salir? Se perderan todos los datos", 
	            "Segurisimo?", 
	            JOptionPane.YES_NO_OPTION, 
	            JOptionPane.WARNING_MESSAGE
	    );
		
		if (option == JOptionPane.YES_OPTION) {
	        System.exit(0);
		}
	}
	
}
