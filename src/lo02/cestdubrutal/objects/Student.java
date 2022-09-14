package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.enums.Strategy;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Student {

    private Player player;
    private int creditECTS;
    private int strength = 0;
    private int dexterity;
    private int resistance;
    private int constitution;
    private int initiative;
    private Strategy strategy;

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public int getCreditECTS() { return creditECTS; }
    public void setCreditECTS(int creditECTS){
        creditECTS = max(0,creditECTS);
        this.creditECTS = min(creditECTS,30+constitution);
        // j'avoue je sais pas comment faire pour mettre les deux
    }

    public int getStrength() { return strength; }
    public void setStrength(int strength){
        strength = max(0,strength);
        this.strength = min(strength,10);
    }

    public int getDexterity () { return dexterity; }
    public void setDexterity(int dexterity){
        dexterity = max(0,dexterity);
        this.dexterity = min(dexterity,10);
    }

    public int getResistance() { return resistance; }
    public void setResistance(int resistance){
        resistance = max(0,resistance);
        this.resistance = min(resistance,10);
    }

    public int getConstitution() { return constitution; }
    public void setConstitution(int constitution) {
        constitution = max(0,constitution);
        this.constitution = min(constitution,10);
    }

    public int getInitiative() { return initiative; }
    public void setInitiative(int initiative) {
        initiative = max(0,initiative);
        this.initiative = min(initiative,10);
    }

    public Strategy getStrategy() { return strategy; }
    public void setStrategy(Strategy strategy) { this.strategy = strategy; }

    public Student() {

    }


}
