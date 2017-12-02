import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.sql.*;

public class SignUp {
	
	private Connection conn;
	private PreparedStatement statement;

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
		frame.setBounds(100, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(74, 170, 141, 48);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(54, 229, 133, 33);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(passwordLabel);

		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(42, 280, 176, 40);
		confirmPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(confirmPasswordLabel);

		usernameText = new JTextField();
		usernameText.setBounds(350, 171, 208, 48);
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10); //Necessary?

		passwordText = new JPasswordField();
		passwordText.setBounds(329, 229, 253, 33);
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(passwordText);

		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setBounds(301, 280, 281, 40);
		confirmPasswordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(confirmPasswordText);

		signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(305, 486, 168, 48);
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(signUpButton);

		goBackButton = new JButton("Go Back");
		goBackButton.setBounds(525, 486, 141, 48);
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(goBackButton);

		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(54, 51, 104, 64);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(firstNameLabel);

		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBounds(54, 114, 148, 40);
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lastNameLabel);

		firstNameText = new JTextField();
		firstNameText.setBounds(350, 64, 161, 40);
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(firstNameText);
		firstNameText.setColumns(10); //Necessary?

		lastNameText = new JTextField();
		lastNameText.setBounds(350, 112, 208, 48);
		lastNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lastNameText);
		lastNameText.setColumns(10); //Necessary?

		questionLabel = new JLabel("Secret Question");
		questionLabel.setBounds(42, 319, 173, 58);
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(questionLabel);

		answerLabel = new JLabel("Answer");
		answerLabel.setBounds(46, 370, 141, 58);
		answerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(answerLabel);

		questionDropdown = new JComboBox<String>();
		questionDropdown.setBounds(311, 328, 281, 48);
		String[] questions = {"What was your childhood nickname?",
				"In what city were you born?", 
				"What is your favorite movie?", 
				"What is the name of your first teacher?", 
		"What was your favorite food as a child?"};
		questionDropdown.setModel(new DefaultComboBoxModel<String>(questions));
		frame.getContentPane().add(questionDropdown);

		answerText = new JTextField();
		answerText.setBounds(301, 383, 318, 33);
		answerText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(answerText);
		answerText.setColumns(10); //Necessary?

		studentCheck = new JRadioButton("Student");
		studentCheck.setBounds(42, 423, 122, 64);
		studentCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(studentCheck);

		teacherCheck = new JRadioButton("Teacher");
		teacherCheck.setBounds(242, 423, 304, 58);
		teacherCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(teacherCheck);

		occupationGroup = new ButtonGroup();
		occupationGroup.add(teacherCheck);
		occupationGroup.add(studentCheck);
		
		
		
		signUpButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					signUp(studentCheck, teacherCheck);
				} catch (Exception e) {
					e.printStackTrace();	
				}
				
			}
			
		});
		
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				Login login = new Login();
				login.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void createUser(String occupation) {
		
		try {
			
			String sql = "Insert into " + occupation + " (first, last, username, password, question, answer) values (?, ?, ?, ?, ?, ?);";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, firstNameText.getText());
			statement.setString(2, lastNameText.getText());
			statement.setString(3, usernameText.getText());
			statement.setString(4, new String(passwordText.getPassword()));
			statement.setString(5, (String) questionDropdown.getSelectedItem());
			statement.setString(6, answerText.getText());
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
		
		frame.setVisible(false);
		Menu menu = new Menu();
		menu.getFrame().setVisible(true);
		
	}

}
