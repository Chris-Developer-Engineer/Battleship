import javax.swing.*; //GUI
import java.awt.*; //Font,Dimensions
import java.awt.event.*; //Listeners

public class Settings {
    JFrame settingsScreen = new JFrame("Settings");

    //Button
    JButton save = new JButton("Save");
    JButton volonoff = new JButton("ON/OFF");
    JButton volup = new JButton("Vol UP");
    JButton voldown = new JButton("Vol DOWN");

    public Settings(){
        setScreen();
        settingsScreen.setVisible(true);
    }

    public void setScreen(){
        settingsScreen.setLocation(490, 190);
        settingsScreen.setLayout(new FlowLayout(FlowLayout.CENTER));
        settingsScreen.setSize(300,400);

        settingsScreen.add(save, BorderLayout.PAGE_END);
        settingsScreen.add(volonoff);
        settingsScreen.add(volup);
        settingsScreen.add(voldown);

        volonoff.setBounds(145,100,10,10);


        //Action Listener
        save.addActionListener( //Main Menu button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        settingsScreen.dispose();
                    }
                }
        );
    }
}
