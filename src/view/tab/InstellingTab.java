package view.tab;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.util.facade.Facade;

public class InstellingTab extends Tab
{
    HBox kortingArgumentBox;
    HBox auxArgumentBox;

    public InstellingTab()
    {
        super("Instellingen");
        this.setClosable(false);

        VBox main = new VBox(20);
        VBox kortingGroup = new VBox(20);

        //Mode

        HBox mode = new HBox(40);

        ChoiceBox<String> modeChoice = new ChoiceBox();

        modeChoice.getItems().addAll("Tekst", "Excel");

        String selected = Facade.getDbStrategyText();

        modeChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Facade.changeMode(newValue));
        modeChoice.setValue(selected);

        modeChoice.setPrefWidth(180);

        mode.getChildren().addAll(new Label("Opslagmodus"), modeChoice);
        mode.setAlignment(Pos.CENTER);

        //Korting

        HBox kortingBox = new HBox(10);

        ChoiceBox<String> kortingChoice = new ChoiceBox<>();

        kortingArgumentBox = new HBox(72);
        auxArgumentBox = new HBox(30);

        kortingChoice.getItems().addAll(Facade.getKortingStrategyNames());

        kortingChoice.setValue(Facade.getKortingStrategyNames()[0]);
        kortingChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeKortingStrategy(newValue));

        kortingChoice.prefWidthProperty().bind(modeChoice.widthProperty());

        kortingBox.getChildren().addAll(new Label("Toegepaste Korting"), kortingChoice);
        kortingBox.setAlignment(Pos.CENTER);

        Label kortingArgumentLabel = new Label("Korting");
        Label auxArgumentLabel = new Label("Extra Argument");

        TextField kortingArgument = new TextField();
        kortingArgument.prefWidthProperty().bind(modeChoice.widthProperty());
        kortingArgument.textProperty().addListener((observable, oldValue, newValue) -> Facade.setKortingArgument(Double.parseDouble(newValue)));

        TextField auxArgument = new TextField();
        auxArgument.prefWidthProperty().bind(modeChoice.widthProperty());
        auxArgument.textProperty().addListener((observable, oldValue, newValue) -> Facade.setExtraArgument(newValue));

        kortingArgumentBox.getChildren().addAll(kortingArgumentLabel, kortingArgument);
        auxArgumentBox.getChildren().addAll(auxArgumentLabel, auxArgument);

        kortingGroup.getChildren().addAll(kortingBox, kortingArgumentBox, auxArgumentBox);

        kortingArgumentBox.setAlignment(Pos.CENTER);
        auxArgumentBox.setAlignment(Pos.CENTER);

        changeKortingStrategy(kortingChoice.getValue());

        //Add All

        main.getChildren().addAll(mode, kortingGroup);
        main.setAlignment(Pos.CENTER);

        this.setContent(main);
    }

    private void changeKortingStrategy(String value)
    {
        Facade.setKortingStrategy(value);

        int count = Facade.getKortingArgumentCount();

        if(count == 0)
        {
            kortingArgumentBox.setVisible(false);
            auxArgumentBox.setVisible(false);
        }
        else if(count < 2)
        {
            kortingArgumentBox.setVisible(true);
            auxArgumentBox.setVisible(false);
        }
        else
        {
            kortingArgumentBox.setVisible(true);
            auxArgumentBox.setVisible(true);
        }
    }
}
