import DataAccess.Database;
import DataAccess.Globals;
import Extensions.FileTreeModel;
import Extensions.JTreeExtensions;
import Extensions.MyFile;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Upload extends JFrame{
    private JPanel mainPanelUpload;
    private JTree tree1;
    private JButton vomPCButton;
    private JButton speichernButton;
    private TreeModel treeModelView;


    public  Upload(){
        setContentPane(mainPanelUpload);
        setTitle("Upload");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        updateFileTree();
        vomPCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedPath = JTreeExtensions.GetPath(tree1);

                if(selectedPath.contains("."))
                {
                    JOptionPane.showMessageDialog(mainPanelUpload, "Bitte wählen sie keine Datei aus, sondern einen Ordner.");
                    return;
                }

                JFileChooser fc = new JFileChooser();

                if(fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
                    return;

                Path copyFromPath = Path.of(fc.getSelectedFile().getPath());
                Path copyToPath =  Path.of(Database.SERVER_PATH + Globals.currentUser.company + "\\" + selectedPath + "\\" + fc.getSelectedFile().getName());

                try {
                    Files.copy(copyFromPath, copyToPath, StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(mainPanelUpload, "Ihre Datei wurde erfolgreich hochgeladen.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanelUpload, "Es trat ein Fehler auf, bitte kontaktieren Sie ihren Administrator, wenn das Problem vermehrt auftritt.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }

                updateFileTree();
            }
        });
    }

    public void updateFileTree(){
        File file = new File(Database.SERVER_PATH + Globals.currentUser.company + "\\" + Globals.currentUser.department);
        MyFile myFile = new MyFile(file);
        treeModelView = new FileTreeModel(myFile);
        tree1.setModel(treeModelView);
    }
}