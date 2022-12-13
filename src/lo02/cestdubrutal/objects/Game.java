package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.Ask;
import lo02.cestdubrutal.Main;
import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.StateGame;

import java.util.ArrayList;
import java.util.Comparator;

public class Game {

    private StateGame state;
    private Player player1;
    private Player player2;

    public Game() {
        init();
    }

    public void init() {
        Ask ask = Main.getInstance().getAsk();
        setState(StateGame.Configuration);
        player1 = new Player(ask.askProgram("Veuillez entrer le programme du joueur 1, attention a l'orthographe"));
        while (player2 == null || player1.getProgram() == player2.getProgram()) {
            player2 = new Player(ask.askProgram("Veuillez entrer le programme du joueur 2 (different du joueur 1, attention a l'orthographe"));
        }
        allocation();

    }

    public void allocation() {
        Ask ask = Main.getInstance().getAsk();
        initStudent(player1);
        initStudent(player2);
        Main.getInstance().getDisplay().displayMessage("Allocation des points pour le joueur 1");
        String name;
        for (Student student : player1.getStudents()) {
            if (student instanceof GobiMaster) {
                name = "Maitre du Gobi";
            } else if (student instanceof EliteStudent) {
                name = "Etudiant d'elite";
            } else {
                name = "etudiant";
            }
            player1.studentSetting(student, ask.askRepartition("Repartition pour un " + name + " " + (player1.getStudents().indexOf(student) + 1)));
            Main.getInstance().getDisplay().displayMessage("Il vous reste " + player1.getPointsLeft() + " points restants.");
        }
        Main.getInstance().getDisplay().displayMessage("Au tour du joueur 2");
        for (Student student : player2.getStudents()) {
            if (student instanceof GobiMaster) {
                name = "Maitre du Gobi";
            } else if (student instanceof EliteStudent) {
                name = "Etudiant d'elite";
            } else {
                name = "etudiant";
            }
            player2.studentSetting(student, ask.askRepartition("Repartition pour un " + name + " " + (player2.getStudents().indexOf(student) + 1)));
            Main.getInstance().getDisplay().displayMessage("Il vous reste " + player2.getPointsLeft() + " points restants.");
        }
        for (Student student : player1.getStudents()) {
            student.setCreditECTS(30 + student.getConstitution());
        }
        for (Student student : player2.getStudents()) {
            student.setCreditECTS(30 + student.getConstitution());
        }
        chooseReservist();
        repartition();
    }

    public void chooseReservist() {
        Main.getInstance().getDisplay().displayMessage("Joueur 1");
        Main.getInstance().getDisplay().displayMessage(player1.getStudentsText());
        while (player1.getReservist().size() < 5) {
            Student stu = Main.getInstance().getAsk().askStudent("Choisissez un etudiant qui sera reserviste (envoyez l'index)", player1);
            if (!player1.getReservist().contains(stu)) {
                player1.chooseAsReservist(stu);
            }
        }
        Main.getInstance().getDisplay().displayMessage("Joueur 2");
        Main.getInstance().getDisplay().displayMessage(player2.getStudentsText());
        while (player2.getReservist().size() < 5) {
            Student stu = Main.getInstance().getAsk().askStudent("Choisissez un etudiant qui sera reserviste (envoyez l'index)", player2);
            if (!player2.getReservist().contains(stu)) {
                player2.chooseAsReservist(stu);
            }
        }

    }

    public void repartition() {
        setState(StateGame.Setting);
        Main.getInstance().getDisplay().displayMessage("Joueur 1");
        ArrayList<Student> fighters1 = (ArrayList<Student>) player1.getStudents().clone();
        System.out.println(fighters1.size());
        fighters1.remove(player1.getReservist());
        boolean finish = false;
        while (!finish) {
            for (Student student : fighters1) {
                student.setArea(Main.getInstance().getAsk().askArea("Entrez la zone de deploiement pour l'etudiant : " + student));
            }
            finish = true;
            for (Area area : Area.values()) {
                if (area.getStudents().size() == 0) {
                    finish = false;
                    break;
                }
            }
        }
        Main.getInstance().getDisplay().displayMessage("Joueur 2");
        ArrayList<Student> fighters2 = (ArrayList<Student>) player2.getStudents().clone();
        fighters1.remove(player2.getReservist());
        finish = false;
        while (!finish) {
            for (Student student : fighters2) {
                student.setArea(Main.getInstance().getAsk().askArea("Entrez la zone de deploiement pour l'etudiant : " + student));
            }
            finish = true;
            for (Area area : Area.values()) {
                if (!(area.getStudents().stream().filter(s -> s.getPlayer().equals(player2)).toList().size() > 0)) {
                    finish = false;
                    break;
                }
            }
        }
        fight();

    }

