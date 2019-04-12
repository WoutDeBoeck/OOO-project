package view.tab;

import javafx.scene.control.Tab;
import model.util.facade.Facade;
import view.tab.table.ArtikelTable;

public class ArtikelTab extends Tab
{
    private ArtikelTable artikelLijst;

    public ArtikelTab()
    {
        super("Artikelen");

        this.setClosable(false);

        artikelLijst = new ArtikelTable();

        update();

        this.setContent(artikelLijst);
    }

    public void update()
    {
        Facade.populateTable(artikelLijst);
    }
}
