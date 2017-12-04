import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	//The program's width and height
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	//The deadlines for students and teachers
	public static final int STUDENT_DEADLINE = 7;
	public static final int TEACHER_DEADLINE = 14;
	
	//Info about the current user
	private static String userID;
	private static String occupation;
	
	//Selected book's ID
	private static String bookID;
	
	//Returns the current user's ID
	public static String getUserID() {
		return userID;
	}
	
	//Changes the current user's ID
	public static void setUserID(String id) {
		userID = id;
	}
	
	//Returns the current user's occupation
	public static String getOccupation() {
		return occupation;
	}
	
	//Change's the current user's occupation
	public static void setOccupation(String newOccupation) {
		occupation = newOccupation;
	}
	
	//Returns the selected book's ID
	public static String getBookID() {
		return bookID;
	}
	
	//Changes the selected book's ID
	public static void setBookID(String newBookID) {
		bookID = newBookID;
	}
	
	//Checks whether the passed String is an integer
	//If the string is an integer, return true
	//Otherwise, return false
	public static boolean isInteger(String value) {

		try {
			Integer.parseInt(value);
		} catch(NumberFormatException e) {
			return false;
		}

		return true;

	}
	
	public static Map<String, Integer> getDates() {
		
		Map<String, Integer> dates = new HashMap<String, Integer>();
		
		dates.put("Jan", 31);
		dates.put("Feb", 28);
		dates.put("Mar", 31);
		dates.put("Apr", 30);
		dates.put("May", 31);
		dates.put("Jun", 30);
		dates.put("Jul", 31);
		dates.put("Aug", 31);
		dates.put("Sep", 30);
		dates.put("Oct", 31);
		dates.put("Nov", 30);
		dates.put("Dec", 31);
		
		return dates;
		
	}
	
	public static Map<String, Integer> getDateIndices() {
		
		Map<String, Integer> dates = new HashMap<String, Integer>();

		dates.put("Jan", 1);
		dates.put("Feb", 2);
		dates.put("Mar", 3);
		dates.put("Apr", 4);
		dates.put("May", 5);
		dates.put("Jun", 6);
		dates.put("Jul", 7);
		dates.put("Aug", 8);
		dates.put("Sep", 9);
		dates.put("Oct", 10);
		dates.put("Nov", 11);
		dates.put("Dec", 12);

		return dates;
		
	}
	
}
