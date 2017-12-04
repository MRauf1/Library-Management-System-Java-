import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import java.sql.*;

import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class Report {

	private JFrame frame;
	
	private JLabel idLabel;
	private JTextField idText;
	private JButton returnButton;
	private JButton goBackButton;
	private JTable booksTable;
	private JScrollPane scrollPane;
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
					Report window = new Report();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application
	public Report() {
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
		goBackButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		goBackButton.setBounds(433, 470, 219, 57);
		frame.getContentPane().add(goBackButton);
		
		goBackImage = new ImageIcon(new ImageIcon("src/resources/back.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		goBackButton.setIcon(goBackImage);
		
		returnButton = new JButton("Return");
		returnButton.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		returnButton.setForeground(Color.BLUE);
		returnButton.setBounds(83, 470, 151, 60);
		frame.getContentPane().add(returnButton);
		
		idLabel = new JLabel("Enter The ID");
		idLabel.setForeground(Color.CYAN);
		idLabel.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		idLabel.setBounds(25, 401, 182, 45);
		frame.getContentPane().add(idLabel);
		
		idText = new JTextField();
		idText.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		idText.setBounds(228, 409, 68, 37);
		frame.getContentPane().add(idText);
		idText.setColumns(10);
		
		//Set up the table
		updateTable();
		
		background = new JLabel(new ImageIcon("src/resources/background.jpeg"));
		background.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		frame.getContentPane().add(background);
		
		
		//Switch over to the main menu window once the goBackButton is clicked
		goBackButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				frame.setVisible(false);
				Menu menu = new Menu();
				menu.getFrame().setVisible(true);
				
			}
			
		});
		
		//Check whether the user has the book and remove the link between it and the user
		returnButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent event) {

				Constants.setBookID(idText.getText());
				
				if(checkData()) {
					returnBook();
					//Reset the frame
					frame.setVisible(false);
					Report report = new Report();
					report.getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect ID Entered");
				}
				
			}
			
		});
	
	}
	
	//Set up and update the table
	public void updateTable() {
		
		//Get info about the user
		String userID = Constants.getUserID();
		String occupation = Constants.getOccupation();
		String sqlOccupation = occupation.equals("Student") ? "StudentID" : "TeacherID";
		
		try {
			
			//Fetch the data about user's books
			String sql = "SELECT * FROM Books WHERE " + sqlOccupation + "=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, userID);
			
			result = statement.executeQuery();
			
			//Increment
			int i = 0;
			
			//2D array containing all the rows and columns
			String[][] booksData = new String[25][5];
			
			//Array containing the headers
			String[] headers = {"ID", "Title", "Author", "Published Year", "Return Within"};
			
			//Adding the books and their info to the 2D array
			while(result.next()) {
				
				booksData[i][0] = result.getString(1);
				booksData[i][1] = result.getString(2);
				booksData[i][2] = result.getString(3);
				booksData[i][3] = result.getString(4);
				booksData[i][4] = daysLeft(result) + " days";
				
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
	
	//Returns the number of days left until the deadline
	public String daysLeft(ResultSet result) {
		
		//Gets the user's occupation
		String occupation = Constants.getOccupation();
		
		try {
			
			int deadline = occupation == "Student" ? 7 : 14;
			
			//Get info about the date when the book was checked out
			String oldDate = result.getString(7);
			String[] oldDateArray = oldDate.split(" ");
			String oldMonth = oldDateArray[1];
			int oldDay = Integer.parseInt(oldDateArray[2]);

			//Get info about the date the book is being returned
			Date newDate = new Date();
			String[] newDateArray = newDate.toString().split(" ");
			String newMonth = newDateArray[1];
			int newDay = Integer.parseInt(newDateArray[2]);
			
			Map<String, Integer> months = Constants.getDates();
			Map<String, Integer> monthsIndices = Constants.getDateIndices();
			
			String daysLeft = "";
			
			//If the book was checked out and returned within the same month, subtract their difference from the deadline
			if(months.get(oldMonth).equals(months.get(newMonth))) {
				daysLeft = Integer.toString(deadline - (newDay - oldDay));
			} else {	//Otherwise, find the days left through finding the amount of days passed
				int monthsPassed = Math.abs(monthsIndices.get(newMonth) - monthsIndices.get(oldMonth));
				int daysPassed = 0;
				for(int i = 0; i < monthsPassed; i++) {
					//1. Account for the first month
					//2. Account for the last month
					//3. Account for the rest
					if(i == 0) {
						daysPassed += months.get(oldMonth) - oldDay;
					} else if(i == (monthsPassed - 1)) {
						daysPassed += months.get(oldMonth) - newDay;
					} else {
						daysPassed += months.get(oldMonth);
					}
				}
				daysLeft = Integer.toString(deadline - daysPassed);
			}
			
			//Close the database helper
			result.close();
			
			return daysLeft;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return "";
		
	}
	
	//Return the book by removing the link between the user and the book in the database
	public void returnBook() {
		
		//Get info about the current user and the selected book
		String occupation = Constants.getOccupation();
		String sqlOccupation = occupation.equals("Student") ? "StudentID" : "TeacherID";
		String bookID = Constants.getBookID();
		
		try {
			
			//Remove the link between the current user's ID and the selected book
			String sql = "UPDATE Books SET " + sqlOccupation + "=? WHERE id=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, null);
			statement.setString(2, bookID);
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "Book Returned");
			
			//Close the database helper
			statement.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//Checks whether ID entered is valid and corresponds to the current user
	public boolean checkData() {
		
		//Get info about the current user and the selected book
		String userID = Constants.getUserID();
		String occupation = Constants.getOccupation();
		String sqlOccupation = occupation.equals("Student") ? "StudentID" : "TeacherID";
		String bookID = Constants.getBookID();
		
		boolean idExists;
		
		try {
			
			//Fetch the data
			String sql = "SELECT * FROM Books WHERE " + sqlOccupation + "=? AND id=?";
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, userID);
			statement.setString(2, bookID);
			
			result = statement.executeQuery();
			
			//If the user has the book, set the variable to true
			if(result.next()) {
				idExists = true;
			} else {	//Otherwise, set the variable to false
				idExists = false;
			}
			
			//Close the database helpers
			statement.close();
			result.close();
			
			//If everything is correct, return true
			if(! (idText.getText().isEmpty()) && Constants.isInteger(idText.getText()) && idExists) {
				return true;
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
	
	//Returns the Report frame
	public JFrame getFrame() {
		return frame;
	}
}
