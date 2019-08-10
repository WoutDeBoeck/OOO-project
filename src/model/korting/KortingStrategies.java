package model.korting;

import model.korting.concrete.DrempelKortingStrategy;
import model.korting.concrete.DuursteArtikelKortingStrategy;
import model.korting.concrete.GeenKortingStrategy;
import model.korting.concrete.GroepKortingStrategy;

public enum KortingStrategies
{
    /**
     * Past geen korting toe op de totale prijs. Heeft geen argumenten nodig.
     */
    GEEN_KORTING(GeenKortingStrategy.class, 0),

    /**
     * Past korting toe op alle artikelen van de gespecifieerde groep. Heeft 2 argumenten nodig: een double korting en een String groep.
     */
    GROEPKORTING(GroepKortingStrategy.class, 2),

    /**
     * Past korting toe op 1 instantie van het duurste artikel. Heeft 1 argument nodig: een double korting.
     */
    DUURSTE_ARTIKEL_KORTING(DuursteArtikelKortingStrategy.class, 1),

    /**
     * Past korting toe op de totale prijs als en alleen als die hoger is dan gespecifieerd. Heeft 2 argumenten nodig: een double korting en een double prijsdrempel.
     */
    DREMPELKORTING(DrempelKortingStrategy.class, 2);


    private Class<? extends KortingStrategy> usedClass;
    private int count;


    KortingStrategies(Class<? extends KortingStrategy> usedClass, int count)
    {
        this.usedClass = usedClass;
        this.count = count;
    }


    public Class<? extends KortingStrategy> getUsedClass()
    {
        return usedClass;
    }

    public int getArgumentCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        String defName = name().toLowerCase();
        defName = defName.replaceAll("_", " ");

        String[] parts = defName.split(" ");

        String newName = "";

        for(String string : parts)
        {
            string = string.substring(0, 1).toUpperCase() + string.substring(1);

            if(!string.equalsIgnoreCase(parts[parts.length - 1]))
            {
                string += " ";
            }

            newName += string;
        }

        return newName;
    }
}
