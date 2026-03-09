package Level.Lvl_Sample.Launcher;

public class Launcher {
    public static void main(String[] args) {
        // Suppress warnings
        System.setProperty("javafx.verbose", "false");
        System.setProperty("prism.verbose", "false");
        MainApp.main(args);
    }
}