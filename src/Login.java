import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.sql.*;
import java.awt.Color;

public class Login {

	private JFrame frame;
	
	private JLabel usernameLabel;
	private JTextField usernameText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton signUpButton;
	private JButton forgotPasswordButton;
	private JRadioButton studentCheck;
	private JRadioButton teacherCheck;
	private ButtonGroup occupationGroup;
	private JLabel background;
	private ImageIcon loginImage;
	private JLabel welcomeLabel;
	
	private Connection conn;
	private ResultSet result;
	private PreparedStatement statement;
	

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public Login() {
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
		usernameLabel.setBounds(40, 173, 142, 68);
		frame.getContentPane().add(usernameLabel);
		usernameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));

		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameText.setBounds(217, 197, 235, 33);
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.CYAN);
		passwordLabel.setBounds(40, 252, 132, 50);
		frame.getContentPane().add(passwordLabel);
		passwordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));

		passwordText = new JPasswordField();
		passwordText.setColumns(10);
		passwordText.setBounds(217, 263, 235, 33);
		frame.getContentPane().add(passwordText);
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 20));

		loginButton = new JButton("Login");
		loginButton.setForeground(Color.BLUE);
		loginButton.setBounds(51, 339, 158, 55);
		frame.getContentPane().add(loginButton);
		loginButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		
		loginImage = new ImageIcon(new ImageIcon("src/resources/login.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		loginButton.setIcon(loginImage);

		signUpButton = new JButton("Sign Up");
		signUpButton.setForeground(Color.BLUE);
		signUpButton.setBounds(269, 339, 142, 55);
		frame.getContentPane().add(signUpButton);
		signUpButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));

		forgotPasswordButton = new JButton("Forgot Password?");
		forgotPasswordButton.setForeground(Color.BLUE);
		forgotPasswordButton.setBounds(102, 435, 270, 68);
		frame.getContentPane().add(forgotPasswordButton);
		forgotPasswordButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		
		studentCheck = new JRadioButton("Student");
		studentCheck.setBackground(Color.GRAY);
		studentCheck.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		studentCheck.setForeground(Color.CYAN);
		studentCheck.setBounds(51, 104, 132, 40);
		frame.getContentPane().add(studentCheck);
		
		teacherCheck = new JRadioButton("Teacher");
		teacherCheck.setBackground(Color.GRAY);
		teacherCheck.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		teacherCheck.setForeground(Color.CYAN);
		teacherCheck.setBounds(240, 104, 132, 40);
		frame.getContentPane().add(teacherCheck);
		
		occupationGroup = new ButtonGroup();
		occupationGroup.add(studentCheck);
		occupationGroup.add(teacherCheck);
		
		welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setForeground(Color.CYAN);
		welcomeLabel.setFont(new Font("Segoe Print", Font.PLAIN, 35));
		welcomeLabel.setBounds(122, 25, 193, 55);
		frame.getContentPane().add(welcomeLabel);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		
		//Check the user's entered info and log the user in if everything is correct
		//Otherwise, inform the user about the incorrect info
		loginButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				//Login the user based on the user's occupation
				if (studentCheck.isSelected()) {
					String occupation = "Student";
					login(occupation);
				} else if (teacherCheck.isSelected()) {
					String occupation = "Teacher";
					login(occupation);
				} else {	//If occupation is not checked, inform the user
					JOptionPane.showMessageDialog(null, "Please Enter Your Occupation");
				}
				
			}
			
		});
		
		//Switch over to the signUp window once the signUpButton is clicked
		signUpButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				frame.setVisible(false);
				SignUp signUp = new SignUp();
				signUp.getFrame().setVisible(true);
				
			}
			
		});
		
		//Switch over to the forgotPassword window once the forgotPasswordButton is clicked
		forgotPasswordButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				ForgotPassword forgotPassword = new ForgotPassword();
				forgotPassword.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	//Logs the user in by comparing the entered info with the one in the database to authenticate the user
	public void login(String occupation) {
		
		try {
			
			//Fetch the data from the database
			String sql = "SELECT * FROM " + occupation + " WHERE Username=? AND Password=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, usernameText.getText());
			statement.setString(2, new String(passwordText.getPassword()));
			result = statement.executeQuery();
			
			//If user with entered info exists...
			if (result.next()) {
				
				//Store the current user's ID and occupation
				String userID = result.getString(1);
				Constants.setUserID(userID);
				Constants.setOccupation(occupation);
				
				//Close the database helpers
				result.close();
				statement.close();
				
				//Continue to the main menu
				frame.setVisible(false);
				Menu menu = new Menu();
				menu.getFrame().setVisible(true);
				
			} else {	//If user with entered info does not exist...
				
				//Inform the user about the incorrect data
				JOptionPane.showMessageDialog(null, "Incorrect data");
				
				//Clear the fields
				usernameText.setText("");
				passwordText.setText("");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//Returns the login frame
	public JFrame getFrame() {
		return frame;
	}
}
