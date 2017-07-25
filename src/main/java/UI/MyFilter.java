package UI;

import javax.swing.filechooser.FileFilter;
import java.io.File;

class MyFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.getName().endsWith(".xls"))
            return true;
        if (f.isDirectory())
            return true;
        return false;
    }

    @Override
    public String getDescription() {
        return "Excel files";
    }

}