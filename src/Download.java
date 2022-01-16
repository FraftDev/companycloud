import DataAccess.Database;

import javax.swing.*;

public class Download extends JFrame{
    private JTree treeDownload;
    private JButton downloadButton;
    private JPanel MainPanelDownload;
    private JLabel DownloadHeader;


    public Download(){
        setContentPane(MainPanelDownload);
        setTitle("Download");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
    }

    public static void main(String[] args){
        Download downloadPanel =new Download();
    }
}