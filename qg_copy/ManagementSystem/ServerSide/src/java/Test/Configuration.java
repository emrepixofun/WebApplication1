package Test;

/*
 *  Copyright ©2014 Pixofun
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Alican Şekerefe
 */
public class Configuration
{

    private Properties prop = null;
    private String fileName;
    private String filePath;

    public Configuration(String mainDir, String configFileName)
    {
        try
        {
            String path = mainDir;

            File file = FileFinder.findFile(configFileName, path);
            if (file == null)
            {
                throw new RuntimeException("Configuration: File not found!!! -> " + configFileName);
            }

            this.filePath = path;
            this.fileName = configFileName;

            FileInputStream stream = new FileInputStream(file);
            prop = new Properties();
            prop.load(stream);
        }
        catch (Exception e)
        {
        }
    }
    
    private String getField_(String name, Object defaultValue)
    {
        String value = prop.getProperty(name);
        return (value == null ? defaultValue : value)+"";
    }
    
    public String getField(String name, String defaultValue)
    {
        return (String)getField_(name, defaultValue);
    }

    public String getField(String name)
    {
        return prop.getProperty(name);
    }
    
    public int getFieldAsInt(String name, int defaultValue)
    {
        return Integer.parseInt(getField_(name, defaultValue));
    }

    public int getFieldAsInt(String name)
    {
        return Integer.parseInt(getField(name));
    }
    
    public double getFieldAsDouble(String name, double defaultValue)
    {
        return Double.parseDouble(getField_(name, defaultValue));
    }

    public double getFieldAsDouble(String name)
    {
        return Double.parseDouble(getField(name));
    }
    
    public boolean getFieldAsInt(String name, boolean defaultValue)
    {
        return Boolean.parseBoolean(getField_(name, defaultValue));
    }

    public boolean getFieldAsBool(String name)
    {
        return Boolean.parseBoolean(getField(name));
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    public String getFileName()
    {
        return this.fileName;
    }
}
