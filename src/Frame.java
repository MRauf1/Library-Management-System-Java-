import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.CardLayout;

//import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

//WARNING: SPAGHETTI CODE

public class Frame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Do I even use these?
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	
	private JPanel menu;
	private JPanel account;
	private JPanel changePassword;
	
	private JFrame frame;
	
	//private Connection conn;
	//private PreparedStatement statement;
	private JPasswordField oldPasswordTextBoxChangePassword;
	private JPasswordField newPasswordTextBoxChangePassword;
	private JPasswordField confirmPasswordTextBoxChangePassword;
	private JTextField firstNameTextBoxAccount;
	private JTextField lastNameTextBoxAccount;
	private JTextField usernameTextBoxAccount;
	private JTextField answerTextBoxAccount;
	/*
	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	//Create the application
	public Frame() {
		
		//conn = JavaConnect.getConnection();
		initialize();
	
	}

	//Initialize the contents of the frame.
	private void initialize() {
		
		//Create the frame
		frame = new JFrame();
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		/*
		JPanel login = (new Login2()).getLoginPanel();
		frame.getContentPane().add(login);
		
		JPanel signUp = (new SignUp2().getSignUpPanel());
		frame.getContentPane().add(signUp);
		*/
		
		
		menu = new JPanel();
		frame.getContentPane().add(menu, "name_15944595346898");
		menu.setLayout(null);
		
		JButton aboutButtonMenu = new JButton("About");
		aboutButtonMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aboutButtonMenu.setBounds(507, 486, 89, 23);
		menu.add(aboutButtonMenu);
		
		JButton booksButtonMenu = new JButton("Books");
		booksButtonMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		booksButtonMenu.setBounds(72, 66, 89, 23);
		menu.add(booksButtonMenu);
		
		JButton accountButtonMenu = new JButton("Account");
		accountButtonMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		accountButtonMenu.setBounds(352, 488, 113, 19);
		menu.add(accountButtonMenu);
		
		JButton logOutButtonMenu = new JButton("Log Out");
		logOutButtonMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOutButtonMenu.setBounds(629, 478, 103, 39);
		menu.add(logOutButtonMenu);
		
		account = new JPanel();
		frame.getContentPane().add(account, "name_182182968068529");
		account.setLayout(null);
		
		JButton changePasswordButtonAccount = new JButton("Change Password");
		changePasswordButtonAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		changePasswordButtonAccount.setBounds(58, 498, 213, 52);
		account.add(changePasswordButtonAccount);
		
		JButton applyButtonAccount = new JButton("Apply");
		applyButtonAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		applyButtonAccount.setBounds(314, 506, 89, 23);
		account.add(applyButtonAccount);
		
		JButton goBackButtonAccount = new JButton("Go Back");
		goBackButtonAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButtonAccount.setBounds(488, 504, 116, 40);
		account.add(goBackButtonAccount);
		
		JLabel firstNameLabelAccount = new JLabel("First Name");
		firstNameLabelAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		firstNameLabelAccount.setBounds(10, 32, 151, 34);
		account.add(firstNameLabelAccount);
		
		JLabel lastNameLabelAccount = new JLabel("Last Name");
		lastNameLabelAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastNameLabelAccount.setBounds(10, 83, 137, 23);
		account.add(lastNameLabelAccount);
		
		JLabel usernameLabelAccount = new JLabel("Username");
		usernameLabelAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabelAccount.setBounds(10, 129, 121, 25);
		account.add(usernameLabelAccount);
		
		JLabel answerLabelAccount = new JLabel("Answer");
		answerLabelAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		answerLabelAccount.setBounds(10, 230, 95, 34);
		account.add(answerLabelAccount);
		
		JLabel questionLabelAccount = new JLabel("Question");
		questionLabelAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		questionLabelAccount.setBounds(10, 186, 95, 33);
		account.add(questionLabelAccount);
		
		firstNameTextBoxAccount = new JTextField();
		firstNameTextBoxAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		firstNameTextBoxAccount.setBounds(194, 43, 209, 20);
		account.add(firstNameTextBoxAccount);
		firstNameTextBoxAccount.setColumns(10);
		
		lastNameTextBoxAccount = new JTextField();
		lastNameTextBoxAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastNameTextBoxAccount.setBounds(185, 88, 220, 20);
		account.add(lastNameTextBoxAccount);
		lastNameTextBoxAccount.setColumns(10);
		
		usernameTextBoxAccount = new JTextField();
		usernameTextBoxAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameTextBoxAccount.setBounds(185, 135, 220, 20);
		account.add(usernameTextBoxAccount);
		usernameTextBoxAccount.setColumns(10);
		
		answerTextBoxAccount = new JTextField();
		answerTextBoxAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		answerTextBoxAccount.setBounds(185, 241, 203, 20);
		account.add(answerTextBoxAccount);
		answerTextBoxAccount.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"What was your childhood nickname?", 
																"In what city were you born?", 
																"What is your favorite movie?", 
																"What is the name of your first teacher?", 
																"What was your favorite food as a child?"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(194, 196, 373, 34);
		account.add(comboBox);
		
		changePassword = new JPanel();
		frame.getContentPane().add(changePassword, "name_182722424932921");
		changePassword.setLayout(null);
		
		JLabel oldPasswordLabelChangePassword = new JLabel("Old Password");
		oldPasswordLabelChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldPasswordLabelChangePassword.setBounds(60, 132, 150, 32);
		changePassword.add(oldPasswordLabelChangePassword);
		
		JLabel newPasswordLabelChangePassword = new JLabel("New Password");
		newPasswordLabelChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPasswordLabelChangePassword.setBounds(60, 212, 169, 45);
		changePassword.add(newPasswordLabelChangePassword);
		
		JLabel confirmPasswordLabelChangePassword = new JLabel("Confirm Password");
		confirmPasswordLabelChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmPasswordLabelChangePassword.setBounds(33, 297, 191, 57);
		changePassword.add(confirmPasswordLabelChangePassword);
		
		oldPasswordTextBoxChangePassword = new JPasswordField();
		oldPasswordTextBoxChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldPasswordTextBoxChangePassword.setBounds(319, 142, 198, 20);
		changePassword.add(oldPasswordTextBoxChangePassword);
		
		newPasswordTextBoxChangePassword = new JPasswordField();
		newPasswordTextBoxChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPasswordTextBoxChangePassword.setBounds(304, 228, 198, 20);
		changePassword.add(newPasswordTextBoxChangePassword);
		
		confirmPasswordTextBoxChangePassword = new JPasswordField();
		confirmPasswordTextBoxChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmPasswordTextBoxChangePassword.setBounds(311, 319, 191, 20);
		changePassword.add(confirmPasswordTextBoxChangePassword);
		
		JButton applyButtonChangePassword = new JButton("Apply");
		applyButtonChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		applyButtonChangePassword.setBounds(320, 474, 93, 32);
		changePassword.add(applyButtonChangePassword);
		
		JButton cancelButtonChangePassword = new JButton("Cancel");
		cancelButtonChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButtonChangePassword.setBounds(487, 477, 121, 27);
		changePassword.add(cancelButtonChangePassword);
		/*
		//Once the "signup" button is clicked, check if the entered data is valid.
		//If it's valid, create a new user with that data.
		signUpButtonSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				try {
					
					signUp(studentCheckSignUp, teacherCheckSignUp);
					
				} catch (Exception e) {
					e.printStackTrace();	
				}
			}
		});
		
		//If the "goBack" button is clicked on the "signUp" page, open the "login" page.
		goBackButtonSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signUp.setVisible(false);
				login.setVisible(true);
			}
		});
		
		accountButtonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				account.setVisible(true);
				menu.setVisible(false);
				
			}
		});
		
	}

	
	
	public void createUser(String occupation) {
		
		try {
			
			String sql = "Insert into " + occupation + " (first, last, username, password, question, answer) values (?, ?, ?, ?, ?, ?);";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, firstNameTextBoxSignUp.getText());
			statement.setString(2, lastNameTextBoxSignUp.getText());
			statement.setString(3, usernameTextBoxSignUp.getText());
			statement.setString(4, new String(passwordTextBoxSignUp.getPassword()));
			statement.setString(5, (String) questionDropdownSignUp.getSelectedItem());
			statement.setString(6, answerTextBoxSignUp.getText());
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "New Account Created!");
			statement.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}
	
	public void signUp(JRadioButton student, JRadioButton teacher) {
		
		if (student.isSelected()) {
			
			createUser("Student");
			
		} else if (teacher.isSelected()) {
			
			createUser("Teacher");
			
		} 
		
		signUp.setVisible(false);
		menu.setVisible(true);
		
	}*/
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}