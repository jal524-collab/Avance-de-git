package controles;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import views.MainView;

public class HomeControl {

	private MainView view;
	private UserControl userController;
	
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
		
		view.btnHome.addActionListener(e -> {
			view.showView(MainView.HOME);
			updateMenuState(MainView.HOME);
		});
		
	}
	
	private void showUsers() {
		if(userController == null) {
			userController = new UserControl(view.usersPanel);
		}
			
		userController.loadUsers();
		
		view.showView(MainView.USERS);
		updateMenuState(MainView.USERS);
		
	}
	
	private void handleClose() {
		/*int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());*/
			view.dispose();
		//}
	}
	
	private void updateMenuState(String viewName) {
		view.btnUsers.setEnabled(!viewName.equals(MainView.USERS));
		view.btnHome.setEnabled(!viewName.equals(MainView.HOME));
	}
	
}