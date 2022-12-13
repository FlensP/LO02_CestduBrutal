package lo02.cestdubrutal;

import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.Program;
import lo02.cestdubrutal.objects.Player;
import lo02.cestdubrutal.objects.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class TextAsk implements Ask {


    Scanner scanner = new Scanner(System.in);

    @Override
    public Program askProgram(String s) {
        Program p = null;
        while (p == null) {
            System.out.println(s);
            String s1 = scanner.nextLine();
            switch (s1) {
                case "ISI" -> p = Program.ISI;
                case "RT" -> p = Program.RT;
                case "GM" -> p = Program.GM;
                case "GI" -> p = Program.GI;
                case "A2I" -> p = Program.A2I;
                case "MM" -> p = Program.MM;
                case "MTE" -> p = Program.MTE;
            }
        }
        return p;
    }

    @Override
    public ArrayList<Integer> askRepartition(String s) {
        ArrayList<Integer> ints = new ArrayList<>();
        boolean format = false;
        while (!format) {
            ints.clear();
            System.out.println(s);
            System.out.println("de la forme 2/0/4/0/5/1 (force/dexterite/resistance/constitution/initiative/strategie avec 1 = offensif 2 def et 3 random)");
            String s1 = scanner.nextLine();
            if (s1.split("/").length == 6) {
                for (String s2 : s1.split("/")) {
                    try {
                        ints.add(Integer.valueOf(s2));
                    } catch (NumberFormatException e) {
                        break;
                    }
                }
                if (ints.size() == 6) {
                    if (ints.get(0) <= 10 && ints.get(1) <= 10 && ints.get(2) <= 10 && ints.get(3) <= 30 && ints.get(4) <= 10 && ints.get(5) <= 3) {
                        if (ints.get(0) >= 0 && ints.get(1) >= 0 && ints.get(2) >= 0 && ints.get(3) >= 0 && ints.get(4) >= 0 && ints.get(5) > 0) {
                            format = true;
                        }
                    }
                }

            }
        }
        return ints;
    }

    @Override
    public Student askStudent(String s, Player p) {
        Student stu = null;
        while (stu == null) {
            System.out.println(s);
            int index;
            try {
                index = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                index = -1;
            }
            if (index >= 0 && index < p.getStudents().size()) {
                stu = p.getStudents().get(index);
            }
        }

        return stu;
    }

    @Override
    public Student askStudentOrNot(String s, Player p) {
        Student stu = null;
        while (stu == null) {
            System.out.println(s);
            int index;
            try {
                index = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                if (s.equals("N")) {
                    return null;
                }
                index = -1;
            }
            if (index >= 0 && index < p.getStudents().size()) {
                stu = p.getStudents().get(index);
            }
        }

        return stu;
    }

    @Override
    public Area askArea(String s) {
        Area a = null;
        while (a == null) {
            System.out.println(s);
            System.out.println("Entrez L pour la bibliotheque, A pour quartier admin, I pour halles indus, B pour BDE et S pour halles sportives");
            String s1 = scanner.nextLine();
            switch (s1) {
                case "L" -> a = Area.Library;
                case "A" -> a = Area.AdministrativeQuarter;
                case "I" -> a = Area.IndustrialsHalls;
                case "B" -> a = Area.StudentOffice;
                case "S" -> a = Area.SportsHall;
            }
        }
        return a;
    }
}
