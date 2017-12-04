import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class Account {

	private JFrame frame;
	
	private Connection conn;
	private ResultSet result;
	private PreparedStatement statement;
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel usernameLabel;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JLabel moneyOwedLabel;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField usernameText;
	private JTextField answerText;
	private JComboBox<String> questionDropdown;
	private JButton changePasswordButton;
	private JButton applyButton;
	private JButton goBackButton;
	private JLabel background;
	private ImageIcon goBackImage;
	private ImageIcon applyImage;
	private ImageIcon passwordImage;
	
	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account window = new Account();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public Account() {
		conn = JavaConnect.getConnection();
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(500, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setForeground(Color.CYAN);
		firstNameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		firstNameLabel.setBounds(37, 32, 165, 32);
		frame.getContentPane().add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(Color.CYAN);
		lastNameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		lastNameLabel.setBounds(37, 85, 165, 32);
		frame.getContentPane().add(lastNameLabel);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.CYAN);
		usernameLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		usernameLabel.setBounds(37, 139, 136, 32);
		frame.getContentPane().add(usernameLabel);
		
		questionLabel = new JLabel("Question");
		questionLabel.setForeground(Color.CYAN);
		questionLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		questionLabel.setBounds(37, 193, 125, 32);
		frame.getContentPane().add(questionLabel);
		
		answerLabel = new JLabel("Answer");
		answerLabel.setForeground(Color.CYAN);
		answerLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		answerLabel.setBounds(37, 247, 125, 32);
		frame.getContentPane().add(answerLabel);
		
		changePasswordButton = new JButton("Change Password");
		changePasswordButton.setForeground(Color.BLUE);
		changePasswordButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		changePasswordButton.setBounds(10, 452, 274, 72);
		frame.getContentPane().add(changePasswordButton);
		
		passwordImage = new ImageIcon(new ImageIcon("src/resources/password.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		changePasswordButton.setIcon(passwordImage);
		
		applyButton = new JButton("Apply Changes");
		applyButton.setForeground(Color.BLUE);
		applyButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		applyButton.setBounds(294, 452, 285, 72);
		frame.getContentPane().add(applyButton);
		
		applyImage = new ImageIcon(new ImageIcon("src/resources/confirm.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		applyButton.setIcon(applyImage);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setBounds(589, 452, 185, 72);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		firstNameText = new JTextField();
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		firstNameText.setBounds(201, 35, 449, 32);
		frame.getContentPane().add(firstNameText);
		firstNameText.setColumns(10);
		
		lastNameText = new JTextField();
		lastNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastNameText.setBounds(201, 88, 449, 32);
		frame.getContentPane().add(lastNameText);
		lastNameText.setColumns(10);
		
		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameText.setBounds(201, 142, 449, 32);
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10);
		
		answerText = new JTextField();
		answerText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		answerText.setBounds(201, 250, 449, 32);
		frame.getContentPane().add(answerText);
		answerText.setColumns(10);
		
		questionDropdown = new JComboBox<String>();
		questionDropdown.setFont(new Font("Tahoma", Font.PLAIN, 20));
		questionDropdown.setBounds(201, 196, 449, 31);
		String[] questions = {"What was your childhood nickname?",
				"In what city were you born?", 
				"What is your favorite movie?", 
				"What is the name of your first teacher?", 
		"What was your favorite food as a child?"};
		questionDropdown.setModel(new DefaultComboBoxModel<String>(questions));
		frame.getContentPane().add(questionDropdown);
		
		moneyOwedLabel = new JLabel("Money Owed");
		moneyOwedLabel.setForeground(Color.CYAN);
		moneyOwedLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		moneyOwedLabel.setBounds(37, 323, 613, 37);
		frame.getContentPane().add(moneyOwedLabel);
		
		//Writes the data into the text boxes
		prepareData();
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		//Switch over to the changePassword window once the changePasswordButton is clicked
		changePasswordButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				ChangePassword changePassword = new ChangePassword();
				changePassword.getFrame().setVisible(true);
				
			}
			
		});
		
		//Applies all the changes entered once the applyButton is clicked
		applyButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				checkData();
				
			}
			
		});
		
		//Switch over to the menu window once the goBackButton is clicked
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Menu menu = new Menu();
				menu.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	//Fetches the current info about the user from the database and displays it in the text boxes
	public void prepareData() {
		
		//Get info about the current user
		String occupation = Constants.getOccupation();
		String id = Constants.getUserID();
		
		try {
			
			//Fetch the current user's data from the database
			String sql = "Select * from " + occupation + " where Id=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			result = statement.executeQuery();
			
			//Write the current's user's info inside the text boxes
			firstNameText.setText(result.getString(2));
			lastNameText.setText(result.getString(3));
			usernameText.setText(result.getString(4));
			questionDropdown.setSelectedItem(result.getString(6));
			answerText.setText(result.getString(7));
			moneyOwedLabel.setText("Money Owed: \t$" + result.getString(8));
			
			//Close the database helpers
			result.close();
			statement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//Changes the info about the current user and stores it in the database
	public void changeData() {
		
		//Get info about the current user
		String userID = Constants.getUserID();
		String occupation = Constants.getOccupation();
		
		try {
			
			//Change and store the new data in the database
			String sql = "Update " + occupation + " set first=?, last=?, username=?, question=?, answer=? where Id=" + userID;
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, firstNameText.getText());
			statement.setString(2, lastNameText.getText());
			statement.setString(3, usernameText.getText());
			statement.setString(4, (String) questionDropdown.getSelectedItem());
			statement.setString(5, answerText.getText());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Changes Applied");
			
			//Close the database helper
			statement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		//Switch over to the main menu frame
		frame.setVisible(false);
		Menu menu = new Menu();
		menu.getFrame().setVisible(true);
		
	}
	
	//Checks whether the entered data is valid
	public void checkData() {
		
		boolean isValidFirstName = ! (firstNameText.getText().isEmpty());
		boolean isValidLastName = ! (lastNameText.getText().isEmpty());
		boolean isValidUsername = ! (usernameText.getText().isEmpty());
		boolean isValidAnswer = ! (answerText.getText().isEmpty());
		
		//If everything is valid, apply the changes
		if(isValidFirstName && isValidLastName && isValidUsername && isValidAnswer) {
			changeData();
		} else {	//Otherwise, inform the user
			JOptionPane.showMessageDialog(null, "Please Enter The Fields Correctly");
		}
		
	}
	
	//Returns the account frame
	public JFrame getFrame() {
		return frame;
	}
}
