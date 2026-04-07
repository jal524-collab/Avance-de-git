package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.LoginButton;
import components.RoundButton;
import components.TextPrompt;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import views.ejemplos.BorderPanel;

import javax.imageio.*;
import java.io.*;

public class LoginView extends JPanel{
	
LoginWindow window;
Image fondo;
Font fuente;
JTextField emailField;
JPasswordField passwordField;
JLabel correo;
JLabel contrasena;

	public LoginView(LoginWindow window) {
		

		this.window = window;
		fuente = new Font("Arial", Font.PLAIN, 14);
		setLayout(null);
		

		inicializarComponentes();
	}
	

	private void inicializarComponentes() {
		crearBotones();
		crearFormulario();
		cargarFondo();
	}
	

	private void crearBotones() {
		
		LoginButton boton1 = new LoginButton("Iniciar Sesion");
		boton1.setBounds(250,320,160,60);
		boton1.setToolTipText("Haz click aquí");
		boton1.setFont(fuente);
		
		boton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton1.addMouseListener(new MouseAdapter() {
			
	        public void mouseEntered(MouseEvent e) {

	            boton1.setBackground(Color.BLACK);
	            boton1.repaint();
	        }


	        public void mouseExited(MouseEvent e) {

	            boton1.setBackground(Color.LIGHT_GRAY); 
	            boton1.repaint();
	        }
	    });

	    add(boton1);
	    boton1.addActionListener(e -> handleLogin());
	}
		
	protected void handleRegistration() {
		// TODO Auto-generated method stub
		
	}


	public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
					
		}
	
	public void cargarFondo() {
		
		try {
			fondo = ImageIO.read(getClass().getResource("/img/fondo.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	
	}

	private void crearFormulario() {
		
		JLabel lblGreeting = new JLabel("Bienvenido!");
		lblGreeting.setFont(fuente);
		lblGreeting.setBounds(10,0,200,40);
		add(lblGreeting);
		
		int labelX = 10, positionY = 170, textX = 150;
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(fuente);
		lblEmail.setBounds(labelX,positionY,200,40);
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setBackground(Color.white);
		lblEmail.repaint();
		add(lblEmail);
		
		emailField = new JTextField();
		new TextPrompt("Ingresa tu usuario", emailField);
		emailField.setFont(fuente);
		emailField.setBounds(textX,positionY,200,40);
		add(emailField);
		
		correo = new JLabel("El email es requerido.");
		correo.setBounds(textX, positionY+35, 200, 30);
		correo.setFont(new Font("Arial", Font.BOLD, 10));
		correo.setForeground(Color.RED);
		correo.setVisible(false);
		add(correo);
		
		positionY += 70;
		
		JLabel lblPasswordLabel = new JLabel("Contraseña: ");
		lblPasswordLabel.setFont(fuente);
		lblPasswordLabel.setBounds(labelX,positionY,200,40);
		lblPasswordLabel.setForeground(Color.LIGHT_GRAY);
		lblPasswordLabel.setBackground(Color.white);
		lblPasswordLabel.repaint();
		add(lblPasswordLabel);
		
		passwordField = new JPasswordField();
		new TextPrompt("Ingresa tu contraseña", passwordField);
		passwordField.setFont(fuente);
		passwordField.setBounds(textX,positionY,200,40);
		add(passwordField);
		
		contrasena = new JLabel("");
		contrasena.setBounds(textX, positionY+35, 200, 30);
		contrasena.setFont(new Font("Arial", Font.BOLD, 10));
		contrasena.setForeground(Color.RED);
		add(contrasena);
	}
	
	private void handleLogin() {
			
		
		try {
			if(validacion(emailField.getText(), String.valueOf(passwordField.getPassword()))) {
				JOptionPane.showMessageDialog(
					this,
					"Se inicio la sesion", 
					"Sesion iniciada", 
					JOptionPane.INFORMATION_MESSAGE
				);
				
				new MainView();
				window.dispose();
			}
			
		}catch(InvalidPasswordException ex) {
				JOptionPane.showMessageDialog(
						this,
						"eStA mAl eN aLgO, nI mOdO", 
						"EEGGHH, EsTa MaL", 
						JOptionPane.ERROR_MESSAGE
					);
		}
		
		catch(InvalidUserException ex) {
			JOptionPane.showMessageDialog(
					this,
					"eStA mAl eN aLgO, nI mOdO", 
					"EEGGHH, EsTa MaL", 
					JOptionPane.ERROR_MESSAGE
				);
		}
	}
	
	private void showEmailError(String message) {
		correo.setText(message);
		correo.setVisible(true);
	}
	
	private void showPasswordError(String message) {
		contrasena.setText(message);
	}
	
	private void resetErrorMessages() {
		correo.setText("");
		contrasena.setText("");
	}
		
	private boolean validacion(String email, String password) 
			throws InvalidUserException, InvalidPasswordException {
		
		resetErrorMessages();
		
		if(email.trim().isEmpty()) {
			showEmailError("El correo es obligatorio");
			return false;
		}
				
		if(password.trim().isEmpty()) {
			showPasswordError("La contrasena es obligatoria");
			return false;
		};
		
		if(!email.trim().isEmpty() && !email.trim().equals("Javier Arturo Lopez")) {
			throw new InvalidUserException();
		}
				
		if(!password.trim().isEmpty() && !password.trim().equals("1234")) {
			throw new InvalidPasswordException();
		};
		
		return true;
		}
	
	
}
