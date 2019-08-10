package controller.event.input;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
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
                if(Facade.getArtikelByCode(code) == null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Er zijn geen artikels gevonden met code \"" + code + "\" in de database");
                    alert.setTitle("Alert");
                    alert.setHeaderText("Kan Artikel \"" + code + "\" niet vinden.");
                    alert.showAndWait();
                }
                else
                {
                    int stock = Facade.getCurrentStock(code);

                    int alreadyPresent = 0;

                    for (Object o : table.getItems())
                    {
                        if (((Artikel) o).getCode().equals(code))
                        {
                            alreadyPresent++;
                        }
                    }

                    if (stock > 0 && stock > alreadyPresent)
                    {
                        Artikel display = Facade.getDisplayArtikel(code);

                        if (display != null)
                        {
                            table.getItems().add(display);
                            table.sort();
                        }
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Het artikel met code \"" + code + "\" kan niet worden toegevoegd, want er zijn er niet meer genoeg van in stock.");
                        alert.setTitle("Alert");
                        alert.setHeaderText("Kan Artikel \"" + code + "\" niet toevoegen.");
                        alert.showAndWait();
                    }
                }

                ((TextField) event.getSource()).setText("");
            }
        }
    }
}
