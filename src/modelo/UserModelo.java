package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserModelo {
	
	private int id;
	private String name;
	private String email;
	private String country;
	private char gender;
	private String description;
	private List<String> Producto;
	private String password;
	private String imagePath;
	private String role;
	
	public UserModelo() {
		
		Producto = new ArrayList<>();
	}
	
	public UserModelo(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
		Producto = new ArrayList<>();
	}
	
	public UserModelo(String email, String password) {
		this.email = email;
		this.password = password;
		Producto = new ArrayList<>();
	}
	
	public UserModelo(String name, String email, String country, char gender, 
				String description, List<String> Producto, String imagePath, String role) {
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.description = description;
		this.Producto = Producto;
		this.imagePath = imagePath;
		this.role = role;
	}
	
	public UserModelo(int id, String name, String email, String country, char gender, 
			String description, List<String> Producto, String imagePath, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.description = description;
		this.Producto = Producto;
		this.imagePath = imagePath;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getProductos() {
		return Producto;
	}

	public void setProductos(List<String> producto) {
		this.Producto = Producto;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String toString() {
		return "Nombre: " + name +
		           "\nEmail: " + email +
		           "\nPaís: " + country +
		           "\nGénero: " + gender +
		           "\nDescripción: " + description +
		           "\nProductos:\n" +
		           String.join("\n", Producto);
	}
}
	