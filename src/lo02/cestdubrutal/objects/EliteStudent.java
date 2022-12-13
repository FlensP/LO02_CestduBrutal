package lo02.cestdubrutal.objects;

public class EliteStudent extends Student {

    public EliteStudent(Player player) {
        super(player);
        setConstitution(5);
        setDexterity(1);
        setPlayer(player);
        setResistance(1);
        setInitiative(1);
        setStrength(1);
    }

    @Override
    public String toString() {

        return "Etudiant d'elite du " +
                "joueur=" + getPlayer().getProgram() +
                ", stats=" + getCreditECTS() +
                "/" + getStrength() +
                "/" + getDexterity() +
                "/" +getResistance()+
                "/" + getConstitution() +
                "/" + getInitiative() +
                ", strategy=" + getStrategy() +
                " sur la zone :" + getArea() +
                '}';
    }

}
