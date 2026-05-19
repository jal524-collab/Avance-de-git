package repositorio;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import config.DataBaseConnection;
import modelo.UserModelo;

public class UserRepositorio {

	public void save(UserModelo user) throws IOException {
	
		List<UserModelo> users = getUsers();
		users.add(user);
		
	}
	
	public List<UserModelo> getUsers() throws IOException {
		
		List<UserModelo> users = new ArrayList<UserModelo>();
		System.out.println("lista");
		
		try(
			Connection connection = DataBaseConnection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users"); 
		) {
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				UserModelo user = new UserModelo(
					rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("email"),
					rs.getString("country"),
					rs.getString("gender").charAt(0),
					rs.getString("description"),
					Arrays.asList(rs.getString("producto").split("\\|")),
					rs.getString("image_path"),
					rs.getString("role")
				);
				users.add(user);
			}
			
		}catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return users;		
	}
	
	public boolean delete(int id) {
		
		String sql = "DELETE FROM users WHERE id = ?";
		
		try(Connection connection = DataBaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Se eliminó");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
		
	}
	
	
	
	/*
	 * Cambié para que retorne si se pudo actualizar o no
	 */
	public boolean update(int index, UserModelo updatedUser) throws IOException {
		//Ya no actualizamos la tabla completa
		//List<User> users = getUsers();
		//users.set(index, updatedUser);
		
		String sql = "UPDATE users SET name = ?, email = ?, country = ?,"
				+ " description = ?, productos = ?, gender = ?, role = ? "
				+ "WHERE id = ?";
		
		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, updatedUser.getName());
			pst.setString(2, updatedUser.getEmail());
			pst.setString(3, updatedUser.getCountry());
			pst.setString(4, updatedUser.getDescription());
			pst.setString(5, String.join("|", updatedUser.getProductos()));
			pst.setString(6, String.valueOf(updatedUser.getGender()));
			pst.setString(7, updatedUser.getRole());
			pst.setInt(8, updatedUser.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				return true;
			}			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
			
}