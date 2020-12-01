import java.util.*;

public class BattleshipAI{

    private static int column;
    private static int row;
    private static Random rand = new Random();
    private static boolean turn;
    private static int gridSize = 9;
    private static int destroyedCount = 0;
    private static int numOfShips;
    private static boolean allDestroyed = false;

    //keep track of hits in order to check if a ship has been destroyed
    private static int hitRow1;
    private static int hitRow2;
    private static int hitRow3;
    private static int hitRow4;
    private static int hitRow5;
    private static int hitRow6;
    private static int hitColumn1;
    private static int hitColumn2;
    private static int hitColumn3;
    private static int hitColumn4;
    private static int hitColumn5;
    private static int hitColumn6;

    private static int successfulHitCount = 0;

    public static void main(String[] args){
        int [][]test = new int[9][9];

        test[0][8] = 1;
        test[1][8] = 1;
        test[2][8] = 1;
        test[3][8] = 1;
        test[4][8] = 1;

        test[1][0] = 1;
        test[1][1] = 1;
        test[1][2] = 1;
        test[1][3] = 1;

        test[7][4] = 1;
        test[7][5] = 1;

        test[4][4] = 1;
        test[4][5] = 1;
        test[4][6] = 1;

        test[3][2] = 1;
        test[4][2] = 1;
        test[5][2] = 1;
        test[6][2] = 1;
        test[7][2] = 1;
        test[8][2] = 1;

        while(!allDestroyed){
            AI(test, gridSize);
        }

        System.out.println("Final SuccessfulHitCount: " + successfulHitCount);
        System.out.println("Final Grid["+ row + "]["+column+"] = " + test[row][column]);
    }

    public static void AI(int[][] grid, int gridSize){

        if(gridSize == 7)
            numOfShips = 3;

        if(gridSize == 8)
            numOfShips = 4;

        if(gridSize == 9)
            numOfShips = 5;

        turn = true;
        int turnCount = -1;

        while(turn){ // it is AI's turn

    /*
      grid[][] : 0 -> no ship and space has not been attacked yet
                 1 -> there is a ship but space has not been attacked yet
                 2 -> there is a ship and space has already been attacked
                 3 -> no ship and space has already been attacked
                 4 -> space of ship that has already been destroyed
   */

            if(grid[row][column] == 2){
                System.out.println("Currently at Grid["+ row + "]["+ column +"] = " + grid[row][column]);
                adjSpace(grid, row, column);

                if(checkDestroyedShips()){
                    System.out.println("Final destroyedCount: " + destroyedCount);
                    System.out.println("Final numOfShips: " + numOfShips);
                    destroyedCount = 0;
                    allDestroyed = true;
                    return;
                }

                if(!turn){
                    System.out.println("Move on to player's turn");
                    return;
                }//end inner if statement

            }//end outer if statement

            //randomly select a space
            row = rand.nextInt(grid.length);
            column = rand.nextInt(grid[0].length);

            if(grid[row][column] == 3 || grid[row][column] == 4){ // find another random space if space has already been attacked
                continue;
            }

            //attack space then move on to player turn
            if(grid[row][column] == 0){
                grid[row][column] = 3;
                //change color of space(attacked but no ship)
                continue; //return;
            }

            if(grid[row][column] == 1){
                System.out.println("Hit at Grid["+ row + "]["+column+"] = " + grid[row][column]);
                successfulHitCount++;
                assignHit();
                grid[row][column] = 2;
                System.out.println("Changed Grid["+ row + "]["+column+"] to " + grid[row][column]);
                // change color of space(successful attack on ship)
                System.out.println("Move on to player's turn");
                continue; //return
            }

        }


    } //end AI

