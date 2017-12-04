import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.sql.*;
import java.awt.Font;
import java.awt.Color;

public class AddBooks {

	private JFrame frame;
	
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel publishedYearLabel;
	private JTextField titleText;
	private JTextField authorText;
	private JTextField publishedYearText;
	private JButton addButton;
	private JButton goBackButton;
	private JLabel background;
	private ImageIcon goBackImage;
	private ImageIcon addImage;
	
	private Connection conn;
	private PreparedStatement statement;
	

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBooks window = new AddBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public AddBooks() {
		conn = JavaConnect.getConnection();
		initialize();
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(500, 100, Constants.WIDTH, Constants.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		titleLabel = new JLabel("Title");
		titleLabel.setForeground(Color.CYAN);
		titleLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		titleLabel.setBounds(30, 180, 129, 30);
		frame.getContentPane().add(titleLabel);
		
		authorLabel = new JLabel("Author");
		authorLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		authorLabel.setForeground(Color.CYAN);
		authorLabel.setBounds(30, 249, 137, 38);
		frame.getContentPane().add(authorLabel);
		
		publishedYearLabel = new JLabel("Year Published");
		publishedYearLabel.setForeground(Color.CYAN);
		publishedYearLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		publishedYearLabel.setBounds(30, 331, 200, 38);
		frame.getContentPane().add(publishedYearLabel);
		
		titleText = new JTextField();
		titleText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleText.setBounds(169, 180, 298, 27);
		frame.getContentPane().add(titleText);
		titleText.setColumns(10);
		
		authorText = new JTextField();
		authorText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		authorText.setBounds(203, 255, 264, 30);
		frame.getContentPane().add(authorText);
		authorText.setColumns(10);
		
		publishedYearText = new JTextField();
		publishedYearText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		publishedYearText.setBounds(281, 331, 186, 33);
		frame.getContentPane().add(publishedYearText);
		publishedYearText.setColumns(10);
		
		addButton = new JButton("Add");
		addButton.setForeground(Color.BLUE);
		addButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		addButton.setBounds(139, 441, 147, 68);
		frame.getContentPane().add(addButton);
		
		addImage = new ImageIcon(new ImageIcon("src/resources/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		addButton.setIcon(addImage);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setBounds(464, 441, 233, 68);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		
		//Check the entered fields and add the new book to the database once the addButton is clicked
		addButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				checkData();
				
			}
			
		});
		
		//Switch over to the books window once the goBackButton is clicked
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Books books = new Books();
				books.getFrame().setVisible(true);
				
			}
			
		});
		
	}
	
	//Once the fields are entered correctly, add the new book to the database
	public void addBook() {
		
		try {
			
			//Insert the book with all its info to the database
			String sql = "Insert into Books (title, author, publishedYear) values (?, ?, ?)";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, titleText.getText());
			statement.setString(2, authorText.getText());
			statement.setString(3, publishedYearText.getText());
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "Book Added");
			
			//Close the database helper
			statement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		//Clear the fields
		titleText.setText("");
		authorText.setText("");
		publishedYearText.setText("");
		
	}
	
	//Check whether the fields are entered correctly
	public void checkData() {
		
		String publishedYear = publishedYearText.getText();
		
		boolean isValidTitle = ! (titleText.getText().isEmpty());
		boolean isValidAuthor = ! (authorText.getText().isEmpty());
		boolean isValidPublishedYear = ! (publishedYear.isEmpty()) && Constants.isInteger(publishedYear);
		
		//If everything is correct, add the new book
		if(isValidTitle && isValidAuthor && isValidPublishedYear) {
			addBook();
		} else {	//If the fields are not entered correctly, inform the user
			JOptionPane.showMessageDialog(null, "Please Enter The Fields Correctly");
		}
		
	}
	
	//Returns the addBooks frame
	public JFrame getFrame() {
		return frame;
	}
	
}
