import DataAccess.Database;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Rename extends JFrame{
    private JPanel renamePanel;
    private JTextField Rename;

    public Rename(){
        setContentPane(renamePanel);
        setTitle("Neuer Ordner");
        setSize(300, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        Rename.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (Rename.getText().equals("")){
                    Rename.setText("Name eingeben");
                }
            }
        });
        Rename.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (Rename.getText().equals("Name eingeben")){
                    Rename.setText("");
                }
            }
        });
    }
    public static void main(String[] args){
        Rename renamePanel =new Rename();
    }
}
