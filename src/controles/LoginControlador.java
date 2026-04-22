package controles;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import modelo.UserModelo;
import views.LoginView;
import views.MainView;
import views.FormularioRegistro;

public class LoginControlador {

	private LoginView view;

	public LoginControlador(LoginView view) {
		this.view = view;
		addListeners();
	}

	private boolean validateCredentials(UserModelo user)
			throws InvalidUserException, InvalidPasswordException {

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
		;

		if (!user.getEmail().trim().isEmpty() && !user.getEmail().trim().equals("b.lara@uabcs.mx")) {
			throw new InvalidUserException("El correo no coincide.");
		}

		if (!user.getPassword().trim().isEmpty() && !user.getPassword().trim().equals("1234")) {
			throw new InvalidPasswordException("La contraseña no coincide");
		}

		return valid;
	}

	private void handleRegistration() {
		new RegistroControl(new FormularioRegistro());
		view.getWindow().dispose();
	}

	private void handleLogin() {
		
		UserModelo user = new UserModelo(
			view.getEmail(),
			view.getPassword()
		); 
		
		System.out.println(user.getName());
		
		try {
			if (validateCredentials(user)) {
				JOptionPane.showMessageDialog(view.getWindow(), "Se inició la sesión", "Sesión iniciada",
						JOptionPane.INFORMATION_MESSAGE);

				new HomeControl(new MainView());
				view.getWindow().dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException ex) {
			view.showPasswordError("Credenciales Incorrectas");
		}
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