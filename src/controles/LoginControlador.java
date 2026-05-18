package controles;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import modelo.UserModelo;
import repositorio.LoginRepositorio;
import utils.Session;
import views.LoginView;
import views.MainView;
import views.FormularioRegistro;

public class LoginControlador {

	private LoginView view;
	private LoginRepositorio repository;

	public LoginControlador(LoginView view) {
		repository = new LoginRepositorio();
		this.view = view;
		addListeners();
	}

	private boolean validateCredentials(UserModelo user) {

		view.resetErrorMessages();

		boolean valid = true;

		if (user.getEmail().trim().isEmpty()) {
			view.showEmailError("El correo es obligatorio");
			valid = false;
		}

		if (user.getPassword().trim().isEmpty()) {
			view.showPasswordError("La contraseña es obligatoria");
			valid = false;
		}

		return valid;
	}

	private void handleRegistration() {
		new RegistroControl(new FormularioRegistro());
		view.getWindow().dispose();
	}

	private void handleLogin() {
		
		if(!validateCredentials(new UserModelo(view.getEmail(), view.getPassword()))){
			return;
		}
		
		UserModelo user = repository.login(view.getEmail(), view.getPassword());
		
		if(user == null) {
			view.showPasswordError("Credenciales incorrectas");
			return;
		}
		
		Session.login(user);
		
		//JOptionPane.showMessageDialog(view.getWindow(),  "Se inició la sesión", "Sesión iniciada", JOptionPane.INFORMATION_MESSAGE);
		
		if(Session.getRole().equals("ADMIN")) {
			new HomeControl(new MainView());			
			
		}else {
			JOptionPane.showMessageDialog(view.getWindow(), "No tienes permisos");
		}
		
		view.getWindow().dispose();
	}
	
	private void addListeners() {
				
		KeyAdapter enterListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleLogin();
				}
			}
		};
		
		view.getPasswordField().addKeyListener(enterListener);
		view.getEmailField().addKeyListener(enterListener);
		
		view.getBtnLogin().addActionListener(e-> handleLogin());
		
		view.getLblRegister().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleRegistration();
			}
		});
	}

}