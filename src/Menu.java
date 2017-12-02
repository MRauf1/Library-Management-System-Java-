import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu {

	private JFrame frame;
	
	private JButton aboutButton;
	private JButton booksButton;
	private JButton accountButton;
	private JButton logOutButton;

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public Menu() {
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aboutButton.setBounds(507, 486, 89, 23);
		frame.add(aboutButton);
		
		booksButton = new JButton("Books");
		booksButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		booksButton.setBounds(72, 66, 89, 23);
		frame.add(booksButton);
		
		accountButton = new JButton("Account");
		accountButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		accountButton.setBounds(352, 488, 113, 19);
		frame.add(accountButton);
		
		logOutButton = new JButton("Log Out");
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOutButton.setBounds(629, 478, 103, 39);
		frame.add(logOutButton);
		
		
		
		aboutButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				
				
			}
			
		});
		
		booksButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				
				
			}
			
		});
		
		accountButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				
			}
			
		});
		
		logOutButton.addActionListener(new java.awt.event.ActionListener() {

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

}
