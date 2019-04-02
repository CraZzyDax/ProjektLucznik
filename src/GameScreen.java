import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreen extends HBox {

    Image background;
    ImageView backgroundImageView;
    Main main;
    HBox configorationHBox;
    VBox paski;
    HBox pasekPower;
    HBox pasekX;
    HBox pasekY;
    HBox pasekZ;

    Button executeButton;


    Slider powerSlider;
    Slider windSliderX;
    Slider windSliderY;
    Slider windSliderZ;
    Label powerLabel;
    Label windLabelX;
    Label windLabelY;
    Label windLabelZ;
    Label powerLabelValue;
    Label windLabelValueX;
    Label windLabelValueY;
    Label windLabelValueZ;

    VBox mainVBox;

    public GameScreen(Main main){
        try {
            background = new Image(new FileInputStream("gra.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        beginConfiguration();
        this.main = main;
        addEventsListeners();
    }
    private void beginConfiguration(){
        executeButton = new Button("Wykonaj");
        paski = new VBox();
        pasekPower = new HBox();
        pasekX = new HBox();
        pasekY = new HBox();
        pasekZ = new HBox();

        powerLabel = new Label("Sila: ");
        windLabelX = new Label("Wiatr X: ");
        windLabelY = new Label("Wiatr Y: ");
        windLabelZ = new Label("Wiatr Z: ");
        powerLabelValue = new Label("50");
        windLabelValueX = new Label("50");
        windLabelValueY = new Label("50");
        windLabelValueZ = new Label("50");
        configorationHBox = new HBox();
        powerSlider = new Slider(0, 100, 50);
        windSliderX = new Slider(-50, 50, 0);
        windSliderY = new Slider(-50, 50, 0);
        windSliderZ = new Slider(-50, 50, 0);
        pasekPower.getChildren().add(powerLabel);
        pasekPower.getChildren().add(powerSlider);
        pasekPower.getChildren().add(powerLabelValue);
        pasekX.getChildren().add(windLabelX);
        pasekX.getChildren().add(windSliderX);
        pasekX.getChildren().add(windLabelValueX);
        pasekY.getChildren().add(windLabelY);
        pasekY.getChildren().add(windSliderY);
        pasekY.getChildren().add(windLabelValueY);
        pasekZ.getChildren().add(windLabelZ);
        pasekZ.getChildren().add(windSliderZ);
        pasekZ.getChildren().add(windLabelValueZ);

        paski.getChildren().add(pasekPower);
        paski.getChildren().add(pasekX);
        paski.getChildren().add(pasekY);
        paski.getChildren().add(pasekZ);
        configorationHBox.getChildren().add(paski);
        configorationHBox.getChildren().add(executeButton);


        backgroundImageView = new ImageView(background);
        backgroundImageView.setX(0);

        mainVBox= new VBox();


        mainVBox.getChildren().add(backgroundImageView);
        mainVBox.getChildren().add(configorationHBox);
        this.getChildren().add(mainVBox);
    }
    private void addEventsListeners(){
        powerSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                powerLabelValue.setText(Integer.toString(newValue.intValue()));
            }
        });
        windSliderX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueX.setText(Integer.toString(newValue.intValue()));
            }
        });
        windSliderY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueY.setText(Integer.toString(newValue.intValue()));
            }
        });
        windSliderZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windLabelValueZ.setText(Integer.toString(newValue.intValue()));
            }
        });
    }
}
