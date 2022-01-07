import javax.swing.*;

public class Adminpage extends JFrame{
    private JPanel AdminpagePanel;
    private JPanel LogsPanel;
    private JScrollPane LogsContent;
    private JPanel SettingsStatsPanel;
    private JPanel StatsPanel;
    private JPanel SettingsPanel;
    private JButton freeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton kostenpflichtigeVersionButton;
    private JLabel blacklist;
    private JLabel Whitelist;
    private JProgressBar TotalBar;
    private JProgressBar IndividualBar;
    private JProgressBar DepartmentBar;
    private JLabel TotalMainLabel;
    private JLabel IndividualMainLabel;
    private JLabel DepartmentMainLabel;
    private JLabel TotalStatusLabel;
    private JLabel IndividualStatusLabel;
    private JLabel DepartmentStatusLabel;


    public  Adminpage(){
        setContentPane(AdminpagePanel);
        setTitle("Adminpage");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        Adminpage adminpagePanel =new Adminpage();
    }

}
