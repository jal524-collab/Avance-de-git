package controles;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.UserModelo;
import repositorio.UserRepositorio;
import repositorio.UserRepositorio;
import tableModel.UserTableModel;
import views.LoginWindow;
import views.MainView;
import views.MainView;

public class HomeControl {

	private MainView view;
	
	public HomeControl(MainView view) {
		
		this.view = view;
		registerListeners();
		
	}
	
	public void registerListeners( ) {
		
		view.mItemExit.addActionListener(e -> handleClose());
		
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		
		view.btnUsers.addActionListener(e -> {
			showUsers();
		});
		
		view.btnHome.addActionListener(e -> view.showView(MainView.HOME));
		
	}
	
	private void showUsers() {
		
		UserControl controller = new UserControl(view.usersPanel);
		
		UserRepositorio repository = new UserRepositorio();
		
		try {
			List<UserModelo> users = repository.getUsers();
			
			UserTableModel model = new UserTableModel(users);
			
			view.usersPanel.setTableModel(model);
			
			view.showView(MainView.USERS);
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
		
	}
	 
	private void handleClose() {
		/*int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());*/
			view.dispose();
		//}
	}
	
}