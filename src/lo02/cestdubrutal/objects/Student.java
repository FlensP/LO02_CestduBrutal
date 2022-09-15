package lo02.cestdubrutal.objects;

import lo02.cestdubrutal.enums.Area;
import lo02.cestdubrutal.enums.Strategy;

import java.util.Comparator;

public class Student {

    private Player player;
    private int creditECTS;
    private int strength = 0;
    private int dexterity = 0;
    private int resistance = 0;
    private int constitution = 0;
    private int initiative = 0;
    private Strategy strategy;
    private Area area;

    public Student(Player player) {
        setPlayer(player);
    }

    public void attack() {

        // élève ennemi à attaquer (avec le moins de point de vie)
        Student student = getArea().getStudents().stream()
                .filter(s -> !s.getPlayer().equals(getPlayer()))
                .sorted(Comparator.comparingInt(Student::getCreditECTS))
                .toList().get(0);

        // nombre de points à retirer
        int x = (int) (100 * Math.random());
        double y = 0.6 * Math.random(); // todo: changer la méthode du random pour exclure 0
        int coef = Math.max(0, Math.min(100, 10 * getStrength() - 5 * student.getResistance())); // coef degats
        int damage = (int) (Math.floor(y * (1 + coef) * 10));

        // condition réussite attaque
        if (x <= 40 + 3 * getDexterity()) student.setCreditECTS(Math.max(0, student.getCreditECTS() - damage));

        if (student.getCreditECTS() == 0) student.kill();
    }

    public void heal() {

        // élève à soigner (avec le moins de points de vie)
        Student student = getArea().getStudents().stream()
                .filter(s -> s.getPlayer().equals(getPlayer()))
                .sorted(Comparator.comparingInt(Student::getCreditECTS))
                .toList().get(0);

        // nombre de points à ajouter
        int x = (int) (100 * Math.random());
        double y = Math.random();
        int care = (int) (Math.floor(y * (10 + getConstitution())));

        // condition réussite du soin
        if (x <= 20 + 6 * getDexterity()) student.setCreditECTS(Math.min(student.getCreditECTS() + care, 30 + student.getConstitution()));

    }

    public void kill(){
        getPlayer().removeStudent(this);
        getArea().removeStudent(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCreditECTS() {
        return creditECTS;
    }

    public void setCreditECTS(int creditECTS) {
        creditECTS = Math.max(0, creditECTS);
        this.creditECTS = Math.min(creditECTS, 30 + constitution);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        strength = Math.max(0, strength);
        this.strength = Math.min(strength, 10);
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        dexterity = Math.max(0, dexterity);
        this.dexterity = Math.min(dexterity, 10);
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        resistance = Math.max(0, resistance);
        this.resistance = Math.min(resistance, 10);
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        constitution = Math.max(0, constitution);
        this.constitution = Math.min(constitution, 10);
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        initiative = Math.max(0, initiative);
        this.initiative = Math.min(initiative, 10);
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
