package model.korting;

import model.artikel.Artikel;

import java.util.List;

public interface KortingStrategy
{
    /**
     * Berekend de nieuwe prijs na het toepassen van de gekozen kortingsstrategie.
     * @param artikels
     * Een lijst van alle artikelen die gekocht worden
     * @return
     * De totaalprijs inclusief korting
     */
    double calculateKorting(List<Artikel> artikels);
}