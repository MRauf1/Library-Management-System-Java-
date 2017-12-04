import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class Menu {

	private JFrame frame;
	
	private JButton aboutButton;
	private JButton booksButton;
	private JButton accountButton;
	private JButton logOutButton;
	private JButton reportButton;
	private JLabel background;
	private ImageIcon booksImage;
	private ImageIcon reportImage;
	private ImageIcon accountImage;
	private ImageIcon logOutImage;
	private ImageIcon aboutImage;
	private JLabel aboutLabel;
	private JLabel accountLabel;
	private JLabel booksLabel;
	private JLabel reportLabel;
	private JLabel logOutLabel;
	

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
		frame.setBounds(500, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		aboutButton = new JButton();
		aboutButton.setForeground(Color.BLUE);
		aboutButton.setBounds(303, 375, 133, 110);
		frame.getContentPane().add(aboutButton);
		
		aboutImage = new ImageIcon(new ImageIcon("src/resources/about.png").getImage().getScaledInstance(133, 110, Image.SCALE_DEFAULT));
		aboutButton.setIcon(aboutImage);
		
		booksButton = new JButton();
		booksButton.setForeground(Color.BLUE);
		booksButton.setBounds(25, 148, 147, 124);
		frame.getContentPane().add(booksButton);
		
		booksImage = new ImageIcon(new ImageIcon("src/resources/book.png").getImage().getScaledInstance(147, 124, Image.SCALE_DEFAULT));
		booksButton.setIcon(booksImage);
		
		accountButton = new JButton();
		accountButton.setForeground(Color.BLUE);
		accountButton.setBounds(108, 375, 141, 110);
		frame.getContentPane().add(accountButton);
		
		accountImage = new ImageIcon(new ImageIcon("src/resources/user.png").getImage().getScaledInstance(141, 110, Image.SCALE_DEFAULT));
		accountButton.setIcon(accountImage);
		
		logOutButton = new JButton();
		logOutButton.setForeground(Color.BLUE);
		logOutButton.setBounds(499, 375, 133, 110);
		frame.getContentPane().add(logOutButton);
		
		logOutImage = new ImageIcon(new ImageIcon("src/resources/logout.png").getImage().getScaledInstance(133, 110, Image.SCALE_DEFAULT));
		logOutButton.setIcon(logOutImage);
		
		reportButton = new JButton();
		reportButton.setForeground(Color.BLUE);
		reportButton.setBounds(227, 148, 152, 124);
		frame.getContentPane().add(reportButton);
		
		reportImage = new ImageIcon(new ImageIcon("src/resources/report.png").getImage().getScaledInstance(152, 124, Image.SCALE_DEFAULT));
		reportButton.setIcon(reportImage);
		
		aboutLabel = new JLabel("About");
		aboutLabel.setForeground(Color.CYAN);
		aboutLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		aboutLabel.setBounds(323, 496, 102, 39);
		frame.getContentPane().add(aboutLabel);
		
		accountLabel = new JLabel("Account");
		accountLabel.setForeground(Color.CYAN);
		accountLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		accountLabel.setBounds(113, 496, 121, 39);
		frame.getContentPane().add(accountLabel);
		
		booksLabel = new JLabel("Books");
		booksLabel.setForeground(Color.CYAN);
		booksLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		booksLabel.setBounds(62, 283, 90, 50);
		frame.getContentPane().add(booksLabel);
		
		reportLabel = new JLabel("Report");
		reportLabel.setForeground(Color.CYAN);
		reportLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		reportLabel.setBounds(268, 283, 102, 50);
		frame.getContentPane().add(reportLabel);
		
		logOutLabel = new JLabel("Log Out");
		logOutLabel.setForeground(Color.CYAN);
		logOutLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		logOutLabel.setBounds(509, 490, 118, 50);
		frame.getContentPane().add(logOutLabel);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		
		//Switch over to the about window once the aboutButton is clicked
		aboutButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				About about = new About();
				about.getFrame().setVisible(true);
				
			}
			
		});
		
		//Switch over to the report window once the reportButton is clicked
		reportButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				frame.setVisible(false);
				Report report = new Report();
				report.getFrame().setVisible(true);
				
			}
			
		});
		
		//Switch over to the books window once the booksButton is clicked
		booksButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Books books = new Books();
				books.getFrame().setVisible(true);
				
			}
			
		});
		
		//Switch over to the account window once the accountButton is clicked
		accountButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				frame.setVisible(false);
				Account account = new Account();
				account.getFrame().setVisible(true);
				
			}
			
		});
		
		//Switch over to the login window once the logOutButton is clicked
		logOutButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				frame.setVisible(false);
				Login login = new Login();
				login.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	//Returns the menu frame
	public JFrame getFrame() {
		return frame;
	}

}
