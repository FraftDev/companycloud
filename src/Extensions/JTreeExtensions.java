package Extensions;

import javax.swing.*;
import java.io.File;

public class JTreeExtensions {
    public static String GetPath(JTree tree){
        String jTreeVarSelectedPath = "";
        Object[] paths = tree.getSelectionPath().getPath();
        for (int i=0; i<paths.length; i++) {
            jTreeVarSelectedPath += paths[i];
            if (i+1 <paths.length ) {
                jTreeVarSelectedPath += File.separator;
            }
        }

        return jTreeVarSelectedPath;
    }
}
