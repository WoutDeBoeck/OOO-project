package model.korting.concrete;

import model.artikel.Artikel;
import model.korting.KortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class GroepKortingStrategy implements KortingStrategy
{
    private String groep;
    private double kortingspercentage;


    public GroepKortingStrategy(Double korting, String groep)
    {
        setKortingspercentage(korting);
        setGroep(groep);
    }


    public void setKortingspercentage(double kortingspercentage)
    {
        if(kortingspercentage < 0.0)
        {
            kortingspercentage = 0.0;
        }
        else if(kortingspercentage > 100.0)
        {
            kortingspercentage = 100.0;
        }

        this.kortingspercentage = kortingspercentage;
    }

    public void setGroep(String groep)
    {
        if(groep == null || groep.trim().isEmpty())
        {
            throw new IllegalArgumentException("Een Groepkorting moet een valide groep gespecifieerd hebben.");
        }

        this.groep = groep;
    }


    @Override
    public double calculateKorting(List<Artikel> artikels)
    {
        if(artikels == null)
        {
            artikels = new ArrayList<>();
        }

        double prijs = 0.0;

        for(Artikel artikel : artikels)
        {
            if(artikel.getGroep().equals(groep))
            {
                prijs += (artikel.getPrijs() - (artikel.getPrijs() * (kortingspercentage / 100))) * artikel.getAantal();
            }
            else
            {
                prijs += artikel.getPrijs() * artikel.getAantal();
            }
        }

        return prijs;
    }
}
