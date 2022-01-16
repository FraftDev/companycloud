import javax.swing.*;

public class Mainpage extends JFrame {
    private JPanel mainPanel;
    private JButton aktenNrButton;
    private JButton uploadButton;
    private JButton aktualisierenButton;
    private JButton sortierungButton;
    private JButton filternButton;
    private JTree tree1;
    private JButton downloadButton;
    private JButton adminMenuButton;

    public Mainpage(){
        setContentPane(mainPanel);
        setTitle("Mainpage");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        Mainpage mainpage =new Mainpage();
    }
}
