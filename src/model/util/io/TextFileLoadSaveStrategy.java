package model.util.io;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

//TODO: Refactor to comply with Story 2

public class TextFileLoadSaveStrategy implements LoadSaveStrategy
{
    private final String FILE_NAME = "artikel.txt";
    private final ClassLoader LOADER = this.getClass().getClassLoader();

    public TextFileLoadSaveStrategy()
    {}


    @Override
    public String[] load()
    {
        BufferedReader bufferedReader;

        try
        {
            URL url = LOADER.getResource(FILE_NAME);

            File file = null;

            if(url != null)
            {
                file = new File(url.toURI());
            }

            if(file != null && file.exists())
            {

                FileReader reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);

                ArrayList<String> lines = new ArrayList<>();
                String currentLine = "";

                while (currentLine != null)
                {
                    currentLine = bufferedReader.readLine();

                    if (currentLine != null)
                    {
                        lines.add(currentLine);
                    }
                }

                bufferedReader.close();

                return lines.toArray(new String[0]);
            }
            else
            {
                return new String[0];
            }

        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
            return new String[0];
        }
    }


    @Override
    public void save(String[] lines)
    {
        BufferedWriter writer;

        if(lines != null && lines.length != 0)
        {
            try
            {
                URL url = LOADER.getResource(FILE_NAME);

                if (url != null)
                {
                    File file = new File(url.toURI());

                    if(file != null && file.exists())
                    {
                        writer = new BufferedWriter(new FileWriter(file));

                        for(String line : lines)
                        {
                            writer.write(line);
                            writer.newLine();
                        }

                        writer.close();
                    }
                }
            }
            catch(IOException | URISyntaxException e)
            {
                e.printStackTrace();
            }
        }
    }
}
