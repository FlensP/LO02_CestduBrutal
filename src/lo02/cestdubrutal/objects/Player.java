package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.enums.Program;

import java.util.ArrayList;

public class Player {

    private final ArrayList<Student> students = new ArrayList<>();
    private final Program program;

    public Player(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }
}
