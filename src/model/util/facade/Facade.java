package model.util.facade;

import controller.converter.KortingStrategyConverter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.artikel.Artikel;
import model.artikel.ArtikelFactory;
import model.db.DbHandler;
import model.db.InMemoryArtikelDb;
import controller.event.table.TableChangeListener;
import model.korting.KortingStrategies;
import model.korting.KortingStrategy;
import model.util.factory.KortingFactory;
import model.util.factory.io.ExcelStrategyFactory;
import model.util.factory.io.TextStrategyFactory;
import model.util.io.LoadSaveStrategy;
import model.util.observer.Observer;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;

public class Facade
{

    private static final String FILENAME = "config.properties";
    private static ObservableList tableList;

    private static KortingStrategies strategy = KortingStrategies.GEEN_KORTING;

    //Properties
    public static void changeMode(String mode)
    {
        Properties properties = new Properties();
        ClassLoader loader = Facade.class.getClassLoader();

        try
        {
            File file = new File(loader.getResource(FILENAME).toURI());

            BufferedReader reader = new BufferedReader(new FileReader(file));

            properties.load(reader);
            reader.close();

            properties.setProperty("mode", mode.toLowerCase());

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            properties.store(writer, null);

            writer.close();
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

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
                strategy = (LoadSaveStrategy) TextStrategyFactory.getInstance().create();
            }
            else if(mode.equals("excel"))
            {
                strategy = (LoadSaveStrategy) ExcelStrategyFactory.getInstance().create();
            }
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }

        return strategy;
    }

    public static String getDbStrategyText()
    {
        Properties properties = new Properties();
        ClassLoader loader = Facade.class.getClassLoader();

        String mode = "Tekst";

        try
        {

            File file = new File(loader.getResource(FILENAME).toURI());

            BufferedReader reader = new BufferedReader(new FileReader(file));

            properties.load(reader);

            reader.close();
            mode = properties.getProperty("mode");

            mode = mode.substring(0,1).toUpperCase() + mode.substring(1);
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }

        return mode;
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

    //Korting

    public static void setKortingStrategy(String name)
    {
        KortingStrategyConverter converter = new KortingStrategyConverter();
        strategy = converter.fromString(name);
    }

    public static int getKortingArgumentCount()
    {
        return strategy.getArgumentCount();
    }

    public static KortingStrategy getKortingStrategy()
    {
        return KortingFactory.getStrategy(strategy);
    }

    public static String[] getKortingStrategyNames()
    {
        KortingStrategies[] values = KortingStrategies.values();
        String[] names = new String[values.length];

        for(int i = 0; i < values.length; i++)
        {
            names[i] = values[i].toString();
        }

        return names;
    }

    //View

    public static void setTableList(ObservableList list)
    {
        if(list != null)
        {
            tableList = list;
        }
    }

    public static void subscribeToKassaTable(ObservableList artikelListener)
    {
        if(artikelListener != null)
        {
            TableChangeListener listener = new TableChangeListener(artikelListener);

            tableList.addListener(listener);
        }
    }

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
