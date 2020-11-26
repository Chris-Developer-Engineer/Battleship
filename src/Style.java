import javax.swing.*; //GUI
import java.awt.*; //font
import java.awt.event.*; //listeners

public class Style{
    //Frame
    public JFrame frame(String frameName, int width, int height){
        JFrame frame = new JFrame(frameName);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    //Font
    public Font headlineFont(){
        Font headlineFont = new Font("Times New Roman", Font.PLAIN, 24);
        return headlineFont;
    }

    public Font bodyFont(){
        Font bodyFont = new Font("Helvetica", Font.PLAIN, 12);
        return bodyFont;
    }

    public Font buttonFont(){
        Font bodyFont = new Font("Serif Bold Italic", Font.PLAIN, 12);
        return bodyFont;
    }

    //Buttons
    public JButton button(String name){
        JButton button = new JButton(name);
        return button;
    }

    //Labels
    public JLabel label(String label){
        JLabel name = new JLabel(label);
        return name;
    }

    //Menu
    public JMenu menu(String menuName){
        JMenu menu = new JMenu(menuName);
        return menu;
    }

    public JMenuItem menuItem(String menuItemName){
        JMenuItem menuItem = new JMenuItem(menuItemName);
        return menuItem;
    }

    //Listener Exit
    public void exitListener(JMenuItem item){
        item.addActionListener( //parenthesis not curly braces
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                } //assigns the exit menu item to exit the program
        );
    }
}
