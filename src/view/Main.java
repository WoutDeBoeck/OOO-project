package view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.db.InMemoryArtikelDb;
import model.util.facade.Facade;
import view.screen.KassaView;
import view.screen.KlantView;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
	    Facade.loadDb();

		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}

    @Override
    public void stop() throws Exception
    {
        InMemoryArtikelDb db = InMemoryArtikelDb.getInstance();

        Facade.saveDb();
        super.stop();
    }

    public static void main(String[] args)
	{
	    launch(args);
	}
}
