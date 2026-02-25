package views;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {

	public MainView() {
		
		setSize(600, 600);
		setTitle("Vista de menu");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMenu();
		setVisible(true);
		
	}
	
	public void setMenu() {
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu archivo = new JMenu("Archivo");
		archivo.addSeparator();
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);
		
		JMenuItem opcion1 = new JMenuItem("Opcion 1");
		archivo.add(opcion1);
		
		JMenuItem opcion2 = new JMenuItem("Opcion 2");
		archivo.add(opcion2);
		
		JMenu otraopcion = new JMenu("Otra opcion");
		mb.add(otraopcion);
		
		JMenuItem salir = new JMenuItem("Salir");
		otraopcion.add(salir);
		
		JMenuItem abrir = new JMenuItem("Abrir");
		otraopcion.add(abrir);
		
	}
	
}
