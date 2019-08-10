package controller.event;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.artikel.Artikel;

public class PriceTotalHandler implements ListChangeListener
{
    private Label totaal;


    public PriceTotalHandler(Label totaal)
    {
        this.totaal = totaal;
    }

    @Override
    public void onChanged(Change c)
    {
        c.next();

        ObservableList<Artikel> list = c.getList();
        double totalPrice = 0.0;

        for(Artikel a : list)
        {
            totalPrice += (a.getPrijs() * a.getAantal());
        }

        totaal.setText("Totaal: " + String.format("%.2f" ,totalPrice));
    }
}
