import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class EditBooks {

	private JFrame frame;
	
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel publishedYearLabel;
	private JTextField titleText;
	private JTextField authorText;
	private JTextField publishedYearText;
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
					EditBooks window = new EditBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public EditBooks() {
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
		titleLabel.setBounds(20, 118, 95, 55);
		frame.getContentPane().add(titleLabel);
		
		authorLabel = new JLabel("Author");
		authorLabel.setForeground(Color.CYAN);
		authorLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		authorLabel.setBounds(20, 229, 133, 38);
		frame.getContentPane().add(authorLabel);
		
		publishedYearLabel = new JLabel("Published Year");
		publishedYearLabel.setForeground(Color.CYAN);
		publishedYearLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		publishedYearLabel.setBounds(20, 319, 199, 45);
		frame.getContentPane().add(publishedYearLabel);
		
		titleText = new JTextField();
		titleText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleText.setBounds(125, 129, 350, 38);
		frame.getContentPane().add(titleText);
		titleText.setColumns(10);
		
		authorText = new JTextField();
		authorText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		authorText.setBounds(163, 228, 312, 45);
		frame.getContentPane().add(authorText);
		authorText.setColumns(10);
		
		publishedYearText = new JTextField();
		publishedYearText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		publishedYearText.setBounds(245, 321, 230, 45);
		frame.getContentPane().add(publishedYearText);
		publishedYearText.setColumns(10);
		
		applyButton = new JButton("Apply");
		applyButton.setForeground(Color.BLUE);
		applyButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		applyButton.setBounds(115, 414, 199, 92);
		frame.getContentPane().add(applyButton);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setForeground(Color.BLUE);
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		goBackButton.setBounds(442, 414, 199, 92);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		//Set up the table
		prepareData();
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		
		//Check the fields and apply all the entered changes once the applyButton is clicked
		applyButton.addActionListener(new java.awt.event.ActionListener() {

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
	
	//Set up the table with all the books from the database
	public void prepareData() {
		
		//Get selected book's ID
		String id = Constants.getBookID();

		try {
			
			//Fetch the data
			String sql = "Select * from Books where Id=?";
			statement = conn.prepareStatement(sql);

			statement.setString(1, id);
			result = statement.executeQuery();
			
			//Write all the info to the text boxes
			titleText.setText(result.getString(2));
			authorText.setText(result.getString(3));
			publishedYearText.setText(result.getString(4));

			//Close the database helpers
			result.close();
			statement.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}
	
	//Changes the data about the selected books and stores the changes in the database
	public void changeData() {
		
		//Get the selected book's ID
		String id = Constants.getBookID();
		
		try {
			
			//Edit the info in the database
			String sql = "Update Books set title=?, author=?, publishedYear=? where Id=" + id;
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, titleText.getText());
			statement.setString(2, authorText.getText());
			statement.setString(3, publishedYearText.getText());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Changes Applied");
			
			//Close the database helper
			statement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		//Switch over to the books frame
		frame.setVisible(false);
		Books books = new Books();
		books.getFrame().setVisible(true);
		
	}
	
	//Check whether the fields are entered correctly
	//If they are, apply the changes
	//If not, inform the user
	public void checkData() {
		
		boolean isValidTitle = ! (titleText.getText().isEmpty());
		boolean isValidAuthor = ! (authorText.getText().isEmpty());
		boolean isValidPublishedYear = ! (publishedYearText.getText().isEmpty());
		
		if(isValidTitle && isValidAuthor && isValidPublishedYear) {
			changeData();
		} else {
			JOptionPane.showMessageDialog(null, "Please Fill The Fields Correctly");
		}
		
	}
	
	//Returns the editBooks frame
	public JFrame getFrame() {
		return frame;
	}

}
