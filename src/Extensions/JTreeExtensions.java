package Extensions;

import javax.swing.*;
import java.io.File;

/**
 * To handle all the Extensions of a FileTree
 */
public class JTreeExtensions {
    /**
     * Gets the path of a given tree
     *
     * @param tree tree object, whose path is to be retrieved
     * @return path to that tree
     */
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

    /**
     * Expands all the nodes of a tree
     *
     * @param tree the tree, whose nodes are to be expanded
     */
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
