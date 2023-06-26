import controller.PartieManager;
import view.JardinStringGenerator;

public class GardenMowApplication {
    public static void main(String[] args) {
        // run the application

        PartieManager gameManager = new PartieManager();
        gameManager.startGame();
    }
}
