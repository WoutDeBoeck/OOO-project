package model.event;

import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.artikel.Artikel;
import model.util.facade.Facade;


public class EnterKeyHandler implements EventHandler<KeyEvent>
{
    private TableView table;

    public EnterKeyHandler(TableView table)
    {
        if(table != null)
        {
            this.table = table;
        }
    }

    @Override
    public void handle(KeyEvent event)
    {
        KeyCode character = event.getCode();

        System.out.println(character);

        if (character.equals(KeyCode.ENTER))
        {
            String code = ((TextField) event.getSource()).getText();

            System.out.println(code);

            if (code != null && !code.isEmpty())
            {
                Artikel display = Facade.getDisplayArtikel(code);

                if(display != null)
                {
                    table.getItems().add(display);
                    table.sort();
                }

                ((TextField) event.getSource()).setText("");
            }
        }
    }
}
