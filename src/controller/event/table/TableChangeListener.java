package controller.event.table;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.artikel.Artikel;
import model.artikel.ArtikelFactory;

import java.util.List;

public class TableChangeListener implements ListChangeListener<String>
{
    private ObservableList<Artikel> listeningTable;


    public TableChangeListener(ObservableList listeningTable)
    {
        this.listeningTable = listeningTable;
    }


    @Override
    public void onChanged(Change c)
    {
        c.next();

        List<Artikel> permutation;

        if(c.wasAdded())
        {
            permutation = c.getAddedSubList();

            for(Artikel a : permutation)
            {
                if(listeningTable.contains(a))
                {
                    int i = listeningTable.indexOf(a);

                    Artikel current = listeningTable.get(i);

                    Artikel nw = ArtikelFactory.createDisplayArtikel(current);

                    nw.setAantal(current.getAantal() + 1);

                    listeningTable.set(i, nw);
                }
                else
                {
                    listeningTable.add(a);
                }
            }
        }
        else if(c.wasRemoved())
        {
            permutation = c.getRemoved();

            for(Artikel a : permutation)
            {
                if(listeningTable.contains(a))
                {
                    int i = listeningTable.indexOf(a);

                    Artikel current = listeningTable.get(i);

                    Artikel nw = ArtikelFactory.createDisplayArtikel(current);

                    nw.setAantal(current.getAantal() - 1);

                    if(nw.getAantal() == 0)
                    {
                        listeningTable.remove(a);
                    }
                    else
                    {
                        listeningTable.set(i, nw);
                    }
                }
            }
        }
    }
}
