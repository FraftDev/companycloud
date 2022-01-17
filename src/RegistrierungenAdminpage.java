import DataAccess.Database;

import javax.swing.*;

public class RegistrierungenAdminpage extends JFrame{
    private JTextArea textArea1;
    private JPanel RegistrierungMainPanel;
    private JButton zulassenButton;
    private JButton ablehnenButton;
    private JScrollBar scrollBar1;


    public  RegistrierungenAdminpage(){
        setContentPane(RegistrierungMainPanel);
        setTitle("Download");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }

    public static void main(String[] args){
        RegistrierungenAdminpage registrierungPanel =new RegistrierungenAdminpage();
    }}
