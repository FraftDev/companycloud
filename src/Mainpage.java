import DataAccess.Database;
import DataAccess.Globals;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;

import Extensions.*;

public class Mainpage extends JFrame {
    private JPanel mainPanel;
    private JButton uploadButton;
    private JButton aktualisierenButton;
    private JButton renameButton;
    private JButton löschenButton;
    private JTree DirectoryTree;
    private JButton downloadButton;
    private JButton adminMenuButton;
    private JTextField aktenNrTextField;
    private JButton ordnerHinzufügenButton;
    private JLabel DateiName;
    private JList DateiInfo;
    private JLabel textPane1;
    private TreeModel treeModelView;
    private DefaultListModel<String> fileInfoModel = new DefaultListModel<>();


    public Mainpage(){
        setContentPane(mainPanel);
        setTitle("CompanyCloud | Mainpage");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        updateFileTree();

        uploadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == uploadButton) {
                    Upload mainPanelUpload = new Upload();
                }
            }
        });
        renameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == renameButton ) {
                    Rename renamePanel =new Rename();
                }
            }
        });

        adminMenuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == adminMenuButton && Globals.currentUser.isAdmin) {
                    Adminpage adminpagePanel =new Adminpage();
                }
            }
        });

        aktualisierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFileTree();
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPath = JTreeExtensions.GetPath(DirectoryTree);

                if(!selectedPath.contains("."))
                {
                    JOptionPane.showMessageDialog(mainPanel, "Bitte wählen sie eine Datei aus.");
                    return;
                }

                JFileChooser fileSaver = new JFileChooser();

                if(fileSaver.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
                    return;

                Path copyToPath = Path.of(fileSaver.getSelectedFile().getPath());
                Path copyFromPath =  Path.of(Database.SERVER_PATH + Globals.currentUser.company + "\\" + selectedPath);

                try {
                    Files.copy(copyFromPath, copyToPath, StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(mainPanel, "Ihre Datei wurde erfolgreich heruntergeladen.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Es trat ein Fehler auf, bitte kontaktieren Sie ihren Administrator, wenn das Problem vermehrt auftritt.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ordnerHinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ordnerHinzufügenButton ) {
                    OrdnerFenster ordnerFenster = new OrdnerFenster();
                }
                if(DirectoryTree.isSelectionEmpty())
                    return;

                String selectedPath = JTreeExtensions.GetPath(DirectoryTree);

                if(selectedPath.contains("."))
                {
                    JOptionPane.showMessageDialog(mainPanel, "Bitte wählen sie einen Ordner aus.");
                    return;
                }

                OrdnerFenster ordnerFenster = new OrdnerFenster();

                while(ordnerFenster.publicOrdnerName == null) { }

                new File(Database.SERVER_PATH + Globals.currentUser.company + "\\" + selectedPath + "\\" + ordnerFenster.publicOrdnerName + "\\").mkdirs(); //implement with name here
            }
        });

        DirectoryTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if(DirectoryTree.isSelectionEmpty())
                    return;

                String selectedPath = JTreeExtensions.GetPath(DirectoryTree);

                if(!selectedPath.contains("."))
                {
                    fileInfoModel = new DefaultListModel<>();
                    DateiInfo.setModel(fileInfoModel);
                    DateiName.setText("Name der Datei");
                    return;
                }

                fileInfoModel = new DefaultListModel<>();

                Path fullSelectedPath = Path.of(Database.SERVER_PATH + Globals.currentUser.company + "\\" + selectedPath);

                String fileName =  Path.of(selectedPath).getFileName().toString();
                long fileSize = 0;
                FileTime fileLastModified = null;

                try {
                    fileSize = Files.size(fullSelectedPath);
                    fileLastModified = Files.getLastModifiedTime(fullSelectedPath);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                DateiName.setText(fileName);

                fileInfoModel.addElement("Dateiname: " + fileName);
                fileInfoModel.addElement("Dateigröße: " + fileSize / 1000 + "kb");
                fileInfoModel.addElement("Letzes Mal bearbeitet: " + fileLastModified.toString());

                DateiInfo.setModel(fileInfoModel);
            }
        });
        löschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DirectoryTree.isSelectionEmpty())
                    return;

                String selectedPath = JTreeExtensions.GetPath(DirectoryTree);

                if(!selectedPath.contains("."))
                    return;

                if(JOptionPane.showConfirmDialog(mainPanel, "Wollen Sie die Datei wirklich löschen?.", "Sind Sie sich sicher?", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
                    return;

                try {
                    Files.delete(Path.of(Database.SERVER_PATH + Globals.currentUser.company + "\\" + selectedPath));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args){
        if(Globals.currentUser == null || Globals.currentUser.verified == 0)
        {
            JFrame frame = new LoginFrame("Company Cloud | Login");
            frame.setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setSize(400, 200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
        else
        {
            Mainpage mainpage = new Mainpage();
        }
    }

    public void updateFileTree(){
        File file = new File(Database.SERVER_PATH + Globals.currentUser.company + "\\" + Globals.currentUser.department);
        MyFile myFile = new MyFile(file);
        treeModelView = new FileTreeModel(myFile);
        DirectoryTree.setModel(treeModelView);
    }
}
