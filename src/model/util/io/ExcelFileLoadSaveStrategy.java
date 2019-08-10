package model.util.io;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class ExcelFileLoadSaveStrategy implements LoadSaveStrategy
{
    private ExcelPlugin plugin;
    private final String FILE_NAME = "artikel.xls";
    private final ClassLoader LOADER = this.getClass().getClassLoader();


    public ExcelFileLoadSaveStrategy()
    {
        plugin = new ExcelPlugin();
    }


    @Override
    public String[] load()
    {
        File file = null;
        ArrayList<ArrayList<String>> list = null;

        try
        {
            URL url = LOADER.getResource(FILE_NAME);

            if(url != null)
            {
                URI uri = url.toURI();

                if(uri != null)
                {
                    file = new File(uri);
                }

                if(file != null)
                {
                    list = plugin.read(file);
                }
            }
        }
        catch (URISyntaxException | BiffException | IOException e)
        {
            e.printStackTrace();
        }

        String[] array = new String[0];

        if(list != null)
        {
            array = new String[list.size()];
            int i = 0;

            for(ArrayList<String> a :  list)
            {
                String string = "";

                for(String s : a)
                {
                    string += s + ",";
                }

                array[i++] = string.substring(0, string.length() - 1);
            }
        }

        return array;
    }

    @Override
    public void save(String[] lines)
    {
        ArrayList<ArrayList<String>> total = new ArrayList<>();
        ArrayList<String> row = new ArrayList<>();

        for(String s : lines)
        {
            String[] split = s.split(",");

            for (int i = 0; i < split.length; i++)
            {
                row.add(split[i]);
            }

            total.add(row);
            row = new ArrayList<>();
        }

        try
        {
            URL url = LOADER.getResource(FILE_NAME);

            if (url != null)
            {
                File file = new File(url.toURI());

                if (file != null && file.exists())
                {
                    plugin.write(file, total);
                }
            }
        }
        catch (URISyntaxException | BiffException | IOException | WriteException e)
        {
            e.printStackTrace();
        }
    }
}
