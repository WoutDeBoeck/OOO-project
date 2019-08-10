package model.korting.concrete;

import model.artikel.Artikel;
import model.korting.KortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class GeenKortingStrategy implements KortingStrategy
{

    public GeenKortingStrategy()
    {}


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
            prijs += artikel.getPrijs() * artikel.getAantal();
        }

        return prijs;
    }
}
