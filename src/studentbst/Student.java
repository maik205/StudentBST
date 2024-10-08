package studentbst;

public class Student implements Comparable<Student> {
    String stdID;
    String stdName;
    int yob;
    double gpa;
    String phoneNumber;

    public Student(String stdID, String stdName, int yob, double gpa, String phoneNumber) {
        this.stdID = stdID;
        this.stdName = stdName;
        this.yob = yob;
        this.gpa = gpa;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Student o) {
        return this.stdID.compareTo(((Student) o).stdID);
    }

    @Override
    public String toString() {
        return stdID + " " + stdName + " " + yob + " " + gpa + " " + phoneNumber;
    }

}
