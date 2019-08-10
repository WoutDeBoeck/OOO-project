package model.artikel;

/**
 * Een Artikel is een product dat verkocht en dus ook gekocht kan worden in de winkel.
 * @author Wout De Boeck
 */
public class Artikel implements Comparable<Artikel>
{
    /**
     * De code van een Artikel is een uniek identificatie-nummer waarmee het product kan worden opgezocht en geregistreerd.
     */
    private String code;

    /**
     * De omschrijving van een Artikel geeft een korte inhoud over wat het Artikel is.
     */
    private String omschrijving;

    /**
     * De groep van een Artikel geeft aan met welke andere Artikelen dit Artikel kan worden gegroepeerd.
     */
    private String groep;

    /**
     * De prijs van een Artikel is de aankoopprijs van dat Artikel inclusief btw.
     */
    private double prijs;

    /**
     * Hoeveel instanties van dit Artikel er beschikbaar zijn in voorraad.
     */
    private int aantal;


    /**
     * Maakt een nieuw Artikel-object aan.
     * @param code
     * De unieke code waarmee het Artikel kan worden geïdentificeerd
     * @param omschrijving
     * Een korte omschrijving over het Artikel
     * @param groep
     * De groep waar dit Artikel tot behoord
     * @param prijs
     * De prijs van het Artikel inclusief btw
     * @param aantal
     * Hoeveel Artikkelen er in voorraad zijn
     */
    public Artikel(String code, String omschrijving, String groep, double prijs, int aantal)
    {
        setCode(code);
        setOmschrijving(omschrijving);
        setGroep(groep);
        setPrijs(prijs);
        setAantal(aantal);
    }


    private void setCode(String code)
    {
        if(code == null || code.isEmpty())
        {
            throw new IllegalArgumentException("Een Artikel moet een code hebben.");
        }

        this.code = code;
    }

    public String getCode()
    {
        return code;
    }


    public void setOmschrijving(String omschrijving)
    {
        if(omschrijving == null)
        {
            omschrijving = "";
        }

        this.omschrijving = omschrijving;
    }

    public String getOmschrijving()
    {
        return omschrijving;
    }


    private void setGroep(String groep)
    {
        if(groep == null)
        {
            groep = "";
        }

        this.groep = groep;
    }

    public String getGroep()
    {
        return groep;
    }


    private void setPrijs(double prijs)
    {
        if(prijs <= 0.0)
        {
            prijs = 0.01;
        }

        this.prijs = prijs;
    }

    public double getPrijs()
    {
        return prijs;
    }


    public void setAantal(int aantal)
    {
        if(aantal < 0)
        {
            aantal = 0;
        }

        this.aantal = aantal;
    }

    public int getAantal()
    {
        return aantal;
    }


    @Override
    public int compareTo(Artikel o)
    {
        return omschrijving.compareTo(o.getOmschrijving());
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Artikel)
        {
            if(((Artikel) obj).getOmschrijving().equals(getOmschrijving()) && ((Artikel) obj).getCode().equals(getCode()) && ((Artikel) obj).getGroep().equals(getGroep()))
            {
                return true;
            }
        }

        return false;
    }
}
