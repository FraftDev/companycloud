import DataAccess.Database;

import javax.swing.*;

public class Abteilung1Adminpage extends JFrame{
    private JPanel Abteilung1MainPanel;
    private JTextField textField1;
    private JButton änderungenSpeichernButton;
    private JButton abteilungLöschenButton;


    public  Abteilung1Adminpage(){
        setContentPane(Abteilung1MainPanel);
        setTitle("Download");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }

    public static void main(String[] args){
        Abteilung1Adminpage abeilungs1Panel =new Abteilung1Adminpage();
    }}