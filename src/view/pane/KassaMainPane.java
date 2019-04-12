package view.pane;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.tab.KassaTab;
import view.tab.ArtikelTab;

public class KassaMainPane extends BorderPane
{
	public KassaMainPane()
    {
	    TabPane tabPane = new TabPane();

        KassaTab kassaTab = new KassaTab();
        ArtikelTab artikelTab = new ArtikelTab();
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");

        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);

        this.setCenter(tabPane);
	}
}
