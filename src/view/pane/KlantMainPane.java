package view.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import controller.event.PriceTotalHandler;
import model.util.facade.Facade;
import view.tab.table.ArtikelTable;

public class KlantMainPane extends BorderPane
{
    private ArtikelTable artikelen;


    public KlantMainPane()
    {
        artikelen = new ArtikelTable();

        Facade.subscribeToKassaTable(artikelen.getItems());

        Label totaal = new Label("Totaal: 0.0");
        totaal.prefWidthProperty().bind(artikelen.widthProperty());
        totaal.setFont(Font.font(16));
        totaal.setAlignment(Pos.CENTER);

        artikelen.getItems().addListener(new PriceTotalHandler(totaal));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(artikelen, totaal);

        this.setCenter(vBox);
    }
}
