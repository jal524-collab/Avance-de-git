package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LoginButton extends JButton{
	
	Image caja;
	
	public LoginButton(String text) {
		super(text);
		cargarImagen();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	
		g2.drawImage(caja, 0, 0, getWidth(),  getHeight(), null);
	}
	
	public void cargarImagen() {
		try {
			caja = ImageIO.read(getClass().getResource("/img/caja.png"));
			
			caja = caja.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
						
		}catch(Exception ex) {
			System.out.println("No hay imagen");
		}
	}
	
}
