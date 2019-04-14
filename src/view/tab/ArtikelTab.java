package view.tab;

import javafx.scene.control.Tab;
import model.util.facade.Facade;
import model.util.observer.Observer;
import view.tab.table.ArtikelTable;

public class ArtikelTab extends Tab implements Observer
{
    private ArtikelTable artikelLijst;

    public ArtikelTab()
    {
        super("Artikelen");

        this.setClosable(false);

        artikelLijst = new ArtikelTable();

        this.setContent(artikelLijst);

        Facade.registerToDatabase(this);
        update();
    }


    @Override
    public void update()
    {
        Facade.updateTable(artikelLijst);
    }
}
