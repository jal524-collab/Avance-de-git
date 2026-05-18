package controles;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.UserModelo;
import repositorio.UserRepositorio;
import views.LoginWindow;
import views.MainView;
import views.FormularioRegistro;

public class RegistroControl {

    private FormularioRegistro view;
    private UserRepositorio repository;

    public RegistroControl(FormularioRegistro view){
        this.view = view;
        this.repository = new UserRepositorio();
        registerListeners();
    }

    private void registerListeners(){

        view.getBtnValidate().addActionListener(e -> {

            if(validateForm()){
            	
            	String imagePathString = saveImage();

                UserModelo user = new UserModelo(
                        view.getUserName(),
                        view.getEmail(),
                        view.getCountry(),
                        view.getGender(),
                        view.getDescription(),
                        view.getProductos(),
                        imagePathString,
                        "ADMIN"
                );
                
                registerUser(user);
                
                new HomeControl(new MainView());
                view.dispose();

            }

        });

        view.getBtnReturn().addActionListener(e -> {

            int option = view.confirmReturn();

            if(option == JOptionPane.YES_OPTION){
                new LoginControlador(new LoginWindow().getLoginView());
                view.dispose();
            }

        });

        view.getTxtName().addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent e){

            	char c = e.getKeyChar();
            	
                if(!Character.isAlphabetic(c) && e.getKeyChar() != ' '){
                    e.consume();
                }

                if(Character.isLowerCase(c)){
                	e.setKeyChar(Character.toUpperCase(c));
                }
                
                //Que no tenga más de 10 caracteres
                /*if(view.getTxtName().getText().length() >= 10){
                    e.consume();
                }*/
            }

            @Override
            public void keyPressed(KeyEvent e){

                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    view.getTxtEmail().requestFocusInWindow();
                }

            }

        });

        view.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent e){
                view.getTxtName().requestFocusInWindow();
            }

        });

        view.getTxtName().getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
                validateName();
            }

            public void removeUpdate(DocumentEvent e){
                validateName();
            }

            public void changedUpdate(DocumentEvent e){
                validateName();
            }

        });

        view.getCboCountry().addActionListener(e -> validateCountry());

        view.getChkTerms().addActionListener(e -> validateTerms());

        view.getLstProductos().addListSelectionListener(e -> validateProductos());
        
        view.getBtnSelectImage().addActionListener(e -> view.chooseImage());

    }
    
    private void registerUser(UserModelo user) {
    	
    	try {
    		repository.save(user);
    		
    		JOptionPane.showMessageDialog(view, "Usuario registrado");
    		
    	}catch(IOException e) {
    		JOptionPane.showMessageDialog(view, e.getMessage());
    	}
    	
    }
    
    private String saveImage() {
    	try {
    		String original = view.getSelectedImagePath();
    		
    		if(original == null)
    			return null;
    		
    		File source = new File(original);
    		
    		String extension = original.substring(original.lastIndexOf("."));
    		
    		String newName = UUID.randomUUID() + extension;
    		
    		String folder = "." + File.separator + "images";
    		
    		File directory = new File(folder);
    		
    		if(!directory.exists()) {
    			directory.mkdir();
    		}
    		
    		Path destination = Paths.get(folder, newName);
    		
    		Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
    		
    		return destination.toString();
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    }

    private boolean validateForm(){

        view.resetErrors();

        boolean valid=true;

        if(!validateName()) valid=false;
        if(!validateEmail()) valid=false;
        if(!validateCountry()) valid=false;
        if(!validateGender()) valid=false;
        if(!validateTerms()) valid=false;
        if(!validateDescription()) valid=false;
        if(!validateProductos()) valid=false;
        if(!validateImage()) valid = false;

        return valid;
    }

    private boolean validateName(){

        if(view.getUserName().trim().isEmpty()){
            view.setErrorName("El nombre es obligatorio");
            return false;
        }

        if(view.getUserName().trim().length()<=3){
            view.setErrorName("Mínimo 4 caracteres");
            return false;
        }

        view.setErrorName("");
        return true;
    }

    private boolean validateEmail(){

        if(view.getEmail().trim().isEmpty()){
            view.setErrorEmail("El email es obligatorio");
            return false;
        }

        if(!view.getEmail().contains("@")){
            view.setErrorEmail("Email inválido");
            return false;
        }

        view.setErrorEmail("");
        return true;
    }

    private boolean validateCountry(){

        if(view.getCountryIndex()==0){
            view.setErrorCombo("Seleccione un país");
            return false;
        }

        view.setErrorCombo("");
        return true;
    }

    private boolean validateGender(){

        if(view.getGenderGroup().getSelection()==null){
            view.setErrorGender("Seleccione un género");
            return false;
        }

        view.setErrorGender("");
        return true;
    }

    private boolean validateTerms(){

        if(!view.isTermsAccepted()){
            view.setErrorTerms("Debe aceptar los términos");
            return false;
        }

        view.setErrorTerms("");
        return true;
    }

    private boolean validateDescription(){

        if(view.getDescription().trim().length()<10){
            view.setErrorDescription("Descripción mínima 10 caracteres");
            return false;
        }

        view.setErrorDescription("");
        return true;
    }

    private boolean validateProductos(){

        if(view.getProductos().isEmpty()){
            view.setErrorList("Seleccione al menos un lenguaje");
            return false;
        }

        view.setErrorList("");
        return true;
    }
    
    private boolean validateImage(){

	    if(view.getSelectedImagePath() == null){
	        view.setErrorImage("Seleccione una imagen");
	        return false;
	    }

	    view.setErrorImage("");
	    return true;
	}

}