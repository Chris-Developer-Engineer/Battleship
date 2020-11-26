import javax.swing.*; //GUI
import java.awt.*; //Font,Dimensions
import java.awt.event.*; //Listeners

public class Rules {
    private final int WIDTH = 650, LENGTH = 600;
    private Style style = new Style();
    private JFrame rulesScreen = style.frame("Settings", WIDTH, LENGTH);
    private ImageIcon rulesBackground;
    private JLabel back;
    private ImageIcon guide;
    private JLabel rules;

    //Image Instantiation
    Icon rtmImage = new ImageIcon(getClass().getResource("MainMenuButton1.png"));

    //Rollover image
    Icon rtmImageRollover = new ImageIcon(getClass().getResource("MainMenuButton2.png"));

    //Button
    private JButton returnToMenu = new JButton(rtmImage);

    public Rules(){
        setScreen();
        rulesScreen.setVisible(true);
    }

    public void setScreen(){
        rulesScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Background set
        rulesBackground = new ImageIcon(getClass().getResource("RulesBG.gif"));
        back = new JLabel(rulesBackground);
        back.setSize(650,600);

        guide = new ImageIcon(getClass().getResource("Guide.png"));
        rules = new JLabel(guide);
        rules.setBounds(35, 0, 600,485);

        rulesScreen.add(back);
        rulesScreen.setLocationRelativeTo(null);
        rulesScreen.getContentPane().setBackground(Color.black);
        rulesScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        returnToMenu.setBounds(400,480,200,50);

        returnToMenu.setRolloverIcon(rtmImageRollover);

        back.add(returnToMenu);
        back.add(rules);


        //Action Listener
        returnToMenu.addActionListener( //Main Menu button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        rulesScreen.dispose();
                    }
                }
        );
    }
}
