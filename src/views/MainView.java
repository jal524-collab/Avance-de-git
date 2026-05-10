package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utils.ThemeManager;

public class MainView extends JFrame {

	public static final String HOME = "HOME";
	public static final String USERS = "USERS";
	
	public JMenuItem mItemExit;
	public JButton btnUsers;
	public JButton btnHome;
	public UserView usersPanel;
	
	private CardLayout cardLayout;
	private JPanel container;
	
	public MainView() {

		setSize(1000, 500);
		setTitle("Mi aplicación");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		try {
	        
	        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/img/icono.png"));
	        setIconImage(icono.getImage());
	    } catch (Exception e) {
	        System.out.println("No se pudo cargar el icono: " + e.getMessage());
	    }

		setMenu();
		
		createNavbar();
		createViews();

		setVisible(true);
	}
	
	public void createNavbar() {
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnHome = new JButton("Inicio");
		btnUsers = new JButton("Empleados");
		
		navbar.add(btnHome);
		navbar.add(btnUsers);
		
		add(navbar, BorderLayout.NORTH);
	}
	
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		FondoPanel homePanel = new FondoPanel("/assets/img/background.jpg");
	    homePanel.setLayout(new FlowLayout()); 
		
		homePanel.add(new JLabel("Bienvenido al Sistema de Amazon Gerente"));
		
		usersPanel = new UserView();
		
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		
		add(container, BorderLayout.CENTER);
		
	}
	
	public void showView(String view) {
		cardLayout.show(container, view);
	}

	public void setMenu() {

	    JMenuBar mb = new JMenuBar();
	    setJMenuBar(mb);

	    JMenu menuFile = new JMenu("File");
	    menuFile.setMnemonic(KeyEvent.VK_F);
	    mb.add(menuFile);

	    JMenuItem mItemOpen = new JMenuItem("Open");
	    mItemOpen.setMnemonic(KeyEvent.VK_O);
	    menuFile.add(mItemOpen);

	    JMenuItem mItemSave = new JMenuItem("Save");
	    mItemSave.setMnemonic(KeyEvent.VK_S);
	    menuFile.add(mItemSave);

	    menuFile.addSeparator();

	    mItemExit = new JMenuItem("Exit");
	    mItemExit.setMnemonic(KeyEvent.VK_E);
	    menuFile.add(mItemExit);
	    
	    JMenuItem theme = new JMenuItem("Cambiar modo");
	    theme.addActionListener(e -> {
	    	ThemeManager.toggle();
	    });
	    mb.add(theme);

	    JMenu menuOtherOption = new JMenu("Other Option");
	    menuOtherOption.setMnemonic(KeyEvent.VK_O);
	    mb.add(menuOtherOption);

	    JMenu menuOption1 = new JMenu("Option 1");
	    menuOtherOption.add(menuOption1);

	    JMenuItem mItemOption3 = new JMenuItem("Option 3");
	    menuOption1.add(mItemOption3);

	    JMenuItem mItemOption2 = new JMenuItem("Option 2");
	    menuOtherOption.add(mItemOption2);

	}
	
	public int confirmExit() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}
	
	public void setWindowSize(int width, int height) {
		setSize(width, height);
	}
	
	public void setWindowLocation(int x, int y) {
		setLocation(x, y);
	}

	
	private void ejemplo() {
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Se abrió la ventana");

			}

			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Se minimizó");

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Se volvió a abrir");

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Perdió el focus");

			}

			@Override
			public void windowClosing(WindowEvent e) {

			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Se cerró");

			}

			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Obtuvo el focus");

			}
		});
		
		/*panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {
					System.out.println("Clicks: " + e.getClickCount());
					System.out.println("X: " + e.getX());
					System.out.println("Y: " + e.getY());
					// System.out.println(e.getPoint().x);
					// System.out.println(e.getPoint().y);
					System.out.println("Clic izquierdo");
				}
			}
		});

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println("Arrastrando " + e.getX() + ", " + e.getY());
			}
		});*/

	}
}