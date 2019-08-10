package model.util.factory.io;

import model.util.io.LoadSaveStrategy;
import model.util.io.TextFileLoadSaveStrategy;

public class TextStrategyFactory implements FactorySingleton
{
    private static TextStrategyFactory instance;


    private TextStrategyFactory()
    {
    }


    @Override
    public LoadSaveStrategy create()
    {
        return new TextFileLoadSaveStrategy();
    }

    public static FactorySingleton getInstance()
    {
        if(instance == null)
        {
            instance = new TextStrategyFactory();
        }

        return instance;
    }
}