    private static void adjSpace(int[][] grid, int srow, int scolumn){
        System.out.println("Checking adjacent spaces at Grid["+ srow + "]["+scolumn+"] = " + grid[srow][scolumn]);

        while(turn){

            int space = rand.nextInt(4); // randomly choose a space next to a hit to attack

            switch (space){

                case 0:
                    if(row == (gridSize - 1) || grid[srow + 1][scolumn] == 2 || grid[srow + 1][scolumn] == 3 || grid[srow + 1][scolumn] == 4){
                        continue;
                    }

                    else{
                        if(grid[srow + 1][scolumn] == 1){ // case 1
                            successfulHitCount++;
                            row = row + 1;
                            grid[row][column] = 2;
                            assignHit();
                            //** change color of space(successful attack on ship)
                            System.out.println("Hit at Grid["+ row + "]["+column+"]");
                            System.out.println("Changed Grid["+ row + "]["+column+"] to " + grid[row][column]);
                            turn = false;

                            if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }

                            break; // move onto player's turn
                        }

                        if(grid[srow + 1][scolumn] == 0){
                            grid[row + 1][column] = 3;
                            //** change color of space(attacked but no ship)
                            System.out.println("No ship in space below: Grid["+ (row+1) + "]["+column+"]");
                            System.out.println("Changed Grid["+ (row+1) + "]["+column+"] to " + grid[row+1][column]);
                            turn = false;
                            break; // move onto player's turn
                        }
                    }// end else

                case 1:
                    if(row == 0 || grid[row - 1][column] == 2 || grid[row - 1][column] == 3 || grid[row - 1][column] == 4){
                        continue;
                    }

                    else{
                        if(grid[srow - 1][scolumn] == 1){ // case 2
                            successfulHitCount++;
                            row = row - 1;
                            grid[row][column] = 2;
                            assignHit();
                            //** change color of space(successful attack on ship)
                            System.out.println("Hit at Grid["+ row + "]["+column+"]");
                            System.out.println("Changed Grid["+ row + "]["+column+"] to " + grid[row][column]);
                            turn = false;

                            if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }

                            break; // move onto player's turn
                        }

                        if(grid[srow - 1][scolumn] == 0){
                            grid[row - 1][column] = 3;
                            //** change color of space(attacked but no ship)
                            // move onto player's turn
                            System.out.println("No ship in space above: Grid["+ (row-1) + "]["+column+"]");
                            System.out.println("Changed Grid["+ (row-1) + "]["+column+"] to " + grid[row-1][column]);
                            turn = false;
                            break; // move onto player's turn
                        }
                    }// end else

                case 2:
                    if(scolumn == (gridSize - 1) || grid[srow][scolumn + 1] == 2 || grid[srow][scolumn + 1] == 3 || grid[srow][scolumn + 1] == 4){
                        continue;
                    }

                    else{
                        if(grid[srow][scolumn + 1] == 1){ // case 3
                            successfulHitCount++;
                            column = column + 1;
                            grid[row][column] = 2;
                            assignHit();
                            //** change color of space(successful attack on ship)
                            System.out.println("change 3");
                            System.out.println("Hit at Grid["+ row + "]["+column+"]");
                            System.out.println("Changed Grid["+ row + "]["+column+"] to " + grid[row][column]);
                            turn = false;

                            if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }

                            break; // move onto player's turn
                        }

                        if(grid[srow][scolumn + 1] == 0){
                            grid[row][column + 1] = 3;
                            //** change color of space(attacked but no ship)
                            // move onto player's turn
                            System.out.println("No ship in space to the right: Grid["+ row + "]["+(column+1)+"]");
                            System.out.println("Changed Grid["+ row + "]["+(column+1)+"] to " + grid[row][column+1]);
                            turn = false;
                            break; // move onto player's turn
                        }
                    }// end else

                case 3:
                    if(scolumn == 0 || grid[srow][scolumn - 1] == 2 || grid[srow][scolumn - 1] == 3 || grid[srow][scolumn - 1] == 4){
                        continue;
                    }

                    else{
                        if(grid[srow][scolumn - 1] == 1){ // case 4
                            successfulHitCount++;
                            column = column - 1;
                            grid[row][column] = 2;
                            assignHit();
                            //** change color of space(successful attack on ship)
                            System.out.println("Hit at Grid["+ row + "]["+column+"]");
                            System.out.println("Changed Grid["+ row + "]["+column+"] to " + grid[row][column]);
                            turn = false;

                            if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }

                            break; // move onto player's turn
                        }

                        if(grid[srow][scolumn - 1] == 0){
                            grid[row][column - 1] = 3;
                            //** change color of space(attacked but no ship)
                            System.out.println("No ship in space to the left: Grid["+ row + "]["+(column-1)+"]");
                            System.out.println("Changed Grid["+ row + "]["+(column-1)+"] to " + grid[row][column-1]);
                            turn = false;
                            break; // move onto player's turn
                        }
                    }// end else

            }//end switch



        } //end while

    }//end method adjSpace()

    private static boolean checkDestroyedShips(){ //checks if all ships are destroyed

    /*for(int i = 0; i < gridSize; i++){
      for (int j = 0; j < gridSize; j++){

      if(grid[i][j] == 4){

      }

      }

    } */

        if(destroyedCount == numOfShips){
            System.out.println("You Lose");
            System.out.println("All of your ships have been destroyed");
            return true;
        }
        else
            return false;

    }// end checkDestroyedShips

    private static void assignHit(){ //keeps track of hits
        switch(successfulHitCount){

            case 1:hitRow1 = row;
                hitColumn1 = column;

            case 2:hitRow2 = row;
                hitColumn2 = column;

            case 3:hitRow3 = row;
                hitColumn3 = column;

            case 4:hitRow4 = row;
                hitColumn4 = column;

            case 5:hitRow5 = row;
                hitColumn5 = column;

            case 6:hitRow6 = row;
                hitColumn6 = column;

        }//end switch

    }//end assignHit()

    public static boolean checkTopSpace(int[][] tgrid, int trow, int tcolumn){ // returns true if there is a ship in the top space

        if(trow == 0)
            return false;

        if(tgrid[trow - 1][tcolumn] == 1)
            return true;
        else
            return false;

    }//end checkTopSpace()

    public static boolean checkBottomSpace(int[][] bgrid, int brow, int bcolumn){ // returns true if there is a ship in the bottom space

        if(brow == (gridSize - 1))
            return false;

        if(bgrid[brow + 1][bcolumn] == 1)
            return true;
        else
            return false;

    }//end checkBottomSpace()

    public static boolean checkLeftSpace(int[][] lgrid, int lrow, int lcolumn){ // returns true if there is a ship in the left space

        if(lcolumn == 0)
            return false;

        if(lgrid[lrow][lcolumn - 1] == 1)
            return true;
        else
            return false;

    }//end checkLeftSpace()

    public static boolean checkRightSpace(int[][] rgrid, int rrow, int rcolumn){ // returns true if there is a ship in the right space

        if(rcolumn == (gridSize - 1))
            return false;

        if(rgrid[rrow][rcolumn + 1] == 1)
            return true;
        else
            return false;

    }//end checkRightSpace()

    public static void isDestroyed(int[][] grid){ // checks for any hidden ships around all previous hits to determine if a ship is destroyed

        switch(successfulHitCount){

            case 2: if((checkTopSpace(grid, hitRow1, hitColumn1) == false) & (checkBottomSpace(grid, hitRow1, hitColumn1) == false) & (checkLeftSpace(grid, hitRow1, hitColumn1) == false) & (checkRightSpace(grid, hitRow1, hitColumn1) == false)
                    & (checkTopSpace(grid, hitRow2, hitColumn2) == false) & (checkBottomSpace(grid, hitRow2, hitColumn2) == false) & (checkLeftSpace(grid, hitRow2, hitColumn2) == false) & (checkRightSpace(grid, hitRow2, hitColumn2) == false)){

                grid[hitRow1][hitColumn1] = 4;
                grid[hitRow2][hitColumn2] = 4;

                destroyedCount++;
                successfulHitCount = 0;
                System.out.println("Success: Submarine destroyed");
                System.out.println("Grid["+hitRow1+"]["+hitColumn1+"] and Grid["+hitRow2+"]["+hitColumn2+"] set to 4");
                turn = false;
                return;
            }

            else{
                row = hitRow1;
                column = hitColumn1;
                adjSpace(grid, row, column);
                turn = false;
                return;
            }

            case 3: if(checkTopSpace(grid, hitRow1, hitColumn1) == false & checkBottomSpace(grid, hitRow1, hitColumn1) == false & checkLeftSpace(grid, hitRow1, hitColumn1) == false & checkRightSpace(grid, hitRow1, hitColumn1) == false
                    & checkTopSpace(grid, hitRow2, hitColumn2) == false & checkBottomSpace(grid, hitRow2, hitColumn2) == false & checkLeftSpace(grid, hitRow2, hitColumn2) == false & checkRightSpace(grid, hitRow2, hitColumn2) == false
                    & checkTopSpace(grid, hitRow3, hitColumn3) == false & checkBottomSpace(grid, hitRow3, hitColumn3) == false & checkLeftSpace(grid, hitRow3, hitColumn3) == false & checkRightSpace(grid, hitRow3, hitColumn3) == false){

                grid[hitRow1][hitColumn1] = 4;
                grid[hitRow2][hitColumn2] = 4;
                grid[hitRow3][hitColumn3] = 4;

                destroyedCount++;
                successfulHitCount = 0;
                System.out.println("Success: Battleship destroyed");
                System.out.println("Grid["+hitRow1+"]["+hitColumn1+"], Grid["+hitRow2+"]["+hitColumn2+"] and Grid["+hitRow3+"]["+hitColumn3+"] set to 4");
                turn = false;
                return;
            }

            else{
                row = hitRow1;
                column = hitColumn1;
                adjSpace(grid, row, column);
                turn = false;
                return;
            }

            case 4: if(checkTopSpace(grid, hitRow1, hitColumn1) == false & checkBottomSpace(grid, hitRow1, hitColumn1) == false & checkLeftSpace(grid, hitRow1, hitColumn1) == false & checkRightSpace(grid, hitRow1, hitColumn1) == false
                    & checkTopSpace(grid, hitRow2, hitColumn2) == false & checkBottomSpace(grid, hitRow2, hitColumn2) == false & checkLeftSpace(grid, hitRow2, hitColumn2) == false & checkRightSpace(grid, hitRow2, hitColumn2) == false
                    & checkTopSpace(grid, hitRow3, hitColumn3) == false & checkBottomSpace(grid, hitRow3, hitColumn3) == false & checkLeftSpace(grid, hitRow3, hitColumn3) == false & checkRightSpace(grid, hitRow3, hitColumn3) == false
                    & checkTopSpace(grid, hitRow4, hitColumn4) == false & checkBottomSpace(grid, hitRow4, hitColumn4) == false & checkLeftSpace(grid, hitRow4, hitColumn4) == false & checkRightSpace(grid, hitRow4, hitColumn4) == false){

                grid[hitRow1][hitColumn1] = 4;
                grid[hitRow2][hitColumn2] = 4;
                grid[hitRow3][hitColumn3] = 4;
                grid[hitRow4][hitColumn4] = 4;

                destroyedCount++;
                successfulHitCount = 0;
                System.out.println("Success: Cruiser destroyed");
                System.out.println("Grid["+hitRow1+"]["+hitColumn1+"], Grid["+hitRow2+"]["+hitColumn2+"], Grid["+hitRow3+"]["+hitColumn3+"] and Grid["+hitRow4+"]["+hitColumn4+"] set to 4");
                turn = false;
                return;
            }

            else{
                row = hitRow1;
                column = hitColumn1;
                adjSpace(grid, row, column);
                turn = false;
                return;
            }

            case 5: if(checkTopSpace(grid, hitRow1, hitColumn1) == false & checkBottomSpace(grid, hitRow1, hitColumn1) == false & checkLeftSpace(grid, hitRow1, hitColumn1) == false & checkRightSpace(grid, hitRow1, hitColumn1) == false
                    & checkTopSpace(grid, hitRow2, hitColumn2) == false & checkBottomSpace(grid, hitRow2, hitColumn2) == false & checkLeftSpace(grid, hitRow2, hitColumn2) == false & checkRightSpace(grid, hitRow2, hitColumn2) == false
                    & checkTopSpace(grid, hitRow3, hitColumn3) == false & checkBottomSpace(grid, hitRow3, hitColumn3) == false & checkLeftSpace(grid, hitRow3, hitColumn3) == false & checkRightSpace(grid, hitRow3, hitColumn3) == false
                    & checkTopSpace(grid, hitRow4, hitColumn4) == false & checkBottomSpace(grid, hitRow4, hitColumn4) == false & checkLeftSpace(grid, hitRow4, hitColumn4) == false & checkRightSpace(grid, hitRow4, hitColumn4) == false
                    & checkTopSpace(grid, hitRow5, hitColumn5) == false & checkBottomSpace(grid, hitRow5, hitColumn5) == false & checkLeftSpace(grid, hitRow5, hitColumn5) == false & checkRightSpace(grid, hitRow5, hitColumn5) == false){

                grid[hitRow1][hitColumn1] = 4;
                grid[hitRow2][hitColumn2] = 4;
                grid[hitRow3][hitColumn3] = 4;
                grid[hitRow4][hitColumn4] = 4;
                grid[hitRow5][hitColumn5] = 4;

                destroyedCount++;
                successfulHitCount = 0;
                System.out.println("Success: War Machine destroyed");
                System.out.println("Grid["+hitRow1+"]["+hitColumn1+"], Grid["+hitRow2+"]["+hitColumn2+"], Grid["+hitRow3+"]["+hitColumn3+"], Grid["+hitRow4+"]["+hitColumn4+"] and Grid["+hitRow5+"]["+hitColumn5+"] set to 4");
                turn = false;
                return;
            }

            else{
                row = hitRow1;
                column = hitColumn1;
                adjSpace(grid, row, column);
                turn = false;
                return;
            }

            case 6: if(checkTopSpace(grid, hitRow1, hitColumn1) == false & checkBottomSpace(grid, hitRow1, hitColumn1) == false & checkLeftSpace(grid, hitRow1, hitColumn1) == false & checkRightSpace(grid, hitRow1, hitColumn1) == false
                    & checkTopSpace(grid, hitRow2, hitColumn2) == false & checkBottomSpace(grid, hitRow2, hitColumn2) == false & checkLeftSpace(grid, hitRow2, hitColumn2) == false & checkRightSpace(grid, hitRow2, hitColumn2) == false
                    & checkTopSpace(grid, hitRow3, hitColumn3) == false & checkBottomSpace(grid, hitRow3, hitColumn3) == false & checkLeftSpace(grid, hitRow3, hitColumn3) == false & checkRightSpace(grid, hitRow3, hitColumn3) == false
                    & checkTopSpace(grid, hitRow4, hitColumn4) == false & checkBottomSpace(grid, hitRow4, hitColumn4) == false & checkLeftSpace(grid, hitRow4, hitColumn4) == false & checkRightSpace(grid, hitRow4, hitColumn4) == false
                    & checkTopSpace(grid, hitRow5, hitColumn5) == false & checkBottomSpace(grid, hitRow5, hitColumn5) == false & checkLeftSpace(grid, hitRow5, hitColumn5) == false & checkRightSpace(grid, hitRow5, hitColumn5) == false
                    & checkTopSpace(grid, hitRow6, hitColumn6) == false & checkBottomSpace(grid, hitRow6, hitColumn6) == false & checkLeftSpace(grid, hitRow6, hitColumn6) == false & checkRightSpace(grid, hitRow6, hitColumn6) == false){

                grid[hitRow1][hitColumn1] = 4;
                grid[hitRow2][hitColumn2] = 4;
                grid[hitRow3][hitColumn3] = 4;
                grid[hitRow4][hitColumn4] = 4;
                grid[hitRow5][hitColumn5] = 4;
                grid[hitRow6][hitColumn6] = 4;

                destroyedCount++;
                successfulHitCount = 0;
                System.out.println("Success: Carrier destroyed");
                System.out.println("Grid["+hitRow1+"]["+hitColumn1+"], Grid["+hitRow2+"]["+hitColumn2+"], Grid["+hitRow3+"]["+hitColumn3+"], Grid["+hitRow4+"]["+hitColumn4+"], Grid["+hitRow5+"]["+hitColumn5+"] and Grid["+hitRow6+"]["+hitColumn6+"] set to 4");
                turn = false;
                return;
            }

            else{
                row = hitRow1;
                column = hitColumn1;
                adjSpace(grid, row, column);
                turn = false;
                return;
            }

        } //end switch

    }//end isDestroyed()

}//end class