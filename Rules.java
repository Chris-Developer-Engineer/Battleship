import javax.swing.*; //GUI
import java.awt.*; //Font,Dimensions
import java.awt.event.*; //Listeners

public class Rules {
    JFrame settingsScreen = new JFrame("Rules");

    //Button
    JButton save = new JButton("Exit");

    public Rules(){
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
