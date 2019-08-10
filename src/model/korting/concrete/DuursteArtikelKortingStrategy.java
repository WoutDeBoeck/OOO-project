package model.korting.concrete;

import model.artikel.Artikel;
import model.korting.KortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class DuursteArtikelKortingStrategy implements KortingStrategy
{
    private double kortingspercentage;


    public DuursteArtikelKortingStrategy(Double kortingPercentage)
    {
        setKortingspercentage(kortingPercentage);
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

    @Override
    public double calculateKorting(List<Artikel> artikels)
    {
        if(artikels == null)
        {
            artikels = new ArrayList<>();
        }

        Artikel duurste = null;

        for(Artikel artikel : artikels)
        {
            if(duurste == null)
            {
                duurste = artikel;
            }
            else
            {
                if(duurste.getPrijs() < artikel.getPrijs())
                {
                    duurste = artikel;
                }
            }
        }

        double prijs = 0.0;

        for(Artikel artikel : artikels)
        {
            if(artikel.equals(duurste))
            {
                //Ervan uitgaande dat de korting op slechts een artikel wordt toegepast

                double kortingPrijs = artikel.getPrijs() - (artikel.getPrijs() * (kortingspercentage /100));

                prijs += kortingPrijs + (artikel.getPrijs() * (artikel.getAantal() - 1));
            }
            else
            {
                prijs += artikel.getPrijs() * artikel.getAantal();
            }
        }

        return prijs;
    }
}
