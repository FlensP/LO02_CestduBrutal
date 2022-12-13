package lo02.cestdubrutal.enums;

import lo02.cestdubrutal.objects.Player;
import lo02.cestdubrutal.objects.Student;

import java.util.ArrayList;

public enum Area {

    Library(), StudentOffice(), AdministrativeQuarter(), IndustrialsHalls(), SportsHall();

    private final ArrayList<Student> students = new ArrayList<>();

    private Player controller = null;

    public Player getController() {
        return controller;
    }

    public void setController(Player controller) {
        this.controller = controller;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        switch (this){
            case Library -> {
                return "Bibliotheque";
            }
            case SportsHall -> {
                return "Halle Sportive";
            }
            case StudentOffice -> {
                return "BDE";
            }
            case IndustrialsHalls -> {
                return "Halles Industrielles";
            }
            case AdministrativeQuarter ->{
                return "Quartier Administratif";
            }
        }
        return "Reserviste";
    }
}

