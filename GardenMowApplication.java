import manager.PartieManager;

public class GardenMowApplication {
    public static void main(String[] args) {
        // set up a game manager and start the game
        PartieManager gameManager = new PartieManager();
        gameManager.startGame();
    }
}
