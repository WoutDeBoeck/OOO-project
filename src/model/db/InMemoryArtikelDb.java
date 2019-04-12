package model.db;

import model.artikel.Artikel;

import java.util.Collection;
import java.util.HashMap;

/**
 * InMemoryArtikelDb is een Singleton die een map van artikelen opslaat. Deze kunnen worden opgevraagd of verwijderd met de respectievelijke methoden.
 * Er kan dankzij het Singleton pattroon ten allen tijde slechts 1 instantie van deze map bestaan.
 * @author Wout De Boeck
 */
public class InMemoryArtikelDb
{
    private HashMap<String, Artikel> artikelen;
    private static InMemoryArtikelDb instance;


    private InMemoryArtikelDb()
    {
        artikelen = new HashMap<>();
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

    public Collection<Artikel> getAll()
    {
        return artikelen.values();
    }

    public void removeArtikel(String code)
    {
        if(code == null)
        {
            code = "";
        }

        artikelen.remove(code);
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
}
