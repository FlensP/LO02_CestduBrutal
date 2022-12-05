package lo02.cestdubrutal;

import lo02.cestdubrutal.objects.Game;

public class Main {

    private static Main instance;
    private Game game;
    private Display display;

    public static void main(String[] args) {
        getInstance().setGame(new Game());
        getInstance().setDisplay(new TextDisplay());
    }

    private Main() {

    }

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Display getDisplay() {
        return display;
    }
}
