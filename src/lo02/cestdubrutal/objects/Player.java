package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.Program;
import lo02.cestdubrutal.enums.Strategy;

import java.util.ArrayList;

public class Player {

    private final ArrayList<Student> students = new ArrayList<>();

    private final Program program;
    private final ArrayList<Student> reservist = new ArrayList<>();
    private int pointsLeft = 400;

    public Player(Program program) {
        this.program = program;
    }

    public void studentSetting(Student student, ArrayList<Integer> pointsGiven) {
        switch (pointsGiven.get(5)) {
            case 1 -> student.setStrategy(Strategy.Offensive);
            case 2 -> student.setStrategy(Strategy.Defensive);
            case 3 -> student.setStrategy(Strategy.Random);
        }
        pointsGiven.remove(5);
        int sumPointsGiven = 0;
        for (Integer integer : pointsGiven) {
            sumPointsGiven += integer;
        }
        if (sumPointsGiven <= pointsLeft) {
            student.setStrength(student.getStrength() + pointsGiven.get(0));
            student.setDexterity(student.getDexterity() + pointsGiven.get(1));
            student.setResistance(student.getResistance() + pointsGiven.get(2));
            student.setConstitution(student.getConstitution() + pointsGiven.get(3));
            student.setInitiative(student.getInitiative() + pointsGiven.get(4));
            setPointsLeft(getPointsLeft() - sumPointsGiven);
        }
    }

    public void chooseAsReservist(Student student) {
        if (reservist.size() < 5) {
            reservist.add(student);
        }
    }

    public void sendStudent(Student student, Area area) {
        if (!reservist.contains(student)) {
            Student stu = students.stream().filter(s -> s.equals(student)).toList().get(0);
            stu.setArea(area);
            area.addStudent(student);
        }
    }

    public void sendReservist(Student student) {
        reservist.remove(student);
    }

    public void resendStudent(Student student, Area area, Strategy strategy) {
        if ((student.getArea().getStudents().size() >= 2) && (student.getArea().getController() != null)) {
            sendStudent(student, area);
            student.setStrategy(strategy);
        } //sinon il ne peut pas renvoyer le student
    }

    public String getStudentsText(){
        StringBuilder str = new StringBuilder("");
        for (Student student : students){
            str.append((students.indexOf(student) + 1) + " " );
            str.append(student.toString() + "\n");
        }
        return str.toString();
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

    public ArrayList<Student> getReservist() {
        return reservist;
    }
}
