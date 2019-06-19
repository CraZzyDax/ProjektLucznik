import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
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
import java.lang.Math;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreen extends HBox {

    private boolean isStarted;

    private Image background;
    private ImageView backgroundImageView;
    private Image pasek;
    private ImageView pasekImageView;

    private Image arrowImage;
    private ImageView arrowImageView;
    private Main main;
    private HBox configorationHBox;
    private VBox paski;
    private HBox pasekPower;
    private HBox pasekX;
    private HBox pasekY;
    private HBox pasekAngle;
    private Pane gamePane;
    private VBox positionLabelsVBox;
    private VBox buttonsVBox;


    private Rectangle executeRectangle;
    private Rectangle startStopRectangle;
    private Rectangle tickRectangle;
    private Rectangle exitRectangle;


    private Slider powerSlider;
    private Slider windSliderX;
    private Slider gravitationSlider;
    private Slider angle;
    private Label powerLabelValue;
    private Label windLabelValueX;
    private Label windLabelValueY;
    private Label angleLabelValue;


    private Label arrowPositionX;
    private Label arrowPositionY;

    private VBox mainVBox;
    private Pane pasekPane;

    private Physics physics;

    private Rotate rotate;


    private Rectangle arrowRectangle;

    private AnimationTimer timer;

    public GameScreen(Main main){
        try {
            background = new Image(new FileInputStream("gra.png"));
            arrowImage = new Image(new FileInputStream("strzala.png"));
            pasek = new Image(new FileInputStream("pasek.png")) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        beginConfiguration();
        this.main = main;
        addEventsListeners();
        drawArrowBeforeShot(90);
    }
    private void beginConfiguration(){


        isStarted = false;
        arrowRectangle = new Rectangle(75,15, Color.RED);

        executeRectangle = new Rectangle(250,25);
        tickRectangle = new Rectangle(250,25);
        startStopRectangle = new Rectangle(250,25);
        exitRectangle = new Rectangle(100,30);

        executeRectangle.setX(300);
        tickRectangle.setX(300);
        startStopRectangle.setX(300);
        executeRectangle.setY(3);
        startStopRectangle.setY(36);
        tickRectangle.setY(72);
        exitRectangle.setX(800);
        exitRectangle.setY(10);
        executeRectangle.setFill(Color.rgb(255,2,2,0));
        tickRectangle.setFill(Color.rgb(255,2,2,0));
        startStopRectangle.setFill(Color.rgb(255,2,2,0));
        exitRectangle.setFill(Color.rgb(255,2,2,0));


        paski = new VBox();
        pasekPower = new HBox();
        pasekX = new HBox();
        pasekY = new HBox();
        pasekAngle = new HBox();
        gamePane = new Pane();
        buttonsVBox = new VBox();
        pasekPane = new Pane();

        angleLabelValue = new Label("0");
        powerLabelValue = new Label("50");
        windLabelValueX = new Label("50");
        windLabelValueY = new Label("50");

        angleLabelValue.setTextFill(Color.WHITE);
        powerLabelValue.setTextFill(Color.WHITE);
        windLabelValueX.setTextFill(Color.WHITE);
        windLabelValueY.setTextFill(Color.WHITE);

        angleLabelValue.setScaleX(1.5);
        angleLabelValue.setScaleY(1.5);
        powerLabelValue.setScaleX(1.5);
        powerLabelValue.setScaleY(1.5);
        windLabelValueX.setScaleX(1.5);
        windLabelValueX.setScaleY(1.5);
        windLabelValueY.setScaleX(1.5);
        windLabelValueY.setScaleY(1.5);


        arrowPositionX = new Label(Integer.toString(Physics.FIRST_POSITION_X));
        arrowPositionY = new Label(Integer.toString(Physics.FIRST_POSITION_Y));






        arrowPositionX.setScaleX(1.5);
        arrowPositionX.setScaleY(1.5);
        arrowPositionY.setScaleX(1.5);
        arrowPositionY.setScaleY(1.5);
        arrowPositionY.setTextFill(Color.WHITE);
        arrowPositionX.setTextFill(Color.WHITE);
        arrowPositionY.setPadding(new Insets(7,0,0,0));
        positionLabelsVBox = new VBox();
        positionLabelsVBox.setPadding(new Insets(3,0,0,440));
        positionLabelsVBox.getChildren().add(arrowPositionX);
        positionLabelsVBox.getChildren().add(arrowPositionY);

        configorationHBox = new HBox();
        powerSlider = new Slider(0, 100, 10);
        windSliderX = new Slider(-50, 50, 0);
        gravitationSlider = new Slider(-50, 50, 0);
        angle = new Slider(0, 180, 90);////////////////////////////////
        pasekPower.getChildren().addAll(powerSlider,powerLabelValue);
        pasekX.getChildren().addAll(windSliderX,windLabelValueX);
        pasekX.setPadding(new Insets(8,0,0,0));
        pasekY.setPadding(new Insets(8,0,0,0));
        pasekAngle.setPadding(new Insets(8,0,0,0));
        pasekY.getChildren().addAll(gravitationSlider,windLabelValueY);

        pasekAngle.getChildren().addAll(angle,angleLabelValue);

        paski.getChildren().addAll(pasekPower,pasekX,pasekY,pasekAngle);
        paski.setPadding(new Insets(9,0,0,125));

        //buttonsVBox.getChildren().addAll(executeRectangle);
        //pasekPane.getChildren().addAll(executeRectangle);

        configorationHBox.getChildren().addAll(paski,buttonsVBox,positionLabelsVBox);


        rotate = new Rotate();
        rotate.setPivotX(20);
        rotate.setPivotY(20);

        arrowRectangle.getTransforms().add(rotate);
        backgroundImageView = new ImageView(background);





        arrowImageView = new ImageView(arrowImage);
        backgroundImageView.setX(0);
        pasekImageView = new ImageView(pasek);
        arrowRectangle.setFill(new ImagePattern(arrowImage));
        arrowRectangle.setX(Physics.FIRST_POSITION_X-75);
        arrowRectangle.setY(Physics.FIRST_POSITION_Y-7);

        mainVBox= new VBox();

        pasekPane.getChildren().addAll(pasekImageView,configorationHBox);
        pasekPane.getChildren().addAll(executeRectangle,startStopRectangle,tickRectangle,exitRectangle);
        gamePane.getChildren().add(backgroundImageView);
        gamePane.getChildren().add(arrowRectangle);

        mainVBox.getChildren().add(gamePane);
        mainVBox.getChildren().add(pasekPane);

        this.getChildren().add(mainVBox);


        timer = new AnimationTimer() {

            @Override
            public synchronized void handle(long now) {
                arrowRectangle.setX(physics.getArrow().getPosX()-75);
                arrowRectangle.setY(physics.getArrow().getPosY()-7);
                arrowPositionX.setText(Double.toString(physics.getArrow().getPosX()));
                arrowPositionY.setText(Double.toString(physics.getArrow().getPosY()));
            }

        };



    }

    private void addEventsListeners(){
        powerSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                powerLabelValue.setText(Integer.toString(newValue.intValue()));
                //physics.setPower(newValue.intValue());
            }
        });
        windSliderX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueX.setText(Integer.toString(newValue.intValue()));
                //physics.setWindX(newValue.intValue());
            }
        });
        gravitationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueY.setText(Integer.toString(newValue.intValue()));
                //physics.setg(newValue.intValue());
            }
        });

        angle.valueProperty().addListener((observable, oldValue, newValue) -> {
            //arrowImageView.setRotate(newValue.intValue()-90);
            angleLabelValue.setText(Integer.toString(newValue.intValue()));
            //physics.getArrow().setAngleX(newValue.intValue());
            drawArrowBeforeShot(newValue.intValue());
        });

        executeRectangle.setOnMouseClicked(event -> {
            if(!isStarted){
                main.startLoop();
                main.enableAutoamtedTicks();
                timer.start();
            }

            isStarted = true;
        });
        startStopRectangle.setOnMouseClicked(event -> main.changeAutomatedLoop());

        tickRectangle.setOnMouseClicked(event -> main.singleTick());
        exitRectangle.setOnMouseClicked(event -> {
            main.stopLoop();
            main.exit();
        });
    }

    private void drawArrowBeforeShot(int rotatea){
        //arrowRectangle.setX(physics.getArrow().getPosX());
        //arrowRectangle.setY(physics.getArrow().getPosY());\
        rotate.setPivotX(rotatea);
        arrowRectangle.getTransforms().add(rotate);
        arrowRectangle.setRotate(90);
}

    public void drawArrowAfterShot(){

    }

    public Slider getAngle() {
        return angle;
    }

    public Slider getPowerSlider() {
        return powerSlider;
    }

    public Slider getWindSliderX() {
        return windSliderX;
    }

    public Slider getGravitationSlider() {
        return gravitationSlider;
    }

    public void setPhysics(Physics physics) {
        this.physics = physics;
    }
}
