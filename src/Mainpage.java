import DataAccess.Database;
import DataAccess.Globals;

import javax.swing.*;
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

import Extensions.*;

public class Mainpage extends JFrame {
    private JPanel mainPanel;
    private JButton uploadButton;
    private JButton aktualisierenButton;
    private JButton sortierungButton;
    private JButton filternButton;
    private JTree DirectoryTree;
    private JButton downloadButton;
    private JButton adminMenuButton;
    private JTextField aktenNrTextField;
    private JButton ordnerHinzufügenButton;
    private TreeModel treeModelView;

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
        adminMenuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == adminMenuButton && Globals.currentUser.isAdmin) {
                    Adminpage adminpagePanel =new Adminpage();
                }
            }
        });

        aktenNrTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (aktenNrTextField.getText().equals("")){
                    aktenNrTextField.setText("Akten Nr.");
                }
            }
        });
        aktenNrTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (aktenNrTextField.getText().equals("Akten Nr.")){
                    aktenNrTextField.setText("");
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

                if(DirectoryTree.isSelectionEmpty())
                    return;

                String selectedPath = JTreeExtensions.GetPath(DirectoryTree);

                if(selectedPath.contains("."))
                {
                    JOptionPane.showMessageDialog(mainPanel, "Bitte wählen sie einen Ordner aus.");
                    return;
                }

                new File(Database.SERVER_PATH + Globals.currentUser.company + "\\" + selectedPath + "\\directoryName\\").mkdirs(); //implement with name here
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
