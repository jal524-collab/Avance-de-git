package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		

		JTextField txtEmail = new JTextField();
		TextPrompt promptEmail = new TextPrompt("Ingresa tu usuario", txtEmail);
		txtEmail.setFont(fuente);
		txtEmail.setBounds(txtX,y,200,40);
		add(txtEmail);
		

		
		
		JLabel lblEmailRequerido = new JLabel("El email es requerido.");
		lblEmailRequerido.setBounds(txtX, y+35, 200, 30);
		lblEmailRequerido.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmailRequerido.setForeground(Color.RED);
		add(lblEmailRequerido);
		
		
		y += 70;
		

		JLabel lblContrasena = new JLabel("Contraseña: ");
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(lblX,y,200,40);
		add(lblContrasena);
		

		JPasswordField contrasena = new JPasswordField();
		TextPrompt promptContrasena = new TextPrompt("Ingresa tu contraseña", contrasena);
		contrasena.setFont(fuente);
		contrasena.setBounds(txtX,y,200,40);
		add(contrasena);
		
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
	

}
