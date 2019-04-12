package view.tab;

import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.event.EnterKeyHandler;
import view.tab.table.ArtikelTable;

public class KassaTab extends Tab
{
    private TextField input;

    protected VBox mainBox;
    private ArtikelTable artikelLijst;


    public KassaTab()
    {
        super("Kassa");

        this.setClosable(false);

        input = new TextField();
        mainBox = new VBox();
        artikelLijst = new ArtikelTable();

        EnterKeyHandler enterHandler = new EnterKeyHandler(artikelLijst);

        input.addEventFilter(KeyEvent.KEY_PRESSED, enterHandler);

        mainBox.getChildren().addAll(artikelLijst, input);
        this.setContent(mainBox);
    }
}
