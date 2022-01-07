import DataAccess.Database;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Mainpage extends JFrame {
    private JPanel mainPanel;
    private JButton uploadButton;
    private JButton aktualisierenButton;
    private JButton sortierungButton;
    private JButton filternButton;
    private JTree tree1;
    private JButton downloadButton;
    private JButton adminMenuButton;
    private JTextField aktenNrTextField;

    public Mainpage(){
        setContentPane(mainPanel);
        setTitle("Mainpage");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        uploadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == uploadButton) {
                    Upload mainPanelUpload =new Upload();
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

                if (e.getSource() == adminMenuButton) {
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
    }


    public static void main(String[] args){
        Mainpage mainpage =new Mainpage();//wenn man mainpage selbständig öffnen möchte
    }
}
