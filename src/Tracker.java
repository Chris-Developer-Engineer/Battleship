import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.awt.Color; //Color

public class Tracker extends JLabel
{
    private static int setting;
    private static int fontSize;
    private static JLabel playerLabel = new JLabel();
    private static JLabel oppLabel = new JLabel();
    private static int[] pShipArray = new int[5];
    private static int[] oShipArray = new int[5];
    private final static String[] shipNames = {"Submarine", "Battleship", "Cruiser", "War Machine", "Carrier"};

    private static JLabel pShipLabel0 = new JLabel();
    private static JLabel pShipLabel1 = new JLabel();
    private static JLabel pShipLabel2 = new JLabel();
    private static JLabel pShipLabel3 = new JLabel();
    private static JLabel pShipLabel4 = new JLabel();
    private static JLabel oShipLabel0 = new JLabel();
    private static JLabel oShipLabel1 = new JLabel();
    private static JLabel oShipLabel2 = new JLabel();
    private static JLabel oShipLabel3 = new JLabel();
    private static JLabel oShipLabel4 = new JLabel();

    public static void setLabels(int size)
    {
        setting = size;
        fontSize = 32 - ((size - 7) * 3);

        formatLabels(new Font("Times New Roman", Font.PLAIN, fontSize), Color.WHITE);

        for(int x = 0; x < size - 4; x++)
        {
            pShipArray[x] = 1;
            oShipArray[x] = 1;
        }

        Game.playerShips.add(playerLabel);
        Game.playerShips.add(pShipLabel0);
        Game.playerShips.add(pShipLabel1);
        Game.playerShips.add(pShipLabel2);

        Game.opponentShips.add(oppLabel);
        Game.opponentShips.add(oShipLabel0);
        Game.opponentShips.add(oShipLabel1);
        Game.opponentShips.add(oShipLabel2);

        switch(size)
        {
            case 8:
                Game.playerShips.add(pShipLabel3);
                Game.opponentShips.add(oShipLabel3);
                break;
            case 9:
                Game.playerShips.add(pShipLabel3);
                Game.opponentShips.add(oShipLabel3);
                Game.playerShips.add(pShipLabel4);
                Game.opponentShips.add(oShipLabel4);
                break;
        }

        // updateLabels(1, 3);
    }

    public static void updateLabels(int whichLabel, String ship)
    {
        int shipID = 0;

        for(String str : shipNames)
        {
            if(ship.equals(str))
                break;
            else
                shipID++;
        }

        //if whichLabel = 0, update player label
        if(whichLabel == 0)
        {
            //destroyed ship [shipID]
            pShipArray[shipID] = 0;
            switch(shipID)
            {
                case 0:
                    pShipLabel0.setText(shipNames[0] + ": Destroyed");
                    pShipLabel0.setForeground(Color.RED);
                    break;
                case 1:
                    pShipLabel1.setText(shipNames[1] + ": Destroyed");
                    pShipLabel1.setForeground(Color.RED);
                    break;
                case 2:
                    pShipLabel2.setText(shipNames[2] + ": Destroyed");
                    pShipLabel2.setForeground(Color.RED);
                    break;
                case 3:
                    pShipLabel3.setText(shipNames[3] + ": Destroyed");
                    pShipLabel3.setForeground(Color.RED);
                    break;
                case 4:
                    pShipLabel4.setText(shipNames[4] + ": Destroyed");
                    pShipLabel4.setForeground(Color.RED);
                    break;
            }
        }
        //else whichLabel = 1, update opponent label
        else
        {
            //destroyed ship [shipID]
            oShipArray[shipID] = 0;
            switch(shipID)
            {
                case 0:
                    oShipLabel0.setText(shipNames[0] + ": Destroyed");
                    oShipLabel0.setForeground(Color.RED);
                    break;
                case 1:
                    oShipLabel1.setText(shipNames[1] + ": Destroyed");
                    oShipLabel1.setForeground(Color.RED);
                    break;
                case 2:
                    oShipLabel2.setText(shipNames[2] + ": Destroyed");
                    oShipLabel2.setForeground(Color.RED);
                    break;
                case 3:
                    oShipLabel3.setText(shipNames[3] + ": Destroyed");
                    oShipLabel3.setForeground(Color.RED);
                    break;
                case 4:
                    oShipLabel4.setText(shipNames[4] + ": Destroyed");
                    oShipLabel4.setForeground(Color.RED);
                    break;
            }
        }
    }

    public static void clearPanel()
    {
        Game.playerShips.removeAll();
        Game.opponentShips.removeAll();
    }

    public static void formatLabels(Font font, Color color)
    {
        playerLabel.setFont(font);
        playerLabel.setForeground(Color.BLUE);
        playerLabel.setText("Player Ships:");

        oppLabel.setFont(font);
        oppLabel.setForeground(Color.MAGENTA);
        oppLabel.setText("Opponent Ships:");

        pShipLabel0.setFont(font);
        pShipLabel0.setForeground(color);
        pShipLabel0.setText(shipNames[0] + ": Alive");

        pShipLabel1.setFont(font);
        pShipLabel1.setForeground(color);
        pShipLabel1.setText(shipNames[1] + ": Alive");

        pShipLabel2.setFont(font);
        pShipLabel2.setForeground(color);
        pShipLabel2.setText(shipNames[2] + ": Alive");

        pShipLabel3.setFont(font);
        pShipLabel3.setForeground(color);
        pShipLabel3.setText(shipNames[3] + ": Alive");

        pShipLabel4.setFont(font);
        pShipLabel4.setForeground(color);
        pShipLabel4.setText(shipNames[4] + ": Alive");

        oShipLabel0.setFont(font);
        oShipLabel0.setForeground(color);
        oShipLabel0.setText(shipNames[0] + ": Alive");

        oShipLabel1.setFont(font);
        oShipLabel1.setForeground(color);
        oShipLabel1.setText(shipNames[1] + ": Alive");

        oShipLabel2.setFont(font);
        oShipLabel2.setForeground(color);
        oShipLabel2.setText(shipNames[2] + ": Alive");

        oShipLabel3.setFont(font);
        oShipLabel3.setForeground(color);
        oShipLabel3.setText(shipNames[3] + ": Alive");

        oShipLabel4.setFont(font);
        oShipLabel4.setForeground(color);
        oShipLabel4.setText(shipNames[4] + ": Alive");
    }
}
