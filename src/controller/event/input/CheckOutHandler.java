package controller.event.input;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.verkoop.Verkoop;
import model.artikel.Artikel;
import model.util.facade.Facade;

import java.util.HashMap;
import java.util.Map;

public class CheckOutHandler implements EventHandler<ActionEvent>
{
    private TableView table;


    public CheckOutHandler(TableView table)
    {
        this.table = table;
    }

    @Override
    public void handle(ActionEvent event)
    {
        HashMap<String, Integer> cart = new HashMap<>();

        for(Object o : table.getItems())
        {
            if(cart.containsKey(((Artikel) o).getCode()))
            {
                cart.put(((Artikel) o).getCode(), cart.get(((Artikel) o).getCode()) + 1);
            }
            else
            {
                cart.put(((Artikel) o).getCode(), 1);
            }
        }

        for(Map.Entry<String, Integer> entry : cart.entrySet())
        {
            Facade.removeArtikelAmountFromDb(entry.getKey(), entry.getValue());
        }

        //TODO: display total and korting

        table.getItems().removeAll(table.getItems());
    }
}
