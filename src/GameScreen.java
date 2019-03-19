import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreen extends HBox {

    Image background;
    ImageView backgroundImageView;
    Main main;

    StackPane stackPane;
    Pane pane;

    public GameScreen(Main main){
        try {
            background = new Image(new FileInputStream("gra.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        beginConfiguration();
        this.main = main;
    }
    private void beginConfiguration(){
        backgroundImageView = new ImageView(background);
        backgroundImageView.setX(0);
        backgroundImageView.setY(0);
        stackPane= new StackPane();
        pane = new Pane();
        stackPane.getChildren().add(backgroundImageView);
        stackPane.getChildren().add(pane);
        this.getChildren().add(stackPane);
    }
}
