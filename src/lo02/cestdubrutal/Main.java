package lo02.cestdubrutal;

import lo02.cestdubrutal.objects.Game;

public class Main {

    private static Main instance;
    private Game game;
    private Display display;
    private Ask ask;

    public static void main(String[] args) {
        getInstance().setDisplay(new TextDisplay());
        getInstance().setAsk(new TextAsk());
        getInstance().setGame(new Game());
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

    public void setAsk(Ask ask) {
        this.ask = ask;
    }

    public Ask getAsk() {
        return ask;
    }
}
