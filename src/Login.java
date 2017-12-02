import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	private JFrame frame;
	
	private JLabel usernameLabel;
	private JTextField usernameText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton signUpButton;
	private JButton forgotPasswordButton;

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
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 100, 100, 50);
		frame.getContentPane().add(usernameLabel);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		usernameText = new JTextField();
		usernameText.setBounds(266, 113, 235, 33);
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 200, 100, 50);
		frame.getContentPane().add(passwordLabel);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		passwordText = new JPasswordField();
		passwordText.setColumns(10);
		passwordText.setBounds(274, 206, 201, 39);
		frame.getContentPane().add(passwordText);
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 20));

		loginButton = new JButton("Login");
		loginButton.setBounds(186, 327, 81, 33);
		frame.getContentPane().add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

		signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(395, 327, 101, 33);
		frame.getContentPane().add(signUpButton);
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

		forgotPasswordButton = new JButton("Forgot Password?");
		forgotPasswordButton.setBounds(235, 405, 189, 68);
		frame.getContentPane().add(forgotPasswordButton);
		forgotPasswordButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		
		loginButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				
			}
			
		});
		
		signUpButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				SignUp signUp = new SignUp();
				signUp.getFrame().setVisible(true);
				
			}
			
		});
		
		forgotPasswordButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				
				
			}
			
		});
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
