import DataAccess.Database;
import DataAccess.Globals;

import javax.swing.*;
import javax.swing.tree.TreeModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

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
        downloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == downloadButton) {
                    Download downloadPanel =new Download();
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
