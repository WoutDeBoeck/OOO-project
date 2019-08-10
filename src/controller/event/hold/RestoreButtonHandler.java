package controller.event.hold;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RestoreButtonHandler implements EventHandler<ActionEvent>
{
    private ObservableList artikels;
    private ObservableList hold;


    public RestoreButtonHandler(ObservableList artikels, ObservableList hold)
    {
        this.artikels = artikels;
        this.hold = hold;
    }

    @Override
    public void handle(ActionEvent event)
    {
        artikels.remove(0, artikels.size());

        artikels.addAll(hold);
    }
}
