package view.tab.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.artikel.Artikel;

import java.util.Comparator;

public class ArtikelTable extends TableView
{
    private TableColumn<Artikel, String> codeColumn;
    private TableColumn<Artikel, String> omschrijvingColumn;
    private TableColumn<Artikel, String> groepColumn;
    private TableColumn<Artikel, String> prijsColumn;
    private TableColumn<Artikel, String> aantalColumn;

    public ArtikelTable()
    {
        codeColumn = new TableColumn<>("Code");
        omschrijvingColumn = new TableColumn<>("Omschrijving");
        groepColumn = new TableColumn<>("Groep");
        prijsColumn = new TableColumn<>("Prijs");
        aantalColumn = new TableColumn<>("Aantal");

        codeColumn.prefWidthProperty().bind(this.widthProperty().divide(5));
        omschrijvingColumn.prefWidthProperty().bind(this.widthProperty().divide(5));
        groepColumn.prefWidthProperty().bind(this.widthProperty().divide(5));
        prijsColumn.prefWidthProperty().bind(this.widthProperty().divide(5));
        aantalColumn.prefWidthProperty().bind(this.widthProperty().divide(5));

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));
        groepColumn.setCellValueFactory(new PropertyValueFactory<>("groep"));
        prijsColumn.setCellValueFactory(new PropertyValueFactory<>("prijs"));
        aantalColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));

        getColumns().addAll(codeColumn, omschrijvingColumn, groepColumn, prijsColumn, aantalColumn);

        this.getSortOrder().add(omschrijvingColumn);
        this.sort();
    }
}
