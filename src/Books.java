import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class Books {

	private JFrame frame;
	
	private JTable booksTable;
	private JScrollPane scrollPane;
	private JButton goBackButton;
	private JButton addButton;
	private JButton removeButton;
	private JLabel idRemoveLabel;
	private JButton checkOutButton;
	private JButton editButton;
	private JTextField idRemoveText;
	private JLabel idEditLabel;
	private JTextField idEditText;
	private JLabel idCheckOutLabel;
	private JTextField idCheckOutText;
	private JLabel background;
	private ImageIcon addImage;
	private ImageIcon removeImage;
	private ImageIcon goBackImage;
	
	private Connection conn;
	private ResultSet result;
	private PreparedStatement statement;
	
	private Map<String, String> bookAvailability;

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Books window = new Books();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public Books() {
		conn = JavaConnect.getConnection();
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
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		goBackButton.setBounds(581, 456, 179, 68);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		addButton = new JButton("Add Books");
		addButton.setForeground(Color.BLUE);
		addButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		addButton.setBounds(41, 486, 179, 38);
		frame.getContentPane().add(addButton);
		
		addImage = new ImageIcon(new ImageIcon("src/resources/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		addButton.setIcon(addImage);
		
		removeButton = new JButton("Remove A Book");
		removeButton.setForeground(Color.BLUE);
		removeButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		removeButton.setBounds(263, 486, 248, 38);
		frame.getContentPane().add(removeButton);
		
		removeImage = new ImageIcon(new ImageIcon("src/resources/remove.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		removeButton.setIcon(removeImage);
		
		idRemoveText = new JTextField();
		idRemoveText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idRemoveText.setBounds(423, 446, 77, 29);
		frame.getContentPane().add(idRemoveText);
		idRemoveText.setColumns(10);
		
		idRemoveLabel = new JLabel("Enter The ID");
		idRemoveLabel.setForeground(Color.CYAN);
		idRemoveLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		idRemoveLabel.setBounds(238, 438, 158, 37);
		frame.getContentPane().add(idRemoveLabel);
		
		idCheckOutLabel = new JLabel("Enter The ID");
		idCheckOutLabel.setForeground(Color.CYAN);
		idCheckOutLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		idCheckOutLabel.setBounds(26, 319, 159, 30);
		frame.getContentPane().add(idCheckOutLabel);
		
		idCheckOutText = new JTextField();
		idCheckOutText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idCheckOutText.setBounds(183, 321, 68, 27);
		frame.getContentPane().add(idCheckOutText);
		idCheckOutText.setColumns(10);
		
		checkOutButton = new JButton("Check Out A Book");
		checkOutButton.setForeground(Color.BLUE);
		checkOutButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		checkOutButton.setBounds(26, 367, 227, 38);
		frame.getContentPane().add(checkOutButton);
		
		idEditLabel = new JLabel("Enter The ID");
		idEditLabel.setForeground(Color.CYAN);
		idEditLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		idEditLabel.setBounds(532, 310, 130, 29);
		frame.getContentPane().add(idEditLabel);
		
		idEditText = new JTextField();
		idEditText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idEditText.setBounds(672, 310, 46, 29);
		frame.getContentPane().add(idEditText);
		idEditText.setColumns(10);
		
		editButton = new JButton("Edit Books");
		editButton.setForeground(Color.BLUE);
		editButton.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		editButton.setBounds(532, 364, 186, 45);
		frame.getContentPane().add(editButton);
		
		//Sets up the table
		updateTable();
		
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
		
		//Current user will check out the selected book once the checkOutButton is clicked
		checkOutButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				Constants.setBookID(idCheckOutText.getText());
				
				if(bookAvailability.get(Constants.getBookID()) == null) {
					JOptionPane.showMessageDialog(null, "Book With That ID Does Not Exist");
				} else {
					if(checkBookAvailability()) {
						checkOutBook();
					} else {
						JOptionPane.showMessageDialog(null, "The Book Is Currently Unavailable");
					}
				}
				
			}
			
		});
		
		//Switch over to the addBooks window once the addButton is clicked
		addButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				frame.setVisible(false);
				AddBooks addBooks = new AddBooks();
				addBooks.getFrame().setVisible(true);
				
			}
			
		});
		
		//Removes the selected book once the removeButton is clicked
		removeButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				Constants.setBookID(idRemoveText.getText());
				if(checkRemoveData()) {
					removeBook();
					updateTable();
				} else {
					JOptionPane.showMessageDialog(null, "Book With That ID Does Not Exist");
				}
				 
			}
			
		});
		
		//Depending on whether the ID is valid, switch over to the editBooks frame once the editButton is clicked
		editButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				checkEditData();
				
			}
			
		});
	
	}
	
	//Check whether the entered ID is valid and exists and based on that, switch the frame or inform the user
	public void checkEditData() {
		
		//Get info about the selected book
		String bookID = idEditText.getText();
		
		boolean isValidEditData = ! (bookID.isEmpty()) && Constants.isInteger(bookID) && bookAvailability.get(bookID) != null;
		
		//If the ID is valid, proceed to the editBooks frame
		if(isValidEditData) {
			Constants.setBookID(idEditText.getText());
			frame.setVisible(false);
			EditBooks editBooks = new EditBooks();
			editBooks.getFrame().setVisible(true);
		} else {	//Otherwise, inform the user
			JOptionPane.showMessageDialog(null, "Please Enter A Valid ID");
		}
		
	}
	
	//Removes the selected book from the database
	public void removeBook() {
		
		//Get the selected book's ID
		String bookID = Constants.getBookID();
		
		try {
			
			//Remove the book from the database
			String sql = "DELETE FROM Books WHERE id=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, bookID);
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "Book Removed");
			
			//Close the database helper
			statement.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		//Reset the frame
		frame.setVisible(false);
		Books books = new Books();
		books.getFrame().setVisible(true);
		
	}
	
	//Check whether the selected book is valid
	public boolean checkRemoveData() {
		
		//Get info about the selected book
		String bookID = Constants.getBookID();
		
		try {
			
			//Fetch the data
			String sql = "SELECT * FROM Books WHERE id=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, bookID);
			
			result = statement.executeQuery();
			
			//If the selected book exists in the database, return true
			if(result.next()) {
				//Close the database helpers
				statement.close();
				result.close();
				return true;
			}
			
			//Close the database helpers
			statement.close();
			result.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
	
	//Sets up the table by showing all the books from the database
	public void updateTable() {
		
		try {
			
			//Fetch all the books
			String sql = "Select * from Books";
			statement = conn.prepareStatement(sql);
			
			result = statement.executeQuery();
			
			//Increment
			int i = 0;
			
			//Stores all the books and their info
			String[][] booksData = new String[25][5];
			
			//Stores the headers of the booksTable
			String[] headers = {"ID", "Title", "Author", "Published Year", "Available"};
			
			//Stores the ID and its corresponding availability in the HashMap
			bookAvailability = new HashMap<String, String>();
			
			//Store all the books and all the data inside the 2D array
			while (result.next()) {
				
				booksData[i][0] = result.getString(1);
				booksData[i][1] = result.getString(2);
				booksData[i][2] = result.getString(3);
				booksData[i][3] = result.getString(4);
				
				//Check whether the book is available or not
				boolean isAvailable = result.getString(5) == null && result.getString(6) == null;
				
				if(isAvailable) {
					booksData[i][4] = "Yes";
				} else {
					booksData[i][4] = "No";
				}
				
				bookAvailability.put(booksData[i][0], booksData[i][4]);
				
				i++;
				
			}
			
			//Close the database helpers
			statement.close();
			result.close();
			
			//Create a table with all the books and their info inside
			booksTable = new JTable(booksData, headers);
			booksTable.setBounds(42, 23, 668, 430);
			
			scrollPane = new JScrollPane(booksTable);
			scrollPane.setBounds(25, 21, 711, 268);
			frame.getContentPane().add(scrollPane);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//Current user will check out the selected book
	public void checkOutBook() {
		
		//Get current user's info and selected book's ID and the date
		String userID = Constants.getUserID();
		String occupation = Constants.getOccupation();
		String sqlOccupation = occupation.equals("Student") ? "studentID" : "teacherID";
		String bookID = Constants.getBookID();
		Date date = new Date();
		
		try {
			
			//Connect the user's ID to the Books table in the database
			String sql = "UPDATE Books SET " + sqlOccupation + "=?, IssuedDate=? WHERE Id=" + bookID;
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, userID);
			statement.setString(2, date.toString());
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "Book Checked Out");
			
			//Close the database helper
			statement.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		//Reset the frame
		frame.setVisible(false);
		Books books = new Books();
		books.getFrame().setVisible(true);
		
	}
	
	//Checks whether the selected book is available by looking it up in the Map
	public boolean checkBookAvailability() {
		
		String bookID = Constants.getBookID();
		
		if(bookAvailability.get(bookID).equals("Yes")) {
			return true;
		}
		
		return false;
		
	}
	
	//Returns the books frame
	public JFrame getFrame() {
		return frame;
	}
}
