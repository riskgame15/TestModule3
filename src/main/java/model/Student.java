package model;

public class Student {
    private int studentId;
    private String studentName;
    private String studentClass;
    public Student(int studentId, String studentName, String studentClass){
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
