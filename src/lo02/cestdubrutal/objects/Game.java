package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.StateGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Game {

    private StateGame state;
    private Player player1;
    private Player player2;


    public void init(){
        
    }


    public void pointAllocation(Player player) {
        player.setPointsLeft(400);
    }

    public void fight(Area area) {
        ArrayList<Student> fighters = (ArrayList<Student>) area.getStudents()
                .stream().sorted(Comparator.comparingInt(Student::getInitiative)).toList();

        Player player = fighters.get(0).getPlayer(); //joueur du premier perso

        boolean isControlled = false;

        while (!isControlled) {
            ArrayList<Student> playerTroop1 = (ArrayList<Student>) area.getStudents()
                    .stream().filter(s -> s.getPlayer().equals(player)).toList();
            ArrayList<Student> playerTroop2 = (ArrayList<Student>) area.getStudents()
                    .stream().filter(s -> !s.getPlayer().equals(player)).toList();

            if (fighters == playerTroop1){
                isControlled = true;
                area.setController(playerTroop1.get(0).getPlayer());
            }

            if (fighters == playerTroop2){
                isControlled = true;
                area.setController(playerTroop2.get(0).getPlayer());
            }

            boolean isDefensive = new Random().nextBoolean();

            switch (fighters.get(0).getStrategy()) {
                case Defensive -> fighters.get(0).heal();
                case Offensive -> fighters.get(0).attack();
                case Random -> {
                    if (isDefensive) {
                        fighters.get(0).heal();
                    } else fighters.get(0).attack();
                }
            }
        }
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

