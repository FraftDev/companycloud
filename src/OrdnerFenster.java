import DataAccess.Database;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OrdnerFenster extends JFrame{
    private JPanel ordnerpanel;
    private JTextField ordnerName;

    public OrdnerFenster(){
        setContentPane(ordnerpanel);
        setTitle("Neuer Ordner");
        setSize(300, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        ordnerName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (ordnerName.getText().equals("")){
                    ordnerName.setText("Name des Ordners");
                }
            }
        });
        ordnerName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (ordnerName.getText().equals("Name des Ordners")){
                    ordnerName.setText("");
                }
            }
        });
    }
    public static void main(String[] args){
        OrdnerFenster ordnerFenster =new OrdnerFenster();
    }
    }