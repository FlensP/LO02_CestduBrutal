package lo02.cestdubrutal;

import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.Program;
import lo02.cestdubrutal.objects.Player;
import lo02.cestdubrutal.objects.Student;

import java.util.ArrayList;

public interface Ask {

    Program askProgram(String s);
    ArrayList<Integer> askRepartition(String s);
    Student askStudent(String s, Player p);
    Student askStudentOrNot(String s, Player p);
    Area askArea(String s);
}
