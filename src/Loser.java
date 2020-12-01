import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loser {
    private final int WIDTH = 650, LENGTH = 600;
    private Style style = new Style();
    private JFrame lossScreen = style.frame("Loss", WIDTH, LENGTH);
    private ImageIcon lossBackground;
    private JLabel back;
    private ImageIcon lossPlaque;
    private JLabel overhead;

    //Images Instantiation
    Icon rtmImage = new ImageIcon(getClass().getResource("MMenu1.png"));

    //Rollover images
    Icon rtmImageRollover = new ImageIcon(getClass().getResource("MMenu2.png"));

    //Buttons
    private JButton returnToMenu = new JButton(rtmImage);

    public Loser(){
        setScreen();
        lossScreen.setVisible(true);
    }

    public void setScreen(){
        //Background set
        lossBackground = new ImageIcon(getClass().getResource("LossBG.gif"));
        back = new JLabel(lossBackground);
        back.setSize(650,600);

        lossPlaque = new ImageIcon(getClass().getResource("lossPlate.png"));
        overhead = new JLabel(lossPlaque);
        overhead.setBounds(025, 10, 600,208);

        lossScreen.add(back);
        lossScreen.setLocationRelativeTo(null);
        lossScreen.getContentPane().setBackground(Color.black);
        lossScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        returnToMenu.setBounds(240,480,170, 75);

        //Rollovers for Buttons
        returnToMenu.setRolloverIcon(rtmImageRollover);

        back.add(returnToMenu);
        back.add(overhead);

        //Action Listener
        returnToMenu.addActionListener( //Main Menu button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        lossScreen.dispose();
                    }
                }
        );
    }
}
