package controller.event.table;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DeleteKeyHandler implements EventHandler<KeyEvent>
{
    private TableView table;

    public DeleteKeyHandler(TableView table)
    {
        this.table = table;
    }

    @Override
    public void handle(KeyEvent event)
    {
        if(event.getCode() == KeyCode.DELETE)
        {
            ObservableList list = table.getSelectionModel().getSelectedItems();
            for (Object o : list)
            {
                table.getItems().remove(o);
            }
        }
    }
}
