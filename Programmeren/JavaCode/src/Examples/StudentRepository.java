package Examples;

import Examples.Student;
import com.company.SqlConnection;

import java.sql.*;
import java.util.*;

public class StudentRepository {
    private SqlConnection sqlConnection;

    public StudentRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public ArrayList<Student> readAll() {
        ArrayList<Student> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM STUDENTS");
            while(rs.next()) {
                lijst.add(new Student(rs.getInt("Id"),rs.getString("Name"), rs.getString("StudentNumber")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    public Student read(int id) {
        Student student = null;
        try
        {
            String sqlQuery = "SELECT * FROM STUDENTS WHERE Id=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            student = new Student(rs.getInt("Id"),rs.getString("Name"), rs.getString("StudentNumber"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return student;
    }

    public boolean create(Student student) {
        try
        {
            String sqlQuery = "INSERT INTO STUDENTS VALUES (" + student.getId() + ", '" + student.getName() + "', '" + student.getStudentNumber() + "')";
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean delete(Student student) {
       if(student==null) return false;
       return delete(student.getId());
    }

    public boolean delete(int id) {
        try
        {
            String sqlQuery = "DELETE STUDENTS WHERE Id=" + id;
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
