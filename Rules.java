import javax.swing.*; //GUI
import java.awt.*; //Font,Dimensions
import java.awt.event.*; //Listeners

public class Rules {
    private final int WIDTH = 800, LENGTH = 600;
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
        //Background set
        rulesBackground = new ImageIcon(getClass().getResource("RulesBG.jpg"));
        back = new JLabel(rulesBackground);
        back.setSize(500,500);

        guide = new ImageIcon(getClass().getResource("Guide.png"));
        rules = new JLabel(guide);
        rules.setBounds(180, -130, 800,800);

        rulesScreen.add(back);
        rulesScreen.setLocationRelativeTo(null);
        rulesScreen.getContentPane().setBackground(Color.black);
        rulesScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        returnToMenu.setBounds(450,520,300,24);

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
