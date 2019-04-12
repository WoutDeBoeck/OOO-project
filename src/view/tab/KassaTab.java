package view.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.event.EnterKeyHandler;
import view.tab.table.ArtikelTable;

public class KassaTab extends Tab
{
    private TextField input;
    private Button finish;

    private VBox mainBox;
    private HBox controlBox;

    private ArtikelTable artikelLijst;


    public KassaTab()
    {
        super("Kassa");

        this.setClosable(false);

        input = new TextField();
        finish = new Button("Finish");

        mainBox = new VBox(2);
        controlBox = new HBox(2);

        controlBox.setAlignment(Pos.CENTER);

        artikelLijst = new ArtikelTable();

        EnterKeyHandler enterHandler = new EnterKeyHandler(artikelLijst);

        input.addEventFilter(KeyEvent.KEY_PRESSED, enterHandler);

        input.setPrefWidth(696);

        controlBox.getChildren().addAll(input, finish);
        mainBox.getChildren().addAll(artikelLijst, controlBox);

        this.setContent(mainBox);
    }
}
