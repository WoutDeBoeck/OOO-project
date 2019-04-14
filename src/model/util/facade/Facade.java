package model.util.facade;

import javafx.scene.control.TableView;
import model.artikel.Artikel;
import model.artikel.ArtikelFactory;
import model.db.DbHandler;
import model.db.InMemoryArtikelDb;
import model.util.io.LoadSaveStrategy;
import model.util.io.TextFileLoadSaveStrategy;
import model.util.observer.Observer;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;

public class Facade
{

    private static final String FILENAME = "config.properties";

    //Artikel

    /**
     * Zoekt voor het Artikel met de gegeven code in de database. Deze methode verlaagd het aantal van dit Artikel NIET.
     * @param code
     * De code van het gevraagde Artikel
     * @return
     * Het gevraagde Artikel, of null als het niet gevonden wordt
     */
    public static Artikel getArtikelByCode(String code)
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();
        Artikel artikel = null;

        if (code != null || !code.isEmpty())
        {
            artikel = db.getArtikel(code);
        }

        return artikel;
    }

    /**
     * Zoekt het gevraagde Artikel met de gegeven code in de database en geeft een display object terug.
     * @param code
     * De code van het gevraagde Artikel
     * @return
     * Een nieuw Artikel met aantal 1
     */
    public static Artikel getDisplayArtikel(String code)
    {
        Artikel artikel = getArtikelByCode(code);
        Artikel disp = null;

        if(artikel != null)
        {
            disp = ArtikelFactory.createDisplayArtikel(artikel);
        }

        return disp;
    }


    //DB

    public static void registerToDatabase(Observer observer)
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();

        db.registerObserver(observer);
    }

    public static int getCurrentStock(String code)
    {
        Artikel a = getArtikelByCode(code);

        if(a != null)
        {
            return a.getAantal();
        }

        return 0;
    }

    public static int removeArtikelAmountFromDb(String code, int amount)
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();

        return db.removeAmount(code, amount);
    }

    private static LoadSaveStrategy getDbStrategy()
    {
        Properties properties = new Properties();
        LoadSaveStrategy strategy = null;

        try
        {
            ClassLoader loader = Facade.class.getClassLoader();

            File file = new File(loader.getResource(FILENAME).toURI());

            BufferedReader reader = new BufferedReader(new FileReader(file));

            properties.load(reader);

            reader.close();
            String mode = properties.getProperty("mode");
            System.out.println(mode);

            if(mode.equals("tekst"))
            {
                strategy = new TextFileLoadSaveStrategy();
            }
            else if(mode.equals("excel"))
            {
                //TODO: implement excel
            }
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }

        return strategy;
    }

    public static void loadDb()
    {
        LoadSaveStrategy strategy = getDbStrategy();

        if(strategy != null)
        {
            DbHandler handler = new DbHandler(strategy);

            handler.load();
        }
    }

    public static void saveDb()
    {
        LoadSaveStrategy strategy = getDbStrategy();

        if(strategy != null)
        {
            DbHandler handler = new DbHandler(strategy);

            handler.save();
        }
    }

    //View

    public static void updateTable(TableView table)
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();

        if(table != null)
        {
            table.getItems().removeAll(table.getItems());
            table.getItems().addAll(db.getAll());
            table.sort();
        }
    }
}
