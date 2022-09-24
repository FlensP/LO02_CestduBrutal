package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.Program;
import lo02.cestdubrutal.enums.Strategy;

import java.util.ArrayList;

public class Player {

    private final ArrayList<Student> students = new ArrayList<>();

    private final Program program;
    private final ArrayList<Student> reservist = new ArrayList<>();
    private int pointsLeft = 0;

    public Player(Program program) {
        this.program = program;
    }

    public void studentSetting(Student student, ArrayList<Integer> pointsGiven) {
        int sumPointsGiven = pointsGiven.stream().mapToInt(Integer::intValue).sum();
        if (sumPointsGiven <= pointsLeft) {
            Student stu = students.stream().filter(s -> equals(student)).toList().get(0); //pour être sur que l'élève est de notre équipe
            stu.setStrength(stu.getStrength() + pointsGiven.get(0));
            stu.setDexterity(stu.getDexterity() + pointsGiven.get(1));
            stu.setResistance(stu.getResistance() + pointsGiven.get(2));
            stu.setConstitution(stu.getConstitution() + pointsGiven.get(3));
            stu.setInitiative(stu.getInitiative() + pointsGiven.get(4));
            setPointsLeft(getPointsLeft() - sumPointsGiven);
        }
        //todo voir si on peut pas rendre la fonction moins longue et comment généraliser à une liste
    }

    public void chooseAsReservist(Student student) {
        if (reservist.size() != 5) {
            reservist.add(student);
        }
    }

    public void allocateStrategy(Student student, Strategy strategy) {
        Student stu = students.stream().filter(s -> equals(student)).toList().get(0);
        stu.setStrategy(strategy);
    }

    public void sendStudent(Student student, Area area) {
        if (!reservist.contains(student)) {
            Student stu = students.stream().filter(s -> equals(student)).toList().get(0);
            stu.setArea(area);
            area.addStudent(student);
        }
    }

    public void sendReservist(Student student){
        reservist.remove(student);
        //todo forcer le player à au moins envoyer un reserviste
    }

    public void resendStudent(Student student,Area area,Strategy strategy) {
        if ((student.getArea().getStudents().size() >= 2) && (student.getArea().getController() != null)){
            sendStudent(student,area);
            student.setStrategy(strategy);
        } //sinon il ne peut pas renvoyer le student
    }

    public Program getProgram() {
        return program;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public void setPointsLeft(int pointsLeft) {
        this.pointsLeft = pointsLeft;
    }

}
