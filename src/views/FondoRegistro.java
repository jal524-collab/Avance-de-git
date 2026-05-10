package views;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class FondoRegistro extends JPanel {
    private Image imagen;

    public FondoRegistro(String ruta) {
        
        URL url = getClass().getResource(ruta);
        if (url != null) {
            this.imagen = new ImageIcon(url).getImage();
        } else {
            System.err.println("No se pudo encontrar la imagen en: " + ruta);
        }
        
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
        super.paintComponent(g); 
    }
}