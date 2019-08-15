package model.util.factory;

import model.korting.KortingStrategies;
import model.korting.KortingStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

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
                    Parameter p = usedClass.getConstructors()[0].getParameters()[usedClass.getConstructors()[0].getParameters().length - 1];
                    Constructor constructor = usedClass.getConstructor(korting.getClass(), p.getType());
                    returnStrategy = (KortingStrategy) constructor.newInstance(korting, p.getType().cast(aux));
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