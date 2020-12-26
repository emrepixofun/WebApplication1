/*
*  Copyright ©2014 Pixofun
*/
package Test;

/**
 *
 * @author Alican Şekerefe
 */
public class Settings
{
    private static final String configFileName = "config.cfg";    
    private static Configuration configFile = null;
    
    static
    {
        configFile = new Configuration(FileFinder.getApplicationPath(), configFileName);
    }
    
    public static Configuration getConfigFile()
    {
        return configFile;
    }
}
