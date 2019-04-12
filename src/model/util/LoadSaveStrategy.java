package model.util;

/**
 * LoadSaveStrategy garandeerdt dat de implementerende klassen een methode voor het laden en het opslaan van gegevens zullen hebben.
 * @author Wout De Boeck
 */
public interface LoadSaveStrategy
{
    String[] load();

    void save(String[] lines);
}
