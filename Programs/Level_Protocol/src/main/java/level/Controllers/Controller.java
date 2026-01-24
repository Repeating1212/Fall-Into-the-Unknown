package level.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import level.Managers.MainManager;
import level.Managers.PlayerHandler;
import level.View.* ;
import level.Objects.Base_Class.GameObject;

import java.util.List;

public class Controller {


    public javafx.scene.text.Text coinDisplay;
    // Nodes
    @FXML private ImageView heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8;
    @FXML private ImageView skill1, skill2, skill3, skill4;
    @FXML private StackPane skillBgd1, skillBgd2, skillBgd3, skillBgd4;
    @FXML private ProgressBar bossHealthBar;
    @FXML private ImageView map;
    @FXML private Rectangle skillCooldown1, skillCooldown2, skillCooldown3, skillCooldown4;

    private SceneView sceneView;
    private GameTicks gameUpdater;
    private PlayerHandler playerHandler;
    private MainManager mainManager;

    @FXML private Pane rootPane;


    @FXML
    public void initialize() {

        ImageView[] hearts = new ImageView[]{heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8};
        ImageView[] skills = new ImageView[]{skill1, skill2, skill3, skill4};
        Rectangle[] skillCooldowns = new Rectangle[] {skillCooldown1, skillCooldown2, skillCooldown3, skillCooldown4};
        StackPane[] skillBackgrounds = new StackPane[]{skillBgd1, skillBgd2, skillBgd3, skillBgd4};

        sceneView = new SceneView(rootPane, coinDisplay, hearts, skills, skillBackgrounds, bossHealthBar, map, skillCooldowns);

        playerHandler =  new PlayerHandler(sceneView.getSkillBoxView());
        mainManager = new MainManager(sceneView, playerHandler);
        gameUpdater = new GameTicks(mainManager, playerHandler);

        gameUpdater.start();
        rootPane.requestFocus();

        // Setup Input
        setupKeyboardInputOnPane();
        rootPane.setOnScroll(this::handleMouseWheel);
        setupMouseInput();
    }

    private void setupMouseInput() {
        // Left click to attack
        rootPane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                handleSkillAvtivation(event);
            }
        });
    }


    private void handleSkillAvtivation(MouseEvent event) {
        // Convert mouse coordinates to scene coordinates
        double mouseX = event.getX();
        double mouseY = event.getY();
        playerHandler.skillActivate(mouseX, mouseY);
    }



    private void setupKeyboardInputOnPane() {
        rootPane.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            switch (key) {
                case W -> playerHandler.moveUp(true);
                case A -> playerHandler.moveLeft(true);
                case S -> playerHandler.moveDown(true);
                case D -> playerHandler.moveRight(true);
            }
        });

        rootPane.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();

            switch (key) {
                case W -> playerHandler.moveUp(false);
                case A -> playerHandler.moveLeft(false);
                case S -> playerHandler.moveDown(false);
                case D -> playerHandler.moveRight(false);
            }
        });
    }

    private void handleMouseWheel(ScrollEvent event) {
        double deltaY = event.getDeltaY();

        if (deltaY > 0) {
            // Scroll UP - previous skill
            playerHandler.decreaseCurrentSkill();
        } else if (deltaY < 0) {
            // Scroll DOWN - next skill
            playerHandler.increaseCurrentSkill();
        }

        event.consume(); // Prevent default behavior
    }

    public void hitBoxDebug(List<GameObject> gameObjects){
        for (GameObject object : gameObjects) {
            rootPane.getChildren().add(object.getProperty().getDebugHitBox());
            object.getProperty().showDebugHitBox();
        }
    }

    @FXML
    private void handleResume() {
        gameUpdater.handlePause();
    }
}
