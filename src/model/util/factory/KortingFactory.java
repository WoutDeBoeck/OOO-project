package model.util.factory;

import model.korting.KortingStrategies;
import model.korting.KortingStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class KortingFactory
{

    private KortingFactory()
    {}


    public static KortingStrategy getStrategy(KortingStrategies strategy, Object korting, Object aux)
    {
        if(strategy != null)
        {
            Class usedClass = strategy.getUsedClass();

            try
            {

                KortingStrategy returnStrategy = null;

                if(aux != null)
                {
                    Constructor constructor = usedClass.getConstructor(korting.getClass(), aux.getClass());
                    returnStrategy = (KortingStrategy) constructor.newInstance(korting, aux);
                }
                else if(korting != null)
                {
                    Constructor constructor = usedClass.getConstructor(korting.getClass());
                    returnStrategy = (KortingStrategy) constructor.newInstance(korting);
                }
                else
                {
                    returnStrategy = (KortingStrategy) usedClass.newInstance();
                }

                return returnStrategy;
            }
            catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static KortingStrategy getStrategy(KortingStrategies strategy, Object korting)
    {
        return getStrategy(strategy, korting, null);
    }

    public static KortingStrategy getStrategy(KortingStrategies strategy)
    {
        return getStrategy(strategy, null, null);
    }
}
