import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    Physics physics;
    MainLoop mainLoop;

    Thread loopThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        currentView = new HBox();
        beginConfiguration();
        createViews();
        currentView.getChildren().add(mainMenuScreen);
    }

    private void beginConfiguration(){

        root = new StackPane();
        root.getChildren().add(currentView);
        scene = new Scene(root, 1000, 770);
        primaryStage.setTitle("Łucznik: the game");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.R){
                mainLoop.stopLoop();
                gameScreen = new GameScreen(this);
                currentView.getChildren().set(0,gameScreen);
                System.out.println("blah");
            }
        });

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



    public void disableAutoamtedTicks(){
        mainLoop.disableAutoamtedTicks();
    }
    public void enableAutoamtedTicks(){
        mainLoop.enableAutoamtedTicks();
    }
    public void startLoop(){
        physics = new Physics(
                (double) gameScreen.getAngle().getValue(),
                (double) gameScreen.getPowerSlider().getValue(),
                (double) gameScreen.getGravitationSlider().getValue(),
                (double) gameScreen.getWindSliderX().getValue());
        gameScreen.setPhysics(physics);
        mainLoop = new MainLoop(physics);
        loopThread = new Thread(mainLoop);
        loopThread.start();
    }
    public void stopLoop(){
        if(mainLoop!=null)
            mainLoop.stopLoop();
    }
    public void singleTick(){
        physics.tick();
    }
    public void changeAutomatedLoop(){
        if(mainLoop.isEnableAutomatedTicks())
            disableAutoamtedTicks();
        else
            enableAutoamtedTicks();
    }
}
