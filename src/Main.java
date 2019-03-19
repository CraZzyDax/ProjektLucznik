import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    StackPane root;
    Scene scene;
    HBox currentView;
    Stage primaryStage;

    MainMenuScreen mainMenuScreen;
    GameScreen gameScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        createViews();
        currentView = new HBox();
        currentView.getChildren().add(mainMenuScreen);
        beginConfiguration();
    }

    private void beginConfiguration(){

        root = new StackPane();
        root.getChildren().add(currentView);
        scene = new Scene(root, 1000, 750);
        primaryStage.setTitle("Łucznik: the game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void createViews() {
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
    }

    public static void main(String[] args){
        launch();
    }
    public void exit(){
        primaryStage.close();
    }
    public void changeScreenToGameScreen(){
        currentView.getChildren().set(0,gameScreen);
    }
}
