import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.awt.Color; //Color

public class TurnTracker extends JLabel
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
        if(turnCount % 2 == 0)
            return true;
        else
            return false;
    }

    public void setTexts()
    {
        this.turnLabel.setText("Current turn:");
        if(this.isPlayerTurn() == true)
            currTurn.setText("Player");
        else
            currTurn.setText("Opponent");
    }

    public void updatePanel(JPanel a, Style style)
    {
        turnLabel.setFont(new Font("Helvetica", Font.PLAIN, 36));
        turnLabel.setForeground(Color.WHITE);

        currTurn.setFont(new Font("Helvetica", Font.PLAIN, 36));
        currTurn.setForeground(Color.WHITE);

        setTexts();

        a.add(turnLabel);
        a.add(currTurn);
    }
}
