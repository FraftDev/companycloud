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

    public static void expandAllNodes(JTree tree) {
        int j = tree.getRowCount();
        int i = 0;
        while(i < j) {
            tree.expandRow(i);
            i += 1;
            j = tree.getRowCount();
        }
    }
}
