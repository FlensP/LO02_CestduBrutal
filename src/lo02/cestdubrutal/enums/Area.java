package lo02.cestdubrutal.enums;

import lo02.cestdubrutal.objects.Player;
import lo02.cestdubrutal.objects.Student;

import java.util.ArrayList;

public enum Area {

    Library(), StudentOffice(), AdministrativeQuarter(), IndustrialsHalls(), SportsHall();

    private final ArrayList<Student> students = new ArrayList<>();

    private Player controller = null;

    public void setController(Player controller) {
        this.controller = controller;
    }

    public Player getController() {
        return controller;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void removeStudent(Student student){
        students.remove(student);
    }
    public void addStudent(Student student){
        students.add(student);
    }
}
