package lo02.cestdubrutal.objects;

public class GobiMaster extends Student {

    public GobiMaster(Player player) {
        super(player);
        setConstitution(10);
        setDexterity(2);
        setPlayer(player);
        setResistance(2);
        setInitiative(2);
        setStrength(2);
    }

}
