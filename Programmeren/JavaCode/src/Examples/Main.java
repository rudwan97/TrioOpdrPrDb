package Examples;

import com.company.SqlConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");



//        StudentRepository studentRepository = new StudentRepository(connection);
//
//        //student 1 aanmaken indien niet bestaat:
//        Student student = studentRepository.read(1); // haal id 1 op
//        if(student==null) {
//            student = new Student(1,"Piet","0156142");
//            boolean gelukt = studentRepository.create(student);
//            System.out.println("Student: " + student.getName() + " aanmaken gelukt: " + gelukt);
//        }
//
//        //student 2 aanmaken indien niet bestaat:
//        student = studentRepository.read(2); // haal id 2 op
//        if(student==null) {
//            student = new Student(2,"Yvonne","0156283");
//            boolean gelukt = studentRepository.create(student);
//            System.out.println("Student: " + student.getName() + " aanmaken gelukt: " + gelukt);
//        }
//
//        //student 3 aanmaken indien niet bestaat:
//        student = studentRepository.read(3); // haal id 3 op
//        if(student==null) {
//            student = new Student(3,"Carol","0156364");
//            boolean gelukt = studentRepository.create(student);
//            System.out.println("Student: " + student.getName() + " aanmaken gelukt: " + gelukt);
//        }
//
//        //Toon studenten:
//        ArrayList<Student> students = studentRepository.readAll();
//        for(Student s: students)
//        {
//            System.out.println(s);
//        }
//
//        //Wis student 3:
//        studentRepository.delete(3);
//
//        //Toon studenten:
//        students = studentRepository.readAll();
//        for(Student s: students)
//        {
//            System.out.println(s);
//        }

        connection.disconnectDatabase();

    }
}
