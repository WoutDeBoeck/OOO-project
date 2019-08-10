package controller.event.hold;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HoldButtonHandler implements EventHandler<ActionEvent>
{
    private ObservableList artikels;
    private ObservableList hold;


    public HoldButtonHandler(ObservableList artikels, ObservableList hold)
    {
        this.artikels = artikels;
        this.hold = hold;
    }


    @Override
    public void handle(ActionEvent event)
    {
        this.hold.addAll(this.artikels);

        artikels.remove(0, artikels.size());

        for(Object o : hold)
        {
            System.out.println(o);
        }
    }
}
