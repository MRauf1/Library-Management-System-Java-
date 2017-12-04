import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.sql.*;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class ForgotPassword {

	private JFrame frame;
	
	private JLabel usernameLabel;
	private JLabel questionLabel;
	private JLabel retrievedQuestionLabel;
	private JLabel answerLabel;
	private JLabel passwordLabel;
	private JTextField usernameText;
	private JTextField answerText;
	private JTextField passwordText;
	private JButton userButton;
	private JButton passwordButton;
	private JButton goBackButton;
	private JRadioButton studentCheck;
	private JRadioButton teacherCheck;
	private ButtonGroup occupationGroup;
	private JLabel background;
	private ImageIcon searchImage;
	private ImageIcon goBackImage;
	
	private Connection conn;
	private ResultSet result;
	private PreparedStatement statement;
	
	private String username;
	private String occupation;
	
	

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword window = new ForgotPassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public ForgotPassword() {
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
		usernameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		usernameLabel.setBounds(24, 163, 152, 30);
		frame.getContentPane().add(usernameLabel);
		
		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameText.setBounds(186, 165, 216, 31);
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10);
		
		questionLabel = new JLabel("Question");
		questionLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		questionLabel.setForeground(Color.CYAN);
		questionLabel.setBounds(24, 226, 120, 30);
		frame.getContentPane().add(questionLabel);
		
		answerLabel = new JLabel("Answer");
		answerLabel.setForeground(Color.CYAN);
		answerLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		answerLabel.setBounds(24, 311, 106, 30);
		frame.getContentPane().add(answerLabel);
		
		answerText = new JTextField();
		answerText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		answerText.setBounds(177, 312, 285, 33);
		frame.getContentPane().add(answerText);
		answerText.setColumns(10);
		
		userButton = new JButton("Find");
		userButton.setForeground(Color.BLUE);
		userButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		userButton.setBounds(447, 148, 136, 53);
		frame.getContentPane().add(userButton);
		
		searchImage = new ImageIcon(new ImageIcon("src/resources/search.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		userButton.setIcon(searchImage);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.CYAN);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passwordLabel.setBounds(24, 377, 160, 46);
		frame.getContentPane().add(passwordLabel);
		
		passwordText = new JTextField();
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordText.setBounds(177, 389, 285, 27);
		frame.getContentPane().add(passwordText);
		passwordText.setColumns(10);
		
		passwordButton = new JButton("Retrieve");
		passwordButton.setForeground(Color.BLUE);
		passwordButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		passwordButton.setBounds(496, 374, 144, 53);
		frame.getContentPane().add(passwordButton);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setBounds(470, 471, 173, 67);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		studentCheck = new JRadioButton("Student");
		studentCheck.setBackground(Color.GRAY);
		studentCheck.setForeground(Color.CYAN);
		studentCheck.setFont(new Font("Tahoma", Font.PLAIN, 25));
		studentCheck.setBounds(32, 81, 144, 30);
		frame.getContentPane().add(studentCheck);
		
		teacherCheck = new JRadioButton("Teacher");
		teacherCheck.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		teacherCheck.setForeground(Color.CYAN);
		teacherCheck.setBackground(Color.GRAY);
		teacherCheck.setBounds(235, 81, 167, 30);
		frame.getContentPane().add(teacherCheck);
		
		occupationGroup = new ButtonGroup();
		occupationGroup.add(teacherCheck);
		occupationGroup.add(studentCheck);
		
		retrievedQuestionLabel = new JLabel("");
		retrievedQuestionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		retrievedQuestionLabel.setForeground(Color.CYAN);
		retrievedQuestionLabel.setBounds(177, 226, 325, 30);
		frame.getContentPane().add(retrievedQuestionLabel);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		
		//Finds the user depending on the entered occupation once the userButton is clicked
		userButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				if (studentCheck.isSelected()) {
					occupation = "Student";
					findUser(occupation);
				} else if (teacherCheck.isSelected()) {
					occupation = "Teacher";
					findUser(occupation);
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Your Occupation");
				}
				
			}
			
		});
		
		//Retrieves the password once the passwordButton is clicked
		passwordButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				retrievePassword(occupation);
				
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
	
	//Finds the user based on the entered username
	public void findUser(String occupation) {
		
		//Get the user's entered username
		username = usernameText.getText();
		
		try {
			
			//Fetch the data
			String sql = "Select * from " + occupation + " where Username=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			result = statement.executeQuery();
			
			//If the user is found, enter the user's secret question
			if (result.next()) {
				
				//Write the question
				retrievedQuestionLabel.setText(result.getString(6));
				
				//Close the database helpers
				result.close();
				statement.close();
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Incorrect Username");
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
		
		
	}
	
	//If the answer to the secret question is correct, retrieves and shows the user's password
	public void retrievePassword(String occupation) {
		
		try {
			
			//Fetch the user's correct answer
			String sql = "Select * from " + occupation + " where Username=? and Answer=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, answerText.getText());
			result = statement.executeQuery();
			
			//If the entered answer is correct, show the user's password
			if (result.next()) {
				
				passwordText.setText(result.getString(5));
				
				//Close the database helpers
				statement.close();
				result.close();
				
			} else {	//If the answer is incorrect, inform the user
				
				JOptionPane.showMessageDialog(null, "Incorrect Answer");
				
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//Returns the forgotPassword frame
	public JFrame getFrame() {
		return frame;
	}
}
