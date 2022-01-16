import DataAccess.Database;

import javax.swing.*;

public class Registrierung extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel MainRegistrierungPanel;
    private JButton registrierenButton;

    public Registrierung(){
        setContentPane(MainRegistrierungPanel);
        setTitle("Registrierung");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }

    public static void main(String[] args){
        Registrierung registrierungPanel =new Registrierung();
    }
}
