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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        Upload mainPanelUpload =new Upload();
    }
}