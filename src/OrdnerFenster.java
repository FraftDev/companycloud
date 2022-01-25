import DataAccess.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OrdnerFenster extends JFrame {
    private JPanel ordnerpanel;
    private JTextField ordnerName;
    private JButton SubmitButton;
    public String publicOrdnerName;

    public OrdnerFenster() {
        setContentPane(ordnerpanel);
        setTitle("Neuer Ordner");
        setSize(300, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        ordnerName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (ordnerName.getText().equals("")) {
                    ordnerName.setText("Name des Ordners");
                }
            }
        });
        ordnerName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (ordnerName.getText().equals("Name des Ordners")) {
                    ordnerName.setText("");
                }
            }
        });

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publicOrdnerName = ordnerName.getText();
                ordnerpanel.setVisible(false);
                dispose();
            }
        });
    }
}
