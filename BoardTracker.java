public class BoardTracker
{
    private int[][] board = new int[8][8];
    //Individual spots in the Board are referred to as "Squares"
    //If a Square has a value of 0, Not targeted + No Ship
    //If a Square has a value of 1, Not targeted + Ship
    //If a Square has a value of 2, Targeted + No Ship
    //If a Square has a value of 3, Targeted + Ship

    public void BoardTracker()
    {
        this.reset();
    }

    //Get board value at location (x,y)
    public int getState(int x, int y)
    {
        return board[x][y];
    }

    //Attack the space at location (x,y)
    public void attackSpace(int x, int y)
    {
        switch(getState(x, y))
        {
            case 0:
                board[x][y] = 2;
                System.out.println("Miss!");
                break;
            case 1:
                board[x][y] = 3;
                System.out.println("Hit!");
                break;
            case 2:
            case 3:
                System.out.println("You already targeted that space!");
                break;
        }
    }

    //Reset the board
    public void reset()
    {
        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                board[x][y] = 0;
            }
        }
    }
}
