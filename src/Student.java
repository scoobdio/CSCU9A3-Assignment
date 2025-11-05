import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student
{
	protected static int MAX_NUM_MODULES = 5; // maximum number of modules that a student can enrol

	protected String fullName;
	protected int registrationNumber;
	protected String email;
	protected List<Module> modules;
	
	/**
	 * Constructor
	 * @param fn Full Name
	 * @param rn Registration Number
	 * @param email Email
	 */
	public Student(String fn, int rn, String email) {
		this.fullName = fn;
		this.registrationNumber = rn;
		this.email = email;
		this.modules = new ArrayList<>();
	}

	/**
	 * constructor
	 * generate a student at random
	 * @param rnd
	 */
	public Student(Random rnd) {
		final String[] firstNames = {
				"Aiden", "Bella", "Callum", "Daria", "Ewan",
				"Freya", "Gavin", "Holly", "Ishan", "Jade",
				"Kara", "Liam", "Maya", "Noah", "Orla",
				"Priya", "Quinn", "Rory", "Sofia", "Toby"
		};

		final String[] lastNames = {
				"Anderson", "Bennett", "Campbell", "Davies", "Ellis",
				"Foster", "Gray", "Hughes", "Irving", "Johnson",
				"Kerr", "Lewis", "Murray", "Nguyen", "O'Connor",
				"Patel", "Quinn", "Reid", "Stewart", "Turner"
		};

		String fn = firstNames[rnd.nextInt(firstNames.length)];
		String ln = lastNames[rnd.nextInt(lastNames.length)];
		int number = 1000000 + rnd.nextInt(899999);

		this.fullName = fn + " " + ln;
		this.registrationNumber = number;
		this.email = fn + "." + ln + "@stir.ac.uk";
		this.modules = new ArrayList<>();
	}

	/**
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * Set/change the full name
	 * @param fn The new full name
	 */
	public void setFullName(String fn) {
		this.fullName = fn;
	}

	/**
	 * @return the registration number
	 */
	public int getRegistrationNumber(){
		return registrationNumber;
	}
	/**
	 * Set/change the registration number
	 * @param rn The new registration number
	 */
	public void setRegistrationNumber(int rn) {
		this.registrationNumber = rn;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}
	/**
	 * Set/change the email
	 * @param em The new email
	 */
	public void setEmail(String em) {
		this.email = em;
	}
	
	/**
	 * Add a new module to the list of modules of the student
	 * @param m -The new module to be added
	 * @return true if the module was added successfully
	 */
	public boolean addModule(Module m) {
		if (modules.size() < MAX_NUM_MODULES && !modules.contains(m)) {
			return modules.add(m);
		} else {
			return false;
		}
	}

	/**
	 * Remove a module of the list of modules of the student
	 * @return true if the customer was removed successfully
	 */
	public boolean removeModule(Module m) {
		return modules.remove(m);
	}
	
	/**
	 * Get a description of the Student as a String
	 */
	public String toString() {
		return "Student: " + fullName + " - StudentNo: " + registrationNumber; 
	}
}
