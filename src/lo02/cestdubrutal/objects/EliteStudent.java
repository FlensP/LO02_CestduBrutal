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

}
