import DataAccess.Database;

import javax.swing.*;

public class Abteilung2Adminpage extends JFrame{
    private JPanel Abteilung2Mainpanel;
    private JTextField NameAbteilung2;
    private JButton abteilung2LöschenButton;
    private JButton änderungen2SpeichernButton;


    public  Abteilung2Adminpage(){
        setContentPane(Abteilung2Mainpanel);
        setTitle("Download");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }

    public static void main(String[] args){
        Abteilung2Adminpage abeilungs2Panel =new Abteilung2Adminpage();
    }

}
