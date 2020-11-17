import java.util.*;

public class BattleshipAI{

    private static int column;
    private static int row;
    private static Random rand = new Random();

    public static void AI(int gridSize){

        int[][] grid = new int[gridSize][gridSize];
        int turnCount = -1;

        boolean turn = true;
        while(turn){ // it is AI's turn

    /*
      grid[][] : 0 -> no ship and space has not been attacked yet
                 1 -> there is a ship but space has not been attacked yet
                 2 -> there is a ship and space has already been attacked
                 3 -> no ship and space has already been attacked
                 4 -> space of ship that has already been destroyed
   */

            if(grid[row][column] == 2){

            adjSpace(grid, row, column);

            }// end if statement

            //randomly select a space
            row = rand.nextInt(gridSize);
            column = rand.nextInt(gridSize);

            if(grid[row][column] == 3 || grid[row][column] == 4) // find another random space if space has already been attacked
                continue;

            //attack space then move on to player turn
            if(grid[row][column] == 0){
                grid[row][column] = 3;
                //change color of space(attacked but no ship)
                break;
            }

            if(grid[row][column] == 1){
                grid[row][column] = 2;
                // change color of space(successful attack on ship)
                break;
            }

        }


    }

    private static void adjSpace(int[][] grid, int row, int column){

        while((grid[row + 1][column] == 0 || grid[row + 1][column] == 1) || (grid[row - 1][column] == 0 || grid[row - 1][column] == 1) || (grid[row][column + 1] == 0 || grid[row][column + 1] == 1) || (grid[row][column - 1] == 0 || grid[row][column - 1] == 1)){ // while(there are still unmarked spaces next to a hit)

            //**if whole ship has been destroyed then break loop

            int space = rand.nextInt(4); // attack a space next to the hit

            switch (space){

                case 0:
                    if(grid[row + 1][column] == 1){ // case 1
                        row = row + 1;
                        grid[row][column] = 2;
                        //** change color of space(successful attack on ship)
                        // move onto player's turn
                        return;
                    }

                    if(grid[row + 1][column] == 0){
                        grid[row + 1][column] = 3;
                        //** change color of space(attacked but no ship)
                        // move onto player's turn
                        return;
                    }

                case 1:
                    if(grid[row - 1][column] == 1){ // case 2
                        row = row - 1;
                        grid[row][column] = 2;
                        //** change color of space(successful attack on ship)
                        // move onto player's turn
                        return;
                    }

                    if(grid[row - 1][column] == 0){
                        grid[row - 1][column] = 3;
                        //** change color of space(attacked but no ship)
                        // move onto player's turn
                        return;
                    }

                case 2:
                    if(grid[row][column + 1] == 1){ // case 3
                        column = column + 1;
                        grid[row][column] = 2;
                        //** change color of space(successful attack on ship)
                        // move onto player's turn
                        return;
                    }

                    if(grid[row][column + 1] == 0){
                        grid[row][column + 1] = 3;
                        //** change color of space(attacked but no ship)
                        // move onto player's turn
                        return;
                    }

                case 3:
                    if(grid[row][column - 1] == 1){ // case 4
                        column = column - 1;
                        grid[row][column] = 2;
                        //** change color of space(successful attack on ship)
                        // move onto player's turn
                        return;
                    }

                    if(grid[row][column - 1] == 0){
                        grid[row][column - 1] = 3;
                        //** change color of space(attacked but no ship)
                        // move onto player's turn
                        return;
                    }

            }//end switch

        }

    }//end method adjSpace()


}