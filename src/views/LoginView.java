package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
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

import javax.imageio.*;
import java.io.*;

public class LoginView extends JPanel{
	
Font fuente;
JTextField emailField;
JPasswordField passwordField;
JLabel lblEmailRequired;
JLabel lblPasswordRequired;

	public LoginView() {
		

		fuente = new Font("Arial", Font.PLAIN, 14);
		setLayout(null);
		

		inicializarComponentes();
	}
	

	private void inicializarComponentes() {
		crearBotones();
		crearFormulario();
	}
	

	private void crearBotones() {
		
		LoginButton boton1 = new LoginButton("Iniciar Sesion");
		boton1.setBounds(250,320,160,60);
		boton1.setToolTipText("Haz click aquí");
		boton1.setFont(fuente);

		add(boton1);
		
		/*
		boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Ey! aqui estoy! :D", 
						"Sesion Iniciada", 
						JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		*/
		
		
		boton1.addActionListener(e -> handleLogin());
		
	}
	
	
	
	public void cargarFondo(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Image fondo = null;
		
		try {
			fondo = ImageIO.read(new File("src/img/fondo.jpg"));
			g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
		
	}

	private void crearFormulario() {
		
		int lblX = 10, y = 170, txtX = 150;
		

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(fuente);
		lblEmail.setBounds(lblX,y,200,40);
		add(lblEmail);
		

		emailField = new JTextField();
		new TextPrompt("Ingresa tu usuario", emailField);
		emailField.setFont(getFont());
		emailField.setBounds(txtX,y,200,40);
		add(emailField);
		

		
		
		JLabel lblEmailRequerido = new JLabel("El email es requerido.");
		lblEmailRequerido.setBounds(txtX, y+35, 200, 30);
		lblEmailRequerido.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmailRequerido.setForeground(Color.RED);
		add(lblEmailRequerido);
		
		
		y += 70;
		

		JLabel lblContrasena = new JLabel("Contrasena: ");
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(lblX,y,200,40);
		add(lblContrasena);
		

		passwordField = new JPasswordField();
		TextPrompt promptContrasena = new TextPrompt("Ingresa tu contraseña", passwordField);
		passwordField.setFont(fuente);
		passwordField.setBounds(txtX,y,200,40);
		add(passwordField);
		
		JLabel errorInicial = new JLabel("No existe ese correo");
		errorInicial.setBounds(txtX,y,300,15);
		errorInicial.setForeground(Color.RED);
		errorInicial.setFont(fuente);
		add(errorInicial);
		
		JLabel errorSegundo = new JLabel("Contrasenia erronea");
		errorSegundo.setBounds(txtX,y,300,15);
		errorSegundo.setForeground(Color.RED);
		errorSegundo.setFont(fuente);
		add(errorSegundo);
	}
	
	private void handleLogin() {
			
			if(validacion(emailField.getText(), String.valueOf(passwordField.getPassword()))) {
				JOptionPane.showMessageDialog(
					this,
					"Se inicio la sesion", 
					"Sesion iniciada", 
					JOptionPane.INFORMATION_MESSAGE
				);
			}
		}
	
	private void showEmailError(String message) {
		lblEmailRequired.setText(message);
		lblEmailRequired.setVisible(true);
	}
	
	private void showPasswordError(String message) {
		lblPasswordRequired.setText(message);
	}
	
	private void resetErrorMessages() {
		lblEmailRequired.setText("");
		lblPasswordRequired.setText("");
	}
		
	private boolean validacion(String email, String password) {
		
		resetErrorMessages();
		
		if(email.trim().isEmpty()) {
			showEmailError("El correo es obligatorio");
			return false;
		}
				
		if(password.trim().isEmpty()) {
			showPasswordError("La contrasena es obligatoria");
			return false;
		};
		
		return true;
		}

}
