package Level.BaseLevel.Controllers;


import Game_File.StaticData.GameData;
import Game_File.StaticData.LevelType;
import Data.Interface.SmokeScene;
import Data.Loader.SceneLoader;
import Level.BaseLevel.Manager.GameTicks;
import Level.BaseLevel.Manager.InputHandler;
import Data.Interface.SceneInterface;
import Data.Supplier.SkillSupplier;
import Level.BaseLevel.Manager.LevelManager;
import Level.BaseLevel.Objects.Player;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import Level.BaseLevel.View.* ;

public class LevelController extends SceneInterface implements SmokeScene {

    // Nodes
    @FXML private ImageView heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8;
    @FXML private ImageView skill1, skill2, skill3, skill4;
    @FXML private StackPane skillBgd1, skillBgd2, skillBgd3, skillBgd4;
    @FXML private ProgressBar bossHealthBar;
    @FXML private ImageView map;
    @FXML private Rectangle skillCooldown1, skillCooldown2, skillCooldown3, skillCooldown4;
    @FXML private AnchorPane layer1, layer2;

    private SceneView sceneView;
    private GameTicks gameTicks;
    private InputHandler inputHandler;
    private LevelManager levelManager;

    @FXML private Pane rootPane;


    @FXML
    public void initialize() {
        rootPane.requestFocus();

        // Setup Input
        setupKeyboardInputOnPane();
        rootPane.setOnScroll(this::handleMouseWheel);
        setupMouseInput();
    }

    @Override
    public void initializeData(){
        // Run after receive fileManger
        createClass();
        gameTicks.start();
    }

    @Override
    public void loadSmoke(){
        SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.SMOKE2, fileManager);
    }

    // Private method

    private void createClass(){
        ImageView[] hearts = new ImageView[]{heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8};
        ImageView[] skills = new ImageView[]{skill1, skill2, skill3, skill4};
        Rectangle[] skillCooldowns = new Rectangle[] {skillCooldown1, skillCooldown2, skillCooldown3, skillCooldown4};
        StackPane[] skillBackgrounds = new StackPane[]{skillBgd1, skillBgd2, skillBgd3, skillBgd4};
        AnchorPane[] layers = new AnchorPane[]{layer1, layer2};

        // Display Related
        sceneView = new SceneView(rootPane, layers, hearts, bossHealthBar, map,
                skills, skillBackgrounds, skillCooldowns,
                fileManager);

        // Game update related
        Player player = new Player(SkillSupplier.getPlayerSkills(fileManager));
        this.inputHandler = new InputHandler(sceneView, player);
        this.levelManager = new LevelManager(sceneView, player, GameData.GAME_LEVEL);

        gameTicks = new GameTicks(sceneView, player, inputHandler, levelManager);
    }

    private void setupMouseInput() {
        // Left click to attack
        rootPane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                handleSkillActivation(event);
            }
        });
    }

   private void handleSkillActivation(MouseEvent event) {
        // Convert mouse coordinates to scene coordinates
        double mouseX = event.getX();
        double mouseY = event.getY();
        inputHandler.skillActivate(mouseX, mouseY);
    }

    private void setupKeyboardInputOnPane() {
        rootPane.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            switch (key) {
                case W -> inputHandler.moveUp(true);
                case A -> inputHandler.moveLeft(true);
                case S -> inputHandler.moveDown(true);
                case D -> inputHandler.moveRight(true);
                case B -> levelManager.toggleDebugBox();
            }
        });

        rootPane.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();

            switch (key) {
                case W -> inputHandler.moveUp(false);
                case A -> inputHandler.moveLeft(false);
                case S -> inputHandler.moveDown(false);
                case D -> inputHandler.moveRight(false);
            }
        });
//        rootPane.setOnlyKeyPressed(event -> {
//            KeyCode key = event.getCode();
//
//            if (Objects.requireNonNull(key) == KeyCode.B) {
//                levelManager.toggleDebugBox();
//            }
//        });
    }

    private void handleMouseWheel(ScrollEvent event) {
        double deltaY = event.getDeltaY();

        if (deltaY > 0) {
            // Scroll UP - previous skill
            inputHandler.decreaseCurrentSkill();
        } else if (deltaY < 0) {
            // Scroll DOWN - next skill
            inputHandler.increaseCurrentSkill();
        }

        event.consume(); // Prevent default behavior
    }

    @FXML
    private void handleResume() {
        gameTicks.handlePause();
        sceneView.showPauseScreen(gameTicks);
    }
}
