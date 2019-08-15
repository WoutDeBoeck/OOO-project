package model.verkoop;

import model.artikel.Artikel;
import model.artikel.ArtikelFactory;
import model.korting.*;
import model.korting.concrete.GeenKortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Verkoop
{
    private List<Artikel> artikels;
    private KortingStrategy kortingStrategy;


    public Verkoop(List artikels, KortingStrategy strategy)
    {
        setArtikels(artikels);
        rearangeArtikels();
        setKortingStrategy(strategy);
    }


    public void setKortingStrategy(KortingStrategy strategy)
    {
        if(strategy != null)
        {
            kortingStrategy = strategy;
        }
        else
        {
            kortingStrategy = new GeenKortingStrategy();
        }
    }

    private void setArtikels(List<Artikel> artikels)
    {
        if(artikels == null)
        {
            artikels = new ArrayList<>();
        }

        this.artikels = artikels;
    }

    private void rearangeArtikels()
    {
        ArrayList<Artikel> newArtikels = new ArrayList<>();

        for(Artikel artikel : artikels)
        {
            if(newArtikels.contains(artikel))
            {
                int index = newArtikels.indexOf(artikel);

                Artikel newArtikel = ArtikelFactory.createDisplayArtikel(artikel);
                newArtikel.setAantal(newArtikels.get(index).getAantal() + 1);

                newArtikels.set(index, newArtikel);
            }
            else
            {
                newArtikels.add(artikel);
            }
        }

        artikels = newArtikels;
    }


    /**
     * Geeft de totaalprijs van alle Artikels in deze Verkoop terug.
     */
    public double getTotaalPrijs()
    {
        double prijs = 0.0;

        for(Artikel artikel : artikels)
        {
            prijs = artikel.getPrijs() * artikel.getAantal();
        }

        return prijs;
    }

    /**
     * Geeft de totaalprijs van alle Artikels, inclusief korting, in deze Verkoop terug.
     */
    public double getKortingPrijs()
    {
        return kortingStrategy.calculateKorting(artikels);
    }

    public void printKassaBon()
    {
        String header = "Omschrijving          Aantal    Prijs";
        int headerLength = header.length();

        String line = new String(new char[headerLength]).replace("\0", "*");

        System.out.println(header);
        System.out.println(line);

        for (Artikel artikel: artikels)
        {
            String numbers = artikel.getAantal() + "    " + artikel.getPrijs();

            String padding = new String(new char[headerLength - (artikel.getOmschrijving().length() + numbers.length())]).replace("\0", " ");

            padding = padding.concat(numbers);

            System.out.println(artikel.getOmschrijving() + padding);
        }

        System.out.println(line);

        String end = "Betaald (inclusief korting):";

        String prijs = "€ " + getKortingPrijs();

        String padding = new String(new char[headerLength - (end.length() + prijs.length())]).replace("\0", " ");

        System.out.println(end + padding + prijs);
    }
}

//TODO: Remove debug 'Main' class
/*
class Main
{
    public static void main(String[] args)
    {
        ArrayList<Artikel> list = new ArrayList<>();

        list.add(ArtikelFactory.createArtikel("1,artikel1,gr1,10,2"));
        list.add(ArtikelFactory.createArtikel("2,artikel2,gr2,20,3"));
        list.add(ArtikelFactory.createArtikel("3,bartikel3,gr1,10,4"));

        KortingStrategy strategy1 = KortingFactory.getStrategy(KortingStrategies.DUURSTE_ARTIKEL_KORTING, 25.0);
        KortingStrategy strategy2 = KortingFactory.getStrategy(KortingStrategies.DREMPELKORTING, 5.0, 100.0);
        KortingStrategy strategy3 = KortingFactory.getStrategy(KortingStrategies.GROEPKORTING, 5.0, "gr2");

        Verkoop verkoop = new Verkoop(list, strategy1);

        verkoop.printKassaBon();
    }
}*/
