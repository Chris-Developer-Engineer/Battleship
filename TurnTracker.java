public class TurnTracker
{
    private int turnCount;
    //If turnCount == -1, the game has not been started yet.
    //If turnCount == 0, player is choosing ships.
    //If turnCount > 0 && % 2 = 1, it is the player's turn.
    //If turnCount > 0 && % 2 = 0, it is the opponent's turn.

    //TurnTracker constructor
    public TurnTracker()
    {
        this.turnCount = -1;
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
}