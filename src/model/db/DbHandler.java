package model.db;

import model.artikel.Artikel;
import model.artikel.ArtikelFactory;
import model.util.io.LoadSaveStrategy;

public class DbHandler
{
    private LoadSaveStrategy strategy;

    public DbHandler(LoadSaveStrategy strategy)
    {
        setStrategy(strategy);
    }

    public void setStrategy(LoadSaveStrategy strategy)
    {
        if(strategy != null)
        {
            this.strategy = strategy;
        }
    }


    public void load(String regex)
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();

        String[] lines = strategy.load();

        if(lines != null && lines.length != 0)
        {
            for(String line : lines)
            {
                System.out.println(line);

                Artikel a = ArtikelFactory.createArtikel(line, regex);
                if(a != null)
                {
                    db.addArtikel(a);
                }
            }
        }
    }

    public void load()
    {
        load(",");
    }


    public void save()
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();

        String[] lines = db.toStringArray();

        if(lines != null || lines.length != 0)
        {
            strategy.save(lines);
        }
    }
}
