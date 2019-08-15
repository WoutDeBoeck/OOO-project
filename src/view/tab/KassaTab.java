package view.tab;

import controller.event.*;
import controller.event.hold.HoldButtonHandler;
import controller.event.hold.RestoreButtonHandler;
import controller.event.input.CheckOutHandler;
import controller.event.input.EnterKeyHandler;
import controller.event.table.DeleteKeyHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.util.facade.Facade;
import view.tab.table.ArtikelTable;

public class KassaTab extends Tab
{
    private TextField input;
    private Button finish;

    private Label prijsTotaal;

    private HBox holdBox;
    private VBox mainBox;
    private HBox controlBox;

    private ArtikelTable artikelLijst;
    private ObservableList holdLijst;

    private HBox payButtonBox;

    public KassaTab()
    {
        super("Kassa");

        this.setClosable(false);

        input = new TextField();
        finish = new Button("Check-Out");
        prijsTotaal = new Label("Totaal: 0.00");

        prijsTotaal.setOpaqueInsets(new Insets(2.0, 0.0, 2.0, 0.0));

        holdBox = new HBox(8);
        mainBox = new VBox(5);
        controlBox = new HBox(2);

        holdBox.setAlignment(Pos.CENTER);
        controlBox.setAlignment(Pos.CENTER);

        Background background = new Background(new BackgroundFill(new Color(0,0,0,0.13), null, null));

        holdBox.setBackground(background);

        Button hold = new Button("Hold");
        Button restore = new Button("Restore");

        artikelLijst = new ArtikelTable();
        holdLijst = FXCollections.observableArrayList();

        hold.setOnAction(new HoldButtonHandler(artikelLijst.getItems(), holdLijst));
        restore.setOnAction(new RestoreButtonHandler(artikelLijst.getItems(), holdLijst));

        hold.setPrefWidth(120);
        restore.setPrefWidth(120);

        holdBox.getChildren().addAll(hold, restore);

        artikelLijst.addEventFilter(KeyEvent.KEY_PRESSED, new DeleteKeyHandler(artikelLijst));
        artikelLijst.getItems().addListener(new PriceTotalHandler(prijsTotaal));

        Facade.setTableList(artikelLijst.getItems());

        EnterKeyHandler enterHandler = new EnterKeyHandler(artikelLijst);

        input.addEventFilter(KeyEvent.KEY_PRESSED, enterHandler);

        finish.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> revealPaymentOptions());

        input.setPrefWidth(670);

        controlBox.getChildren().addAll(input, finish);

        //Payment

        payButtonBox = new HBox(30);

        CheckOutHandler checkOutHandler = new CheckOutHandler(artikelLijst);

        Button betaald = new Button("Betaald");
        betaald.setPrefSize(200, 80);

        betaald.setOnAction(checkOutHandler);

        Button annuleer = new Button("Annuleer");
        annuleer.prefWidthProperty().bind(betaald.widthProperty());
        annuleer.prefHeightProperty().bind(betaald.heightProperty());

        betaald.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> hidePaymentOptions());
        annuleer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> hidePaymentOptions());
        annuleer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> resetVerkoop());

        payButtonBox.getChildren().addAll(betaald, annuleer);
        payButtonBox.setAlignment(Pos.CENTER);

        mainBox.getChildren().addAll(prijsTotaal, holdBox, artikelLijst, payButtonBox, controlBox);

        controlBox.setPrefHeight(60);

        this.setContent(mainBox);
        hidePaymentOptions();
    }

    private void resetVerkoop()
    {
        artikelLijst.getItems().clear();
    }

    private void revealPaymentOptions()
    {
        controlBox.setVisible(false);
        payButtonBox.setVisible(true);
    }

    private void hidePaymentOptions()
    {
        payButtonBox.setVisible(false);
        controlBox.setVisible(true);
    }
}
