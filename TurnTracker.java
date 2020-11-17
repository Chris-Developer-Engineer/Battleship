import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.awt.Color; //Color

public class TurnTracker
{
    private int turnCount;
    private JLabel turnLabel = new JLabel();
    private JLabel currTurn = new JLabel();

    //If turnCount == 0, the game has not started yet.
    //If turnCount > 0 && % 2 = 1, it is the player's turn.
    //If turnCount > 0 && % 2 = 0, it is the opponent's turn.

    //TurnTracker constructor
    public TurnTracker()
    {
        this.turnCount = 0;
        this.turnLabel.setText("Current turn:");
        this.currTurn.setText("Setting up...");
    }

    //Increment the turnCount
    public void incrementTurn()
    {
        turnCount += 1;
    }

    //Return the turnCount
    public int getTurnCount()
    {
        return turnCount;
    }

    //Reset the turnCount
    public void reset()
    {
        turnCount = 0;
    }

    public boolean isPlayerTurn()
    {
        if(turnCount % 2 == 1)
            return true;
        else
            return false;
    }

    public void updatePanel(JPanel a, Style style)
    {
        turnLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        turnLabel.setForeground(Color.WHITE);

        currTurn.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        currTurn.setForeground(Color.WHITE);

        if(this.isPlayerTurn() == true)
            currTurn.setText("Player");
        else
            currTurn.setText("Opponent");
        a.add(turnLabel);
        a.add(currTurn);
    }
}
