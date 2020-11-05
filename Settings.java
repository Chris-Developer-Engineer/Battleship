import javax.swing.*; //GUI
import java.awt.*; //Font,Dimensions
import java.awt.event.*; //Listeners

public class Settings {
    JFrame settingsScreen = new JFrame("Settings");

    //Button
    JButton save = new JButton("Save");

    public Settings(){
        setScreen();
        settingsScreen.setVisible(true);
    }

    public void setScreen(){
        settingsScreen.setLocation(490, 190);
        settingsScreen.setLayout(new FlowLayout(FlowLayout.CENTER));
        settingsScreen.setSize(300,400);

        settingsScreen.add(save, BorderLayout.PAGE_END);


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
