import DataAccess.Database;

import javax.swing.*;

public class Upload extends JFrame{
    private JPanel mainPanelUpload;
    private JTree tree1;
    private JButton vomPCButton;
    private JButton speichernButton;


    public  Upload(){
        setContentPane(mainPanelUpload);
        setTitle("Upload");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }

    public static void main(String[] args){
        Upload mainPanelUpload =new Upload(); //wenn man upload selbständig öffnen möchte
        mainPanelUpload.setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }
}