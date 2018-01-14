package Examples;

public class Student {


    private int id;
    private String name;
    private String studentNumber;

    public Student(int id, String name, String studentNumber){
        this.id = id;
        this.name = name;
        this.studentNumber = studentNumber;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentNumber(String studentNumber ) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " Name: " + this.name + " Studentnumber: " + this.studentNumber;
    }

}
