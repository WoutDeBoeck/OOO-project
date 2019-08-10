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
    public InstellingTab()
    {
        super("Instellingen");
        this.setClosable(false);

        VBox main = new VBox(20);
        HBox mode = new HBox(10);
        VBox korting = new VBox(10);

        ChoiceBox<String> modeChoice = new ChoiceBox();
        ChoiceBox<String> kortingChoice = new ChoiceBox<>();

        modeChoice.getItems().addAll("Tekst", "Excel");
        kortingChoice.getItems().addAll(Facade.getKortingStrategyNames());

        String selected = Facade.getDbStrategyText();

        modeChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Facade.changeMode(newValue));
        modeChoice.setValue(selected);

        kortingChoice.setValue(Facade.getKortingStrategyNames()[0]);
        kortingChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Facade.setKortingStrategy(newValue));

        mode.getChildren().addAll(new Label("Opslagmodus"), modeChoice);
        mode.setAlignment(Pos.CENTER);

        korting.getChildren().addAll(new Label("Toegepaste Korting"), kortingChoice);
        korting.setAlignment(Pos.CENTER);

        main.getChildren().addAll(mode, korting);
        main.setAlignment(Pos.CENTER);

        this.setContent(main);
    }
}
