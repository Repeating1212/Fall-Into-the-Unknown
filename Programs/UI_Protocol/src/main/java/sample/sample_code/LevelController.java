package sample.sample_code;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelController {
    @FXML private ImageView heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8;
    @FXML private ImageView skill1, skill2, skill3, skill4;
    @FXML private ImageView map;
    @FXML private ProgressBar bossHealthBar;

    private Image heartFull;
    private Image heartEmpty;
    private Image skillReady;
    private Image skillCooldown;
    private int currentHealth = 8;
    private ImageView[] hearts;
    private ImageView[] skills;
    private boolean[] skillCooldowns;
    private Image mapImg;

    // Skill Background
    private int currentSkill =1;
    @FXML private StackPane skillBgd1, skillBgd2, skillBgd3, skillBgd4;
    @FXML private Pane rootPane;
    private StackPane[] skillBackgrounds;

    @FXML
    public void initialize() {
        // Load images
        heartFull = new Image(getClass().getResourceAsStream("/picture/healthFull.png"));
        heartEmpty = new Image(getClass().getResourceAsStream("/picture/healthEmpty.png"));
        skillReady = new Image(getClass().getResourceAsStream("/picture/skillReady.png"));
        skillCooldown = new Image(getClass().getResourceAsStream("/picture/skillCooldown.png"));
        mapImg = new Image(getClass().getResourceAsStream("/picture/map.png"));

        // Store hearts in array for easy access
        hearts = new ImageView[]{heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8};
        skills = new ImageView[]{skill1, skill2, skill3, skill4};
        skillCooldowns = new boolean[]{true,true, true, true};

        map.setImage(mapImg);
        updateHealthDisplay(8);
        updateSkillCooldown();
        updateHealthBar(1);

        // Store backgrounds in array
        skillBackgrounds = new StackPane[]{skillBgd1, skillBgd2, skillBgd3, skillBgd4};

        // Set initial selection (skill 1 - opacity 1.0, others 0)
        updateSkillSelection();

        // Listen for mouse wheel
        rootPane.setOnScroll(this::handleMouseWheel);

    }

    private void handleMouseWheel(ScrollEvent event) {
        double deltaY = event.getDeltaY();

        if (deltaY > 0) {
            // Scroll UP - previous skill
            if (currentSkill > 0) {
                currentSkill--;
                updateSkillSelection();
            }
        } else if (deltaY < 0) {
            // Scroll DOWN - next skill
            if (currentSkill < 3) {
                currentSkill++;
                updateSkillSelection();
            }
        }

        event.consume(); // Prevent default behavior
    }

    // Method to update health display
    public void updateHealthDisplay(int health) {
        currentHealth = Math.min(8, Math.max(0, health)); // Clamp between 0-8

        for (int i = 0; i < 8; i++) {
            if (i < currentHealth) {
                hearts[i].setImage(heartFull);  // Lighted icon
            } else {
                hearts[i].setImage(heartEmpty); // Dark icon
            }
        }
    }

    public void updateSkillCooldown() {
        for (int i = 0; i < 4; i++) {
            if (skillCooldowns[i]) {
                skills[i].setImage(skillReady);  // Lighted icon
            } else {
                skills[i].setImage(skillCooldown); // Dark icon
            }
        }
    }

    private void updateHealthBar(double progress) {
        bossHealthBar.setProgress(progress);

        // Change color based on health
        if (progress > 0.6) {
            bossHealthBar.setStyle("-fx-accent: green;");
        } else if (progress > 0.3) {
            bossHealthBar.setStyle("-fx-accent: orange;");
        } else {
            bossHealthBar.setStyle("-fx-accent: red;");
        }
    }

    private void updateSkillSelection() {
        for (int i = 0; i < skillBackgrounds.length; i++) {
            if (i == currentSkill) {
                skillBackgrounds[i].setOpacity(1.0);
                skillBackgrounds[i].setStyle("-fx-background-color: black;");
            } else {
                skillBackgrounds[i].setOpacity(0.5);
                skillBackgrounds[i].setStyle("-fx-background-color: transparent;");
            }
        }
    }

    @FXML
    private void switchToMenuScene(ActionEvent event) {
        try {
            // Get the button that was clicked
            Button clickedButton = (Button) event.getSource();

            // Load the second scene
            Parent secondScene = FXMLLoader.load(
                    getClass().getResource("MainMenu.fxml")
            );

            // Get stage from ANY node in the current scene
            Stage stage = (Stage) clickedButton.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(secondScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
