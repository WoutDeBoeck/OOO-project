package model.artikel;

public class ArtikelFactory
{
    private ArtikelFactory()
    {}

    /**
     * Maakt een nieuwe Artikel-instantie aan op basis van de gegeven input. De input wordt gesplitst in verschillende delen op basis van de gegeven regex.
     * @param input
     * De String die de nodige gegevens zou moeten bevatten, gesplitst door een regex
     * @param regex
     * De regex die de gegeven String moet verdelen in verschillende gegevens
     * @return
     * Een nieuwe Artikel-instantie, of null als er iets mis ging tijdens de creatie
     */
    public static Artikel createArtikel(String input, String regex)
    {
        Artikel artikel = null;

        if(input != null && !input.isEmpty())
        {
            String[] split = input.split(regex);

            if(split.length <= 5)
            {
                try
                {
                    String code = split[0];
                    String omschrijving = split[1];
                    String groep = split[2];
                    double prijs = Double.parseDouble(split[3]);
                    int aantal = Integer.parseInt(split[4]);

                    artikel = new Artikel(code, omschrijving, groep, prijs, aantal);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return artikel;
    }

    /**
     * Maakt een nieuwe instantie aan op basis van de gegeven input. Gebruikt ',' als standaard regex om input te splitsen.
     * @param input
     * De String die de nodige gegevens zou moeten bevatten
     * @return
     * Een nieuwe Artikel-instantie, of null als er iets misging tijdens de creatie
     */
    public static Artikel createArtikel(String input)
    {
        return createArtikel(input, ",");
    }

    /**
     * Maakt een nieuw Artikel met een aantal van 1 op basis van een bestaand Artikel. Deze methode wordt gebruikt om een displayable object te maken.
     * @param artikel
     * Een bestaand Artikel object
     * @return
     * Een niew Artikel object waarbij aantal 1 is
     */
    public static Artikel createDisplayArtikel(Artikel artikel)
    {
        Artikel a = null;

        if(artikel != null)
        {
            a = new Artikel(artikel.getCode(), artikel.getOmschrijving(), artikel.getGroep(), artikel.getPrijs(), 1);
        }

        return a;
    }
}
