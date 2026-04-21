package controles;

import java.util.List;

public class UserRegistration {
    private String name;
    private String email;
    private String country;
    private String gender;
    private String description;
    private List<String> languages;
    private boolean termsAccepted;

    public UserRegistration(String name, String email, String country, String gender, 
                            String description, List<String> languages, boolean termsAccepted) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.description = description;
        this.languages = languages;
        this.termsAccepted = termsAccepted;
    }

    // Getters para que el controlador pueda validar
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCountry() { return country; }
    public String getGender() { return gender; }
    public String getDescription() { return description; }
    public List<String> getLanguages() { return languages; }
    public boolean isTermsAccepted() { return termsAccepted; }
}