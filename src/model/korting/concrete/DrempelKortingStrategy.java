package model.korting.concrete;

import model.artikel.Artikel;
import model.korting.KortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class DrempelKortingStrategy implements KortingStrategy
{
    private double kortingspercentage;
    private double drempel;


    public DrempelKortingStrategy(Double kortingspercentage, Double drempel)
    {
        setKortingspercentage(kortingspercentage);
        setDrempel(drempel);
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

    public void setDrempel(double drempel)
    {
        if(drempel < 0.0)
        {
            drempel = 0.0;
        }

        this.drempel = drempel;
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
            prijs += artikel.getPrijs() * artikel.getAantal();
        }

        if(prijs > drempel)
        {
            prijs = prijs - (prijs * (kortingspercentage / 100));
        }

        return prijs;
    }
}