    public void initStudent(Player p) {
        p.addStudent(new GobiMaster(p));
        for (int i = 0; i < 4; i++) {
            p.addStudent(new EliteStudent(p));
        }
        for (int i = 0; i < 15; i++) {
            p.addStudent(new Student(p));
        }
    }

    public void fight() {
        setState(StateGame.Fight);
        ArrayList<Student> students = new ArrayList<>();
        for (Area area : Area.values()) {
            students.addAll(area.getStudents());
        }
        students = (ArrayList<Student>) students.stream().sorted(Comparator.comparingInt(Student::getInitiative)).toList();
        int index = 0;
        Student student = students.get(0);

        while (true) {
            student.action();
            Player player = student.getPlayer();
            if (student.getArea().getStudents().stream().filter(s -> !s.getPlayer().equals(player)).toList().size() == 0) {
                student.getArea().setController(player);
                Main.getInstance().getDisplay().displayMessage("La zone " + student.getArea() + "a ete conquise par le joueur " + student.getPlayer().getProgram());
                break;
            }
            index = (index++) % students.size();
            student = students.get(index);
        }
        if (!checkWin()) {
            changePosition();
        }

    }

    private boolean checkWin() {
        int numAreaPlayer1 = 0;
        int numAreaPlayer2 = 0;
        for (Area area : Area.values()) {
            if (area.getController().equals(player1)) numAreaPlayer1++;
            if (area.getController().equals(player2)) numAreaPlayer2++;
        }
        if (numAreaPlayer1 >= 3) {
            Main.getInstance().getDisplay().displayMessage("Victoire du Joueur 1");
            return true;
        }
        if (numAreaPlayer2 >= 3) {
            Main.getInstance().getDisplay().displayMessage("Victoire du Joueur 2");
            return true;
        }
        return false;
    }

    public void changePosition() {
        setState(StateGame.Truce);
        Main.getInstance().getDisplay().displayMessage("Joueur 1");
        while (player1.getReservist().size() > Math.max(player1.getStudents().size() - 15, 0)) {
            Student stu = Main.getInstance().getAsk().askStudent("Choisissez un etudiant qui ne sera plus reserviste", player1);
            if (stu.getArea() == null) {
                boolean goodArea = false;
                while (!goodArea) {
                    Area area = Main.getInstance().getAsk().askArea("Choisissez la zone dans laquelle il va etre deploye");
                    if (area.getController() == null) {
                        stu.setArea(area);
                        goodArea = true;
                    }
                }
            }
        }
        boolean finish = false;
        while (!finish) {
            Student stu = Main.getInstance().getAsk().askStudentOrNot("Voulez vous changer un etudiant de zone ? (N pour non, sinon l'index)", player1);
            if (stu.equals(null)) {
                break;
            }
            if (stu.getArea().getStudents().stream().filter(s -> s.getPlayer().equals(player1)).toList().size() <= 1) {
                Main.getInstance().getDisplay().displayMessage("Vous ne pouvez pas changer de zone cet etudiant pour le moment");
            } else {
                if (stu.getArea() == null) {
                    boolean goodArea = false;
                    while (!goodArea) {
                        Area area = Main.getInstance().getAsk().askArea("Choisissez la zone dans laquelle il va etre deploye");
                        if (area.getController() != player2) {
                            stu.setArea(area);
                            goodArea = true;
                        }
                    }
                }
            }
        }


        Main.getInstance().getDisplay().displayMessage("Joueur 2");
        while (player2.getReservist().size() > Math.max(player2.getStudents().size() - 15, 0)) {
            Student stu = Main.getInstance().getAsk().askStudent("Choisissez un etudiant qui ne sera plus reserviste", player2);
            if (stu.getArea() == null) {
                boolean goodArea = false;
                while (!goodArea) {
                    Area area = Main.getInstance().getAsk().askArea("Choisissez la zone dans laquelle il va etre deploye");
                    if (area.getController() == null) {
                        stu.setArea(area);
                        goodArea = true;
                    }
                }
            }
        }
        finish = false;
        while (!finish) {
            Student stu = Main.getInstance().getAsk().askStudentOrNot("Voulez vous changer un etudiant de zone ? (N pour non, sinon l'index)", player2);
            if (stu.equals(null)) {
                break;
            }
            if (stu.getArea().getStudents().stream().filter(s -> s.getPlayer().equals(player2)).toList().size() <= 1) {
                Main.getInstance().getDisplay().displayMessage("Vous ne pouvez pas changer de zone cet etudiant pour le moment");
            } else {
                if (stu.getArea() == null) {
                    boolean goodArea = false;
                    while (!goodArea) {
                        Area area = Main.getInstance().getAsk().askArea("Choisissez la zone dans laquelle il va etre deploye");
                        if (area.getController() != player1) {
                            stu.setArea(area);
                            goodArea = true;
                        }
                    }
                }
            }
        }
        fight();
    }


    public StateGame getState() {
        return state;
    }

    public void setState(StateGame state) {
        this.state = state;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}

