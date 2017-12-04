import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.sql.*;
import java.awt.Color;

public class SignUp {

	private JFrame frame;

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JPasswordField confirmPasswordText;
	private JButton signUpButton;
	private JButton goBackButton;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JComboBox<String> questionDropdown;
	private JTextField answerText;
	private JRadioButton studentCheck;
	private JRadioButton teacherCheck;
	private ButtonGroup occupationGroup;
	private JLabel background;
	private ImageIcon signUpImage;
	private ImageIcon goBackImage;
	
	private Connection conn;
	private ResultSet result;
	private PreparedStatement statement;
	
	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public SignUp() {
		conn = JavaConnect.getConnection();
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(500, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.CYAN);
		usernameLabel.setBounds(42, 147, 141, 48);
		usernameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.CYAN);
		passwordLabel.setBounds(42, 206, 133, 33);
		passwordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(passwordLabel);

		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setForeground(Color.CYAN);
		confirmPasswordLabel.setBounds(39, 250, 202, 40);
		confirmPasswordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(confirmPasswordLabel);

		usernameText = new JTextField();
		usernameText.setBounds(252, 155, 215, 33);
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10); //Necessary?

		passwordText = new JPasswordField();
		passwordText.setBounds(252, 206, 215, 33);
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(passwordText);

		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setBounds(251, 254, 215, 33);
		confirmPasswordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(confirmPasswordText);

		signUpButton = new JButton("Sign Up");
		signUpButton.setForeground(Color.BLUE);
		signUpButton.setBounds(104, 473, 168, 48);
		signUpButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(signUpButton);
		
		signUpImage = new ImageIcon(new ImageIcon("src/resources/login.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		signUpButton.setIcon(signUpImage);;

		goBackButton = new JButton("Go Back");
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setBounds(308, 473, 197, 48);
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);

		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setForeground(Color.CYAN);
		firstNameLabel.setBounds(39, 37, 148, 48);
		firstNameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(firstNameLabel);

		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(Color.CYAN);
		lastNameLabel.setBounds(39, 96, 148, 40);
		lastNameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(lastNameLabel);

		firstNameText = new JTextField();
		firstNameText.setBounds(252, 45, 215, 33);
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(firstNameText);
		firstNameText.setColumns(10); //Necessary?

		lastNameText = new JTextField();
		lastNameText.setBounds(252, 100, 215, 33);
		lastNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lastNameText);
		lastNameText.setColumns(10); //Necessary?

		questionLabel = new JLabel("Secret Question");
		questionLabel.setForeground(Color.CYAN);
		questionLabel.setBounds(42, 301, 173, 40);
		questionLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(questionLabel);

		answerLabel = new JLabel("Answer");
		answerLabel.setForeground(Color.CYAN);
		answerLabel.setBounds(42, 352, 141, 40);
		answerLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(answerLabel);

		questionDropdown = new JComboBox<String>();
		questionDropdown.setBounds(252, 301, 281, 33);
		String[] questions = {"What was your childhood nickname?",
				"In what city were you born?", 
				"What is your favorite movie?", 
				"What is the name of your first teacher?", 
		"What was your favorite food as a child?"};
		questionDropdown.setModel(new DefaultComboBoxModel<String>(questions));
		frame.getContentPane().add(questionDropdown);

		answerText = new JTextField();
		answerText.setBounds(252, 356, 281, 33);
		answerText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(answerText);
		answerText.setColumns(10); //Necessary?

		studentCheck = new JRadioButton("Student");
		studentCheck.setBackground(Color.GRAY);
		studentCheck.setForeground(Color.CYAN);
		studentCheck.setBounds(42, 399, 122, 48);
		studentCheck.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(studentCheck);

		teacherCheck = new JRadioButton("Teacher");
		teacherCheck.setBackground(Color.GRAY);
		teacherCheck.setForeground(Color.CYAN);
		teacherCheck.setBounds(208, 399, 122, 48);
		teacherCheck.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		frame.getContentPane().add(teacherCheck);

		occupationGroup = new ButtonGroup();
		occupationGroup.add(teacherCheck);
		occupationGroup.add(studentCheck);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		//Sign up the user with the entered info once the signUpButton is clicked
		signUpButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				signUp(studentCheck, teacherCheck);
				
			}
			
		});
		
		//Switch over to the login window once the goBackButton is clicked
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Login login = new Login();
				login.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	//Returns the sign up frame
	public JFrame getFrame() {
		return frame;
	}
	
	//If all the fields have been correctly filled, create a new user and store the data in the database
	public void createUser(String occupation) {
		
		try {
			
			//Add the new user with the info to the database
			String sql = "Insert into " + occupation + " (first, last, username, password, question, answer, moneyOwed) values (?, ?, ?, ?, ?, ?, 0.0)";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, firstNameText.getText());
			statement.setString(2, lastNameText.getText());
			statement.setString(3, usernameText.getText());
			statement.setString(4, new String(passwordText.getPassword()));
			statement.setString(5, (String) questionDropdown.getSelectedItem());
			statement.setString(6, answerText.getText().toLowerCase());
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "New Account Created!");
			
			//Set the user's ID and occupation
			sql = "SELECT * FROM " + occupation + " WHERE Username=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, usernameText.getText());
			result = statement.executeQuery();
			
			Constants.setUserID(result.getString(1));
			Constants.setOccupation(occupation);
			
			//Close the database helper
			statement.close();
			result.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		
		}
		
		
		
	}
	
	//Check the entered fields and create a new user with the entered info and proceed to the main menu
	public void signUp(JRadioButton student, JRadioButton teacher) {
		
		if (student.isSelected()) {
			//If all the requirements were met, account was created, proceed to the main menu window
			if(checkUsername("Student")) {
				continueToMenu();
			}
		} else if (teacher.isSelected()) {
			//If all the requirements were met, account was created, proceed to the main menu window
			if(checkUsername("Teacher")) {
				continueToMenu();
			}
		} else {	//If the occupation is not checked, inform the user
			JOptionPane.showMessageDialog(null, "Please Enter Your Occupation");
		}
		
		
		
	}
	
	//Check whether the entered username is available
	//If everything is correct, return true
	public boolean checkUsername(String occupation) {
		
		String username = usernameText.getText();
		
		try {
			
			//See if there's already a username existing
			String sql = "SELECT * FROM " + occupation + " WHERE Username=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			result = statement.executeQuery();
			
			//If the username exists, inform the user
			if(result.next()) {
				JOptionPane.showMessageDialog(null, "Username Taken. Please Enter A New One.");
				//Close the database helpers
				statement.close();
				result.close();
			} else {	//If the username is available, check the fields
				//Close the database helpers
				statement.close();
				result.close();
				if(checkFields(occupation)) {
					return true;
				}
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
	
	//Check all the fields for correctness to avoid white spaces and short passwords
	//If everything is correct, return true
	public boolean checkFields(String occupation) {
		
		String password = new String(passwordText.getPassword());
		String confirmPassword = new String(confirmPasswordText.getPassword());
		
		boolean isValidFirstName = ! (firstNameText.getText().isEmpty());
		boolean isValidLastName = ! (lastNameText.getText().isEmpty());
		boolean isValidPassword = ! (password.isEmpty()) && password.length() >= 8;
		boolean isValidConfirmPassword = confirmPassword.equals(password);
		boolean isValidAnswer = ! (answerText.getText().isEmpty());
		
		//If all the fields have been filled correctly, create a new user
		if(isValidFirstName && isValidLastName && isValidPassword && isValidConfirmPassword && isValidAnswer) {
			createUser(occupation);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Please Fill The Fields Correctly.");
		}
		
		return false;
		
	}

	//Switch over to the main menu window once the function is called
	public void continueToMenu() {
		
		frame.setVisible(false);
		Menu menu = new Menu();
		menu.getFrame().setVisible(true);
		
	}
	
}
