package Extensions;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * To handle file Objects
 *
 */
public class MyFile {
    private final File mFile;

    public MyFile(final File pFile) {
        mFile = pFile;
    }

    /**
     * To determine whether the file is a directory or not (then its prob. a file)
     *
     * @return whether the file is a directory or not
     */
    public boolean isDirectory() {
        return mFile.isDirectory();
    }

    /**
     * Lists all files currently available
     *
     * @return list of file objects
     */
    public MyFile[] listFiles() {
        final File[] files = mFile.listFiles();
        if (files == null) return null;
        if (files.length < 1) return new MyFile[0];

        final MyFile[] ret = new MyFile[files.length];
        for (int i = 0; i < ret.length; i++) {
            final File f = files[i];
            ret[i] = new MyFile(f);
        }
        return ret;
    }

    /**
     * gets a file object
     *
     * @return fileobject
     */
    public File getFile() {
        return mFile;
    }

    /**
     * retrieves a filename as a string
     *
     * @return filename
     */
    @Override public String toString() {
        return mFile.getName();
    }
}
