package Extensions;

import java.io.File;
import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * To handle all FileTree related matters
 */
public class FileTreeModel implements TreeModel {
    private final ArrayList<TreeModelListener>  mListeners  = new ArrayList<>();
    private final MyFile                        mFile;

    public FileTreeModel(final MyFile pFile) {
        mFile = pFile;
    }
    @Override public Object getRoot() {
        return mFile;
    }
    @Override public Object getChild(final Object pParent, final int pIndex) {
        return ((MyFile) pParent).listFiles()[pIndex];
    }

    /**
     * Gets the number of files in a directory
     *
     * @param pParent Path to the directory
     * @return number on files
     */
    @Override public int getChildCount(final Object pParent) {
        return ((MyFile) pParent).listFiles().length;
    }

    /**
     * Determines whether the curent file is a leaf to a node
     *
     * @param pNode path to the node
     * @return whether the file is a leaf to the given node
     */
    @Override public boolean isLeaf(final Object pNode) {
        return !((MyFile) pNode).isDirectory();
    }

    /**
     * renames a file if is changed
     *
     * @param pPath old path
     * @param pNewValue new path
     */
    @Override public void valueForPathChanged(final TreePath pPath, final Object pNewValue) {
        final MyFile oldTmp = (MyFile) pPath.getLastPathComponent();
        final File oldFile = oldTmp.getFile();
        final String newName = (String) pNewValue;
        final File newFile = new File(oldFile.getParentFile(), newName);
        oldFile.renameTo(newFile);
        System.out.println("Renamed '" + oldFile + "' to '" + newFile + "'.");
        reload();
    }

    /**
     * Gets the index of a child from a parent
     *
     * @param pParent path to the parent
     * @param pChild path to the child
     * @return index of the given child in the path
     */
    @Override public int getIndexOfChild(final Object pParent, final Object pChild) {
        final MyFile[] files = ((MyFile) pParent).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i] == pChild) return i;
        }
        return -1;
    }
    @Override public void addTreeModelListener(final TreeModelListener pL) {
        mListeners.add(pL);
    }
    @Override public void removeTreeModelListener(final TreeModelListener pL) {
        mListeners.remove(pL);
    }

    /**
     * reloads the object
     */
    public void reload() {
        // Need to duplicate the code because the root can formally be
        // no an instance of the TreeNode.
        final int n = getChildCount(getRoot());
        final int[] childIdx = new int[n];
        final Object[] children = new Object[n];

        for (int i = 0; i < n; i++) {
            childIdx[i] = i;
            children[i] = getChild(getRoot(), i);
        }

        fireTreeStructureChanged(this, new Object[] { getRoot() }, childIdx, children);
    }

    /**
     * Changes the structure of the file tree
     *
     * @param source original object
     * @param path object list of paths
     * @param childIndices Indices of the children
     * @param children Object list of the children
     */
    protected void fireTreeStructureChanged(final Object source, final Object[] path, final int[] childIndices, final Object[] children) {
        final TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (final TreeModelListener l : mListeners) {
            l.treeStructureChanged(event);
        }
    }
}
