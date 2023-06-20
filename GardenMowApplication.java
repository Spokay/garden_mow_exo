import controller.PartieController;
import view.JardinStringGenerator;

public class GardenMowApplication {
    public static void main(String[] args) {
        // run the application

        PartieController gameController = new PartieController();
        gameController.startGame();
        System.out.println(JardinStringGenerator.generateJardinString(gameController.currentGame.getTurnsGardens().get(0)));
    }
}
