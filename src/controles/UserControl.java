package controles;

import javax.swing.table.DefaultTableModel;

import repositorio.UserRepositorio;
import repositorio.UserRepositorio;
import tableModel.UserTableModel;
import views.UserFormDialog;
import views.UserView;
import views.UserView;

public class UserControl {

	private UserView view;
	private UserRepositorio repo;
	private DefaultTableModel model;
	
	public UserControl(UserView view) {
		this.view = view;
		repo = new UserRepositorio();
		
		view.getBtnAdd().addActionListener(e -> {
			UserFormDialog form = new UserFormDialog(null, null);
			form.setVisible(true);
		});
	}
	
}