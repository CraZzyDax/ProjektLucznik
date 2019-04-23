import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreen extends HBox {

    private Image background;
    private ImageView backgroundImageView;

    private Image arrowImage;
    private ImageView arrowImageView;
    private Main main;
    private HBox configorationHBox;
    private VBox paski;
    private HBox pasekPower;
    private HBox pasekX;
    private HBox pasekY;
    private HBox pasekZ;
    private HBox pasekAngle;
    private Pane gamePane;
    private VBox positionLabelsVBox;
    private VBox buttonsVBox;

    private Button executeButton;
    private Button startStopButton;
    private Button tickButton;


    private Slider powerSlider;
    private Slider windSliderX;
    private Slider windSliderY;
    private Slider windSliderZ;
    private Slider angle;
    private Label powerLabel;
    private Label windLabelX;
    private Label windLabelY;
    private Label windLabelZ;
    private Label powerLabelValue;
    private Label windLabelValueX;
    private Label windLabelValueY;
    private Label windLabelValueZ;
    private Label angleLabelValue;


    private Label arrowPositionX;
    private Label arrowPositionY;
    private Label arrowPositionZ;

    private VBox mainVBox;

    private Physics physics;

    private Rotate rotate;


    private Rectangle arrowRectangle;

    public GameScreen(Main main,Physics physics){
        try {
            background = new Image(new FileInputStream("gra.png"));
            arrowImage = new Image(new FileInputStream("strzala.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.physics = physics;
        beginConfiguration();
        this.main = main;
        addEventsListeners();
        drawArrowBeforeShot();
    }
    private void beginConfiguration(){

        arrowRectangle = new Rectangle(75,15, Color.RED);


        executeButton = new Button("Wykonaj");
        startStopButton = new Button("Wlacz/wylacz autoamtyczna petle");
        tickButton = new Button("Wykonaj jeden cykl");
        paski = new VBox();
        pasekPower = new HBox();
        pasekX = new HBox();
        pasekY = new HBox();
        pasekZ = new HBox();
        pasekAngle = new HBox();
        gamePane = new Pane();
        buttonsVBox = new VBox();

        powerLabel = new Label("Sila: ");
        windLabelX = new Label("Wiatr X: ");
        windLabelY = new Label("Wiatr Y: ");
        windLabelZ = new Label("Wiatr Z: ");
        angleLabelValue = new Label("0");
        powerLabelValue = new Label("50");
        windLabelValueX = new Label("50");
        windLabelValueY = new Label("50");
        windLabelValueZ = new Label("50");
        arrowPositionX = new Label(Integer.toString(physics.getArrow().getPosX()));
        arrowPositionY = new Label(Integer.toString(physics.getArrow().getPosY()));
        arrowPositionZ = new Label(Integer.toString(physics.getArrow().getPosZ()));

        positionLabelsVBox = new VBox();
        positionLabelsVBox.getChildren().add(arrowPositionX);
        positionLabelsVBox.getChildren().add(arrowPositionY);
        positionLabelsVBox.getChildren().add(arrowPositionZ);

        configorationHBox = new HBox();
        powerSlider = new Slider(0, 100, 50);
        windSliderX = new Slider(-50, 50, 0);
        windSliderY = new Slider(-50, 50, 0);
        windSliderZ = new Slider(-50, 50, 0);
        angle = new Slider(0, 90, 0);
        pasekPower.getChildren().addAll(powerLabel,powerSlider,powerLabelValue);
        pasekX.getChildren().addAll(windLabelX,windSliderX,windLabelValueX);
        pasekY.getChildren().addAll(windLabelY,windSliderY,windLabelValueY);
        pasekZ.getChildren().addAll(windLabelZ,windSliderZ,windLabelValueZ);

        pasekAngle.getChildren().addAll(angle,angleLabelValue);

        paski.getChildren().addAll(pasekPower,pasekX,pasekY,pasekZ,pasekAngle);

        buttonsVBox.getChildren().addAll(executeButton,startStopButton,tickButton);

        configorationHBox.getChildren().addAll(paski,buttonsVBox,positionLabelsVBox);


        rotate = new Rotate();
        rotate.setPivotX(20);
        rotate.setPivotY(20);

        arrowRectangle.getTransforms().add(rotate);
        backgroundImageView = new ImageView(background);





        arrowImageView = new ImageView(arrowImage);
        backgroundImageView.setX(0);
        arrowRectangle.setFill(new ImagePattern(arrowImage));
        arrowRectangle.setX(physics.getArrow().getPosX()-75);
        arrowRectangle.setY(physics.getArrow().getPosY()-7);

        mainVBox= new VBox();

        gamePane.getChildren().add(backgroundImageView);
        gamePane.getChildren().add(arrowRectangle);

        mainVBox.getChildren().add(gamePane);
        mainVBox.getChildren().add(configorationHBox);

        this.getChildren().add(mainVBox);


    }

    private void addEventsListeners(){
        powerSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                powerLabelValue.setText(Integer.toString(newValue.intValue()));
                physics.setPower(newValue.intValue());
            }
        });
        windSliderX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueX.setText(Integer.toString(newValue.intValue()));
                physics.setWindX(newValue.intValue());
            }
        });
        windSliderY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueY.setText(Integer.toString(newValue.intValue()));
                physics.setWindY(newValue.intValue());
            }
        });
        windSliderZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueZ.setText(Integer.toString(newValue.intValue()));
                physics.setWindZ(newValue.intValue());
            }
        });

        angle.valueProperty().addListener((observable, oldValue, newValue) -> {
            //arrowImageView.setRotate(newValue.intValue()-90);
            angleLabelValue.setText(Integer.toString(newValue.intValue()));
            physics.getArrow().setAngleX(newValue.intValue());
            drawArrowBeforeShot();
        });

        executeButton.setOnAction(event -> {
            main.startLoop();
            main.enableAutoamtedTicks();
        });

    }

    private void drawArrowBeforeShot(){


        //arrowRectangle.setX(physics.getArrow().getPosX());
        //arrowRectangle.setY(physics.getArrow().getPosY());\
        arrowRectangle.getTransforms().add(rotate);
        arrowRectangle.setRotate(physics.getArrow().getAngleX()-90);
}

    public void drawArrowAfterShot(){

    }

}
