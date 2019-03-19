import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenuScreen extends HBox {
    Image nowaGraImage;
    Image nowaGraNImage;
    Image wyjscieImage;
    Image wyjscieNImage;
    Image background;
    ImageView nowaGraImageImageView;
    ImageView wyjscieImageImageImageView;
    ImageView backgroundImageView;
    Main main;

    StackPane stackPane;
    Pane pane;

    public MainMenuScreen(Main main){
          try {
              nowaGraImage = new Image(new FileInputStream("nowagra.png"));
              nowaGraNImage = new Image(new FileInputStream("nowagran.png"));
              wyjscieImage = new Image(new FileInputStream("wyjscie.png"));
              wyjscieNImage = new Image(new FileInputStream("wyjscien.png"));
              background = new Image(new FileInputStream("tlo.png"));
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
            beginConfiguration();
          addEventListeners();
          this.main = main;
      }
      private void beginConfiguration(){
        stackPane= new StackPane();
        pane = new Pane();
          nowaGraImageImageView = new ImageView(nowaGraImage);
          wyjscieImageImageImageView = new ImageView(wyjscieImage);
          backgroundImageView = new ImageView(background);
          backgroundImageView.setX(0);
          backgroundImageView.setY(0);
          nowaGraImageImageView.setX(200);
          nowaGraImageImageView.setY(150);
          wyjscieImageImageImageView.setX(200);
          wyjscieImageImageImageView.setY(400);

          stackPane.getChildren().add(backgroundImageView);
          pane.getChildren().add(nowaGraImageImageView);
          pane.getChildren().add(wyjscieImageImageImageView);
          stackPane.getChildren().add(pane);
          this.getChildren().add(stackPane);
      }
    private void addEventListeners(){
          nowaGraImageImageView.setOnMouseEntered(event -> {
              nowaGraImageImageView.setImage(nowaGraNImage);
          });
          nowaGraImageImageView.setOnMouseExited(event -> {
              nowaGraImageImageView.setImage(nowaGraImage);
          });
        wyjscieImageImageImageView.setOnMouseEntered(event -> {
            wyjscieImageImageImageView.setImage(wyjscieNImage);
        });
        wyjscieImageImageImageView.setOnMouseExited(event -> {
            wyjscieImageImageImageView.setImage(wyjscieImage);
        });
        wyjscieImageImageImageView.setOnMouseClicked(event -> {
            main.exit();
        });
        nowaGraImageImageView.setOnMouseClicked(event -> {
            main.changeScreenToGameScreen();
        });

      }
}
