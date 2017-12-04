import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class ChangePassword {

	private JFrame frame;
	
	private JLabel oldPasswordLabel;
	private JLabel newPasswordLabel;
	private JLabel confirmPasswordLabel;
	private JPasswordField oldPasswordText;
	private JPasswordField newPasswordText;
	private JPasswordField confirmPasswordText;
	private JButton applyButton;
	private JButton goBackButton;
	private JLabel background;
	private ImageIcon goBackImage;
	
	private Connection conn;
	private ResultSet result;
	private PreparedStatement statement;

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public ChangePassword() {
		conn = JavaConnect.getConnection();
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(500, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		oldPasswordLabel = new JLabel("Old Password");
		oldPasswordLabel.setForeground(Color.CYAN);
		oldPasswordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		oldPasswordLabel.setBounds(27, 143, 193, 37);
		frame.getContentPane().add(oldPasswordLabel);
		
		newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setForeground(Color.CYAN);
		newPasswordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		newPasswordLabel.setBounds(27, 231, 213, 31);
		frame.getContentPane().add(newPasswordLabel);
		
		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setForeground(Color.CYAN);
		confirmPasswordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		confirmPasswordLabel.setBounds(27, 303, 243, 55);
		frame.getContentPane().add(confirmPasswordLabel);
		
		oldPasswordText = new JPasswordField();
		oldPasswordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldPasswordText.setBounds(292, 143, 280, 35);
		frame.getContentPane().add(oldPasswordText);
		
		newPasswordText = new JPasswordField();
		newPasswordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPasswordText.setBounds(292, 231, 280, 33);
		frame.getContentPane().add(newPasswordText);
		
		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmPasswordText.setBounds(292, 313, 280, 39);
		frame.getContentPane().add(confirmPasswordText);
		
		applyButton = new JButton("Apply");
		applyButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		applyButton.setForeground(Color.BLUE);
		applyButton.setBounds(167, 417, 185, 73);
		frame.getContentPane().add(applyButton);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		goBackButton.setBounds(460, 417, 213, 73);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		//Checks the data and applies the changes regarding the password once the applyButton is clicked
		applyButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				checkData();
				
			}
			
		});
		
		//Switch over to the account window once the goBackButton is clicked
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Account account = new Account();
				account.getFrame().setVisible(true);
				
			}
			
		});
	
	}
	
	//If the fields are entered correctly, changes and stores the new password in the database
	public void applyChanges() {
		
		//Get info about the current user
		String userID = Constants.getUserID();
		String occupation = Constants.getOccupation();
		
		try {
			
			//Change the password in the database to the new one
			String sql = "Update " + occupation + " set password=? where Id=" + userID;
			statement = conn.prepareStatement(sql);
			statement.setString(1, new String(newPasswordText.getPassword()));
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Changes Applied");
			
			//Close the database helper
			statement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		//Switch over to the account frame
		frame.setVisible(false);
		Account account = new Account();
		account.getFrame().setVisible(true);
		
	}
	
	//Check the entered fields to see whether the entered info is valid
	public void checkData() {
		
		//Get info about the current user
		String userID = Constants.getUserID();
		String occupation = Constants.getOccupation();
		
		try {
			
			//Fetch the user's old password
			String sql = "SELECT * FROM " + occupation + " WHERE Id=? and Password=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, userID);
			statement.setString(2, new String(oldPasswordText.getPassword()));
			
			result = statement.executeQuery();
			
			//If the user entered the old password correctly...
			if(result.next()) {
				
				//Close the database helpers
				statement.close();
				result.close();
				
				//Check the validity of the password fields
				String newPassword = new String(newPasswordText.getPassword());
				String confirmPassword = new String(confirmPasswordText.getPassword());
				
				boolean isValidNewPassword = ! (newPassword.isEmpty()) && newPassword.length() >= 8;
				boolean isValidConfirmPassword = confirmPassword.equals(newPassword);
				
				//If the fields are filled properly, apply the changes
				if(isValidNewPassword && isValidConfirmPassword) {
					applyChanges();
				} else {	//If the fields are not filled properly, inform the user
					JOptionPane.showMessageDialog(null, "Please Enter The Fields Correctly");
				}
				
			} else {	//If the old password is incorrectly entered, inform the user
				
				JOptionPane.showMessageDialog(null, "Old Password Is Incorrect");
			
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//Returns the changePassword frame
	public JFrame getFrame() {
		return frame;
	}
}
