package tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.UserModelo;

public class UserTableModel extends AbstractTableModel{

	private List<UserModelo> users;
	
	private final String[] columns = {
		"Nombre",
		"Email",
		"País",
		"Género",
		"Lenguajes"
	};
	
	public UserTableModel(List<UserModelo> users) {
		this.users = users;
	}
	
	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		UserModelo user = users.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return user.getName();
		case 1:
			return user.getEmail();
		case 2:
			return user.getCountry();
		case 3:
			return user.getGender();
		case 4:
			return String.join(", ", user.getLanguages());
		}
		
		return null;
	}
	
	public UserModelo getUserAt(int row) {
		return users.get(row);
	}
	
	public void setUsers(List<UserModelo> users) {
		this.users = users;
		fireTableDataChanged();
	}
		
}