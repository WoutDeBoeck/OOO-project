package model.db;

import model.artikel.Artikel;
import model.util.observer.Observer;
import model.util.observer.ObserverSubject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * InMemoryArtikelDb is een Singleton die een map van artikelen opslaat. Deze kunnen worden opgevraagd of verwijderd met de respectievelijke methoden.
 * Er kan dankzij het Singleton pattroon ten allen tijde slechts 1 instantie van deze map bestaan.
 * @author Wout De Boeck
 */
public class InMemoryArtikelDb implements ObserverSubject
{
    private ArrayList<Observer> observers;

    private HashMap<String, Artikel> artikelen;
    private static InMemoryArtikelDb instance;


    private InMemoryArtikelDb()
    {
        artikelen = new HashMap<>();
        observers = new ArrayList<>();
    }


    public static InMemoryArtikelDb getInstance()
    {
        if(instance == null)
        {
            instance = new InMemoryArtikelDb();
        }

        return instance;
    }


    public void addArtikel(Artikel artikel)
    {
        if(artikel != null)
        {
            artikelen.put(artikel.getCode(), artikel);
        }
    }

    public Artikel getArtikel(String code)
    {
        if(code == null)
        {
            code = "";
        }

        return artikelen.get(code);
    }

    /**
     * Verminderdt het aantal aanwezige instanties van het gegeven Artikel uit de database.
     * @param code
     * De code van het te verminderen Artikel
     * @param amount
     * De hoeveelheid die moet verwijderd worden
     * @return
     * 1 als er niks verwijderd is, 0 als het Artikel succesvol is verwijderd of -1 als de gevraagde hoeveelheid meer was dan de stock
     */
    public int removeAmount(String code, int amount)
    {
        if(code != null && !code.isEmpty())
        {
            if(getArtikel(code) != null && getArtikel(code).getAantal() >= amount)
            {
                getArtikel(code).setAantal(getArtikel(code).getAantal() - amount);
                notifyObservers();
                return 0;
            }
            else
            {
                getArtikel(code).setAantal(0);
                notifyObservers();
                return -1;
            }
        }

        return  1;
    }

    public Collection<Artikel> getAll()
    {
        return artikelen.values();
    }


    /**
     * Maakt een Array van Strings. Elk van deze Strings is een samenstelling van alle data van een Artikel, gescheiden door een ','.
     * @return
     * Array van Strings die de Artikelen uit de Db omschrijven
     */
    public String[] toStringArray()
    {
        Artikel[] artikels = artikelen.values().toArray(new Artikel[0]);

        String[] strings = new String[artikels.length];

        int index = 0;

        for(Artikel artikel : artikels)
        {
            strings[index++] = artikel.getCode() + "," + artikel.getOmschrijving() + "," + artikel.getGroep() + "," + artikel.getPrijs() + "," + artikel.getAantal();
            System.out.println(strings[index - 1]);
        }

        return strings;
    }


    @Override
    public void registerObserver(Observer observer)
    {
        if(observer != null && !observers.contains(observer))
        {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer)
    {
        if(observer != null && observers.contains(observer))
        {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
