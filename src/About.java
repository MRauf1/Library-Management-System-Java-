import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class About {

	private JFrame frame;
	private JButton goBackButton;
	private JLabel lmsLabel;
	private JLabel authorLabel;
	private JLabel bugsLabel1;
	private JLabel bugsLabel2;
	private JLabel background;
	private ImageIcon goBackImage;
	private JLabel versionLabel;

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About window = new About();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public About() {
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(500, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		goBackButton.setBounds(409, 452, 264, 69);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		lmsLabel = new JLabel("Library Management System");
		lmsLabel.setForeground(Color.CYAN);
		lmsLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		lmsLabel.setBounds(27, 189, 404, 50);
		frame.getContentPane().add(lmsLabel);
		
		authorLabel = new JLabel("Created By Rauf Makharov");
		authorLabel.setForeground(Color.CYAN);
		authorLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		authorLabel.setBounds(27, 287, 404, 45);
		frame.getContentPane().add(authorLabel);
		
		bugsLabel1 = new JLabel("For Any Feedback Or For Reporting Bugs, ");
		bugsLabel1.setForeground(Color.CYAN);
		bugsLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bugsLabel1.setBounds(27, 332, 404, 63);
		frame.getContentPane().add(bugsLabel1);
		
		bugsLabel2 = new JLabel("Please E-Mail Rauf Makharov At rmakharov6040@stu.lmtsd.org");
		bugsLabel2.setForeground(Color.CYAN);
		bugsLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bugsLabel2.setBounds(37, 385, 568, 56);
		frame.getContentPane().add(bugsLabel2);
		
		versionLabel = new JLabel("Version 1.0 Beta");
		versionLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		versionLabel.setForeground(Color.CYAN);
		versionLabel.setBounds(71, 250, 291, 36);
		frame.getContentPane().add(versionLabel);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
	
		
		
		//Switch over to the menu window once the goBackButton is clicked
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Menu menu = new Menu();
				menu.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	//Returns the about frame
	public JFrame getFrame() {
		return frame;
	}
	
}
