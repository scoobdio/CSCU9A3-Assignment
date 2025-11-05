public class Professor
{
	protected String fullName;
	protected int registrationNumber;
	protected String email;
	
	protected String division;
	protected double work_percentage;
	
	public Professor(String fn, int rn, String email, String d, double wp) {
		this.fullName = fn;
		this.registrationNumber = rn;
		this.email = email;
		this.division = d;
		this.work_percentage = wp;
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
	
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public double getWork_percentage() {
		return work_percentage;
	}

	public void setWork_percentage(double work_percentage) {
		this.work_percentage = work_percentage;
	}

	public String toString() {
		return "Professor: " + fullName; 
	}
}
