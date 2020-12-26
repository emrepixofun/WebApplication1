package Test;

/*
 *  Copyright ©2014 Pixofun
 */


import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Alican Şekerefe
 */
public class FileFinder
{
    public static File findFile(String fileName)
    {
        String applicationPath = getApplicationPath();
        return findFile(fileName, getApplicationPath());
    }
    
    public static File findFile(String fileName, String directory)
    {
        File file = _findFile(fileName, directory);
        return file;
    }
    
    private static File _findFile(String fileName, String directory)
    {
        File file = null;
        
        try
        {
            ArrayList<File> innerDirs = new ArrayList<File>();
            File[] files = new File(directory).listFiles();
            
            if(files!=null)
            {
                for(int i=0;i<files.length;i++)
                {
                    File target = files[i];
                    if(target.isDirectory())
                        innerDirs.add(target);
                    else if(target.getName().equals(fileName))
                        file = target;
                }

                while(file == null && !innerDirs.isEmpty())
                {
                    File dir = innerDirs.get(0);
                    innerDirs.remove(0);
                    file = findFile(fileName, dir.getPath());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return file;
    }
    
    public static String getApplicationPath()
    {
        String path = ".";
        
        try
        {
            path = ClassLoader.getSystemClassLoader().getResource("").getPath().replace("classes/","").replace("%20"," ");
            path = path.substring(0, path.indexOf("WEB-INF")+7);
        }
        catch(Exception e)
        {
            String className = (FileFinder.class.getPackage().getName()+"").replace(".", "/")+"/"+FileFinder.class.getSimpleName()+".class";
            path = FileFinder.class.getClassLoader().getResource(className).toString();

            if(path.startsWith("jar:file:"))
                path = path.replace("jar:file:/","");
            else
                path = path.replace("file:/","");

            path = path.replace("%20", " ");
            path = path.substring(0, path.indexOf("WEB-INF")+7);
        }
            
        return path;
    }
}
