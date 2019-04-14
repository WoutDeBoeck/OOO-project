package view.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.event.CheckOutButtonHandler;
import model.event.EnterKeyHandler;
import view.tab.table.ArtikelTable;

import java.awt.event.ActionEvent;

public class KassaTab extends Tab
{
    private TextField input;
    private Button finish;

    private Label prijsTotaal;

    private VBox mainBox;
    private HBox controlBox;

    private ArtikelTable artikelLijst;


    public KassaTab()
    {
        super("Kassa");

        this.setClosable(false);

        input = new TextField();
        finish = new Button("Check-Out");
        prijsTotaal = new Label("Totaal: 0.00");

        prijsTotaal.setOpaqueInsets(new Insets(2.0, 0.0, 2.0, 0.0));

        mainBox = new VBox(5);
        controlBox = new HBox(2);

        controlBox.setAlignment(Pos.CENTER);

        artikelLijst = new ArtikelTable();


        EnterKeyHandler enterHandler = new EnterKeyHandler(artikelLijst, prijsTotaal);
        CheckOutButtonHandler checkOutButtonHandler = new CheckOutButtonHandler(artikelLijst);

        input.addEventFilter(KeyEvent.KEY_PRESSED, enterHandler);
        finish.setOnAction(checkOutButtonHandler);


        input.setPrefWidth(670);

        controlBox.getChildren().addAll(input, finish);
        mainBox.getChildren().addAll(prijsTotaal, artikelLijst, controlBox);

        this.setContent(mainBox);
    }
}
