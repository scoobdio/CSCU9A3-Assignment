public class Person {

    protected String fullName;
    protected int registrationNumber;
    protected String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fn) {
        this.fullName = fn;
    }

    public int getRegistrationNumber(){
        return registrationNumber;
    }

    public void setRegistrationNumber(int rn){ this.registrationNumber = rn; }

    public String getEmail() { return email; }

    public void setEmail(String em) {
        this.email = em;
    }

}
