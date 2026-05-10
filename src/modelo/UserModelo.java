package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.exceptions.IOException;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModelo {
    private String name;
    private List<String> productos;
	private String email;
	private String country;
	private char gender;
	private String description;
	
	private String password;
	private String imagePath;
	
	public UserModelo() {
		
		productos = new ArrayList<>();
	}
	
	public UserModelo(String email, String password) {
		this.email = email;
		this.password = password;
		productos = new ArrayList<>();
	}
	
	public UserModelo(String name, String email, String country, char gender, 
						String description, List<String> productos, String imagePath) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.description = description;
		this.productos = productos;
		this.imagePath = imagePath;
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
		return productos;
	}

	public void setProductos(List<String> productos) {
		this.productos = productos;
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
		           String.join("\n", productos);
	}
	
	public String toCsv() {
		return name + "," +
		           email + "," +
		           country + "," +
		           gender + "," +
		           description + "," +
		           String.join("|", productos);
	}
	
	public static UserModelo fromCsv(String userData) {
		String data[] = userData.split(",");
		
		String name = data[0];
		String email = data[1];
	    String country = data[2];
	    char gender = data[3].charAt(0);
	    String description = data[4];
	    
	    List<String> languages = new ArrayList<String>();
	    
	    if(data.length > 5) {
	    	languages = Arrays.asList(data[5].split("\\|"));
	    }
	    
	    return new UserModelo(name, email, country, gender, description, languages, null);
		
	}
	
	
}