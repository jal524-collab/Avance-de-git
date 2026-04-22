package repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import modelo.UserModelo;

public class UserRepositorio {

	private final String FILE = "src/assets/files/users.csv";
	
	public void save(UserModelo user) throws IOException {
		
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8))) {
			writer.write(user.toCsv());
			writer.newLine();
		}
		
	}
	
	public List<UserModelo> getUsers() throws IOException {
		
		List<UserModelo> users = new ArrayList<UserModelo>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
			String line;
			
			while((line = reader.readLine()) != null) {
				UserModelo user = UserModelo.fromCsv(line);
				users.add(user);
			}
		}
		
		return users;
		
	}
	
	public void updateAll(List<UserModelo> users) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(new FileOutputStream(FILE), StandardCharsets.UTF_8))) {

	        for (UserModelo user : users) {
	            writer.write(user.toCsv());
	            writer.newLine();
	        }
	    }
	}
	
	public void delete(int index) throws IOException {
		List<UserModelo> users = getUsers();
		users.remove(index);
		updateAll(users);
	}
	
	public void update(int index, UserModelo updatedUser) throws IOException {
		List<UserModelo> users = getUsers();
		users.set(index, updatedUser);
		updateAll(users);
	}
	
}