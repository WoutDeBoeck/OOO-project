package controller.converter;

import javafx.util.StringConverter;
import model.korting.KortingStrategies;

public class KortingStrategyConverter extends StringConverter<KortingStrategies>
{

    public KortingStrategyConverter()
    {}

    @Override
    public String toString(KortingStrategies strategy)
    {
        return strategy.toString();
    }

    @Override
    public KortingStrategies fromString(String string)
    {
        KortingStrategies strategy = null;

        if(string != null && !string.trim().isEmpty())
        {
            string = string.toUpperCase().replaceAll(" ", "_");

            strategy = KortingStrategies.valueOf(string);
        }

        return strategy;
    }
}
