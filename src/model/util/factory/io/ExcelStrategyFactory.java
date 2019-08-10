package model.util.factory.io;

import model.util.io.ExcelFileLoadSaveStrategy;
import model.util.io.LoadSaveStrategy;

public class ExcelStrategyFactory implements FactorySingleton
{
    private static ExcelStrategyFactory instance;


    private ExcelStrategyFactory()
    {}


    @Override
    public LoadSaveStrategy create()
    {
        return new ExcelFileLoadSaveStrategy();
    }

    public static FactorySingleton getInstance()
    {
        if(instance == null)
        {
            instance = new ExcelStrategyFactory();
        }

        return instance;
    }
}