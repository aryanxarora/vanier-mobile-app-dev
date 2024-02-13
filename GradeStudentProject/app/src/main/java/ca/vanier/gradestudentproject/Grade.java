package ca.vanier.gradestudentproject;

public class Grade {
    private int id;
    private String lastName;
    private String firstName;
    private int s_grade_Assignment1;
    private int s_grade_Assignment2;
    private int s_grade_Assignment3;
    private int s_grade_Mid_Term;
    private int s_grade_Final_Term;

    public Grade() {
    }

    public Grade(int id, String lastName, String firstName, int s_grade_Assignment1, int s_grade_Assignment2, int s_grade_Assignment3, int s_grade_Mid_Term, int s_grade_Final_Term) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.s_grade_Assignment1 = s_grade_Assignment1;
        this.s_grade_Assignment2 = s_grade_Assignment2;
        this.s_grade_Assignment3 = s_grade_Assignment3;
        this.s_grade_Mid_Term = s_grade_Mid_Term;
        this.s_grade_Final_Term = s_grade_Final_Term;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getS_grade_Assignment1() {
        return s_grade_Assignment1;
    }

    public void setS_grade_Assignment1(int s_grade_Assignment1) {
        this.s_grade_Assignment1 = s_grade_Assignment1;
    }

    public int getS_grade_Assignment2() {
        return s_grade_Assignment2;
    }

    public void setS_grade_Assignment2(int s_grade_Assignment2) {
        this.s_grade_Assignment2 = s_grade_Assignment2;
    }

    public int getS_grade_Assignment3() {
        return s_grade_Assignment3;
    }

    public void setS_grade_Assignment3(int s_grade_Assignment3) {
        this.s_grade_Assignment3 = s_grade_Assignment3;
    }

    public int getS_grade_Mid_Term() {
        return s_grade_Mid_Term;
    }

    public void setS_grade_Mid_Term(int s_grade_Mid_Term) {
        this.s_grade_Mid_Term = s_grade_Mid_Term;
    }

    public int getS_grade_Final_Term() {
        return s_grade_Final_Term;
    }

    public void setS_grade_Final_Term(int s_grade_Final_Term) {
        this.s_grade_Final_Term = s_grade_Final_Term;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", s_grade_Assignment1=" + s_grade_Assignment1 +
                ", s_grade_Assignment2=" + s_grade_Assignment2 +
                ", s_grade_Assignment3=" + s_grade_Assignment3 +
                ", s_grade_Mid_Term=" + s_grade_Mid_Term +
                ", s_grade_Final_Term=" + s_grade_Final_Term +
                '}';
    }

    public double calculateGradeAverage(){
        double assignmentTotal = s_grade_Assignment1 + s_grade_Assignment2 + s_grade_Assignment3;
        double assignmentAverage = assignmentTotal / 3.0;
        double totalAverage = (assignmentAverage * 0.4) + (s_grade_Mid_Term * 0.3) + (s_grade_Final_Term * 0.3);
        return totalAverage;
    }
}
