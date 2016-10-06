package myRider;
// ---
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class MyFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory()) return true;

        String nameFile = f.getName().toLowerCase();
        if (nameFile.indexOf(".txt") != -1)
            return true;
        else return false;
    }

    @Override
    public String getDescription()
    {
        return "TXT файлы";
    }

}
