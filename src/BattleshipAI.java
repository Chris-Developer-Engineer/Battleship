import java.util.*;

public class BattleshipAI {

    public static int column;
    public static int row;
    private static Random rand = new Random();
    private static boolean turn;
    private static int gridSize;
    private static int destroyedCount = 0;
    private static int numOfShips;
    private static boolean allDestroyed = false;
    int[][] test = new int[9][9];
    private static int j;
    static boolean doClickSentHit = false;
    static boolean doClickSentMiss = false;
    static boolean doClickSentHitAI = false;
    static boolean doClickSentMissAI = false;
    static boolean clickSwitch = false;
    static boolean changed = false;

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

   /* public BattleshipAI(int[][] multiArray){

        this.test = multiArray;
       /* while(!allDestroyed){
            AI(multiArray, gridSize);
        } */

    // System.out.println("Final SuccessfulHitCount: " + successfulHitCount);
    // System.out.println("Final Grid["+ row + "]["+column+"] = " + multiArray[row][column]);
    //}

    public static void AI(int[][] grid, int gSize) {

       /* if ((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)) {
            System.out.println("No ships around Grid[" + row + "][" + column + "] = " + grid[row][column]);
            System.out.println("successfulHitCount: " + successfulHitCount);
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 2) {
                        row = i;
                        column = j;
                        break;
                    }
                }
            }
            //isDestroyed(grid);
        } */

        if (gSize == 7) {
            numOfShips = 3;
        }

        if (gSize == 8) {
            numOfShips = 4;
        }

        if (gSize == 9) {
            numOfShips = 5;
        }

        gridSize = gSize;

        turn = true;
        int turnCount = -1;

        while (turn) { // it is AI's turn

    /*
      grid[][] : 0 -> no ship and space has not been attacked yet
                 1 -> there is a ship but space has not been attacked yet
                 2 -> there is a ship and space has already been attacked
                 3 -> no ship and space has already been attacked
                 4 -> space of ship that has already been destroyed
   */

            if (grid[row][column] == 2) {
                System.out.println("Currently at Grid[" + row + "][" + column + "] = " + grid[row][column]);
                selectJ(row, column);
                adjSpace(grid, row, column);

                /*if(checkDestroyedShips()){
                    System.out.println("Final destroyedCount: " + destroyedCount);
                    System.out.println("Final numOfShips: " + numOfShips);
                    destroyedCount = 0;
                    allDestroyed = true;

                    return;
                } */


            }//end if statement

            if (!turn) {
                return;
            }//end if statement

            //randomly select a space
            row = rand.nextInt(grid.length);
            column = rand.nextInt(grid[0].length);
            selectJ(row, column);

            if (grid[row][column] == 3 || grid[row][column] == 4) { // find another random space if space has already been attacked
                System.out.println("Already attacked at Grid[" + row + "][" + column + "] = " + grid[row][column]);
                continue;
            }

            //attack space then move on to player turn
            if (grid[row][column] == 0) {
                System.out.println("Miss at Grid[" + row + "][" + column + "] = " + grid[row][column]);
                grid[row][column] = 3;
                System.out.println("Change: Grid[" + row + "][" + column + "] = " + grid[row][column]);
                Game.buttons1[j].setIcon(HitOrMiss.O);
                /*clickSwitch = true;
                doClickSentHit = true;
                Game.buttons[j].doClick();
                doClickSentHit = false;
                clickSwitch = false; */
                //change color of space(attacked but no ship)
                return;
            }

            if (grid[row][column] == 1) {
                System.out.println("Hit at Grid[" + row + "][" + column + "] = " + grid[row][column]);
                Game.buttons1[j].setIcon(HitOrMiss.X);
                successfulHitCount++;
                assignHit();
                grid[row][column] = 2;
                System.out.println("Changed Grid[" + row + "][" + column + "] to " + grid[row][column]);
                // change color of space(successful attack on ship)
                System.out.println("Move on to player's turn");
                turn = false;
            }

            if (checkDestroyedShips()) {
                System.out.println("Final destroyedCount: " + destroyedCount);
                System.out.println("Final numOfShips: " + numOfShips);
                destroyedCount = 0;
                allDestroyed = true;
                if (gridSize == 7)
                    GameBoard.gameOver1 = 3;

                if (gridSize == 8)
                    GameBoard.gameOver1 = 4;

                if (gridSize == 9)
                    GameBoard.gameOver1 = 5;
            }
            return;
        }


    } //end AI

    private static void adjSpace(int[][] grid, int srow, int scolumn) {
        System.out.println("Checking adjacent spaces at Grid[" + srow + "][" + scolumn + "] = " + grid[srow][scolumn]);

        if (!checkBottomSpace(grid, srow, scolumn) && !checkTopSpace(grid, srow, scolumn) && !checkRightSpace(grid, srow, scolumn) && !checkLeftSpace(grid, srow, scolumn))
            return;

        while (turn) {

            int space = rand.nextInt(4); // randomly choose a space next to a hit to attack

            switch (space) {

                case 0:
                    if (row == (gridSize - 1) || grid[srow + 1][scolumn] == 2 || grid[srow + 1][scolumn] == 3 || grid[srow + 1][scolumn] == 4) {
                        continue;
                    } else {
                        if (grid[srow + 1][scolumn] == 1) { // case 1
                            successfulHitCount++;
                            row = row + 1;
                            grid[row][column] = 2;
                            selectJ(row, column);
                            assignHit();
                            Game.buttons1[j].setIcon(HitOrMiss.X); //change color of space(successful attack on ship)
                            System.out.println("Hit at Grid[" + row + "][" + column + "]");
                            System.out.println("Changed Grid[" + row + "][" + column + "] to " + grid[row][column]);
                            turn = false;
                            return; // maybe delete
                            /*if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }*/

                            //break; // move onto player's turn
                        }

                        if (grid[srow + 1][scolumn] == 0) {
                            grid[row + 1][column] = 3;
                            selectJ(row + 1, column);
                            Game.buttons1[j].setIcon(HitOrMiss.O);//change color of space(attacked but no ship)
                            System.out.println("No ship in space below: Grid[" + (row + 1) + "][" + column + "]");
                            System.out.println("Changed Grid[" + (row + 1) + "][" + column + "] to " + grid[row + 1][column]);
                            turn = false;
                            GameBoard.letter = row + 1;
                            GameBoard.number = 1 + column;
                            changed = true;
                            break; // move onto player's turn
                        }
                    }// end else

                case 1:
                    if (row == 0 || grid[row - 1][column] == 2 || grid[row - 1][column] == 3 || grid[row - 1][column] == 4) {
                        continue;
                    } else {
                        if (grid[srow - 1][scolumn] == 1) { // case 2
                            successfulHitCount++;
                            row = row - 1;
                            grid[row][column] = 2;
                            selectJ(row, column);
                            assignHit();
                            Game.buttons1[j].setIcon(HitOrMiss.X);//change color of space(successful attack on ship)
                            System.out.println("Hit at Grid[" + row + "][" + column + "]");
                            System.out.println("Changed Grid[" + row + "][" + column + "] to " + grid[row][column]);
                            turn = false;
                            return;//maybe delete

                            /*if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }*/

                            //break; // move onto player's turn
                        }

                        if (grid[srow - 1][scolumn] == 0) {
                            grid[row - 1][column] = 3;
                            selectJ(row - 1, column);
                            Game.buttons1[j].setIcon(HitOrMiss.O);// change color of space(attacked but no ship)
                            // move onto player's turn
                            System.out.println("No ship in space above: Grid[" + (row - 1) + "][" + column + "]");
                            System.out.println("Changed Grid[" + (row - 1) + "][" + column + "] to " + grid[row - 1][column]);
                            turn = false;
                            GameBoard.letter = row - 1;
                            GameBoard.number = 1 + column;
                            changed = true;
                            break; // move onto player's turn
                        }
                    }// end else

                case 2:
                    if (scolumn == (gridSize - 1) || grid[srow][scolumn + 1] == 2 || grid[srow][scolumn + 1] == 3 || grid[srow][scolumn + 1] == 4) {
                        continue;
                    } else {
                        if (grid[srow][scolumn + 1] == 1) { // case 3
                            successfulHitCount++;
                            column = column + 1;
                            grid[row][column] = 2;
                            selectJ(row, column);
                            assignHit();
                            Game.buttons1[j].setIcon(HitOrMiss.X);// change color of space(successful attack on ship)
                            System.out.println("change 3");
                            System.out.println("Hit at Grid[" + row + "][" + column + "]");
                            System.out.println("Changed Grid[" + row + "][" + column + "] to " + grid[row][column]);
                            turn = false;
                            return;//maybe delete

                            /*if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }*/

                            // break; // move onto player's turn
                        }

                        if (grid[srow][scolumn + 1] == 0) {
                            grid[row][column + 1] = 3;
                            selectJ(row, column + 1);
                            Game.buttons1[j].setIcon(HitOrMiss.O);// change color of space(attacked but no ship)
                            // move onto player's turn
                            System.out.println("No ship in space to the right: Grid[" + row + "][" + (column + 1) + "]");
                            System.out.println("Changed Grid[" + row + "][" + (column + 1) + "] to " + grid[row][column + 1]);
                            turn = false;
                            GameBoard.letter = row;
                            GameBoard.number = 1 + column + 1;
                            changed = true;
                            break; // move onto player's turn
                        }
                    }// end else

                case 3:
                    if (scolumn == 0 || grid[srow][scolumn - 1] == 2 || grid[srow][scolumn - 1] == 3 || grid[srow][scolumn - 1] == 4) {
                        continue;
                    } else {
                        if (grid[srow][scolumn - 1] == 1) { // case 4
                            successfulHitCount++;
                            column = column - 1;
                            grid[row][column] = 2;
                            selectJ(row, column);
                            assignHit();
                            Game.buttons1[j].setIcon(HitOrMiss.X);//** change color of space(successful attack on ship)
                            System.out.println("Hit at Grid[" + row + "][" + column + "]");
                            System.out.println("Changed Grid[" + row + "][" + column + "] to " + grid[row][column]);
                            turn = false;
                            return;//maybe delete

                            /*if((checkTopSpace(grid, row, column) == false) && (checkBottomSpace(grid, row, column) == false) && (checkLeftSpace(grid, row, column) == false) && (checkRightSpace(grid, row, column) == false)){
                                System.out.println("No ships around Grid["+ row + "]["+column+"] = " + grid[row][column]);
                                System.out.println("successfulHitCount: "+ successfulHitCount);
                                isDestroyed(grid);
                            }*/

                            // break; // move onto player's turn
                        }

                        if (grid[srow][scolumn - 1] == 0) {
                            grid[row][column - 1] = 3;
                            selectJ(row, column - 1);
                            Game.buttons1[j].setIcon(HitOrMiss.O);//** change color of space(attacked but no ship)
                            System.out.println("No ship in space to the left: Grid[" + row + "][" + (column - 1) + "]");
                            System.out.println("Changed Grid[" + row + "][" + (column - 1) + "] to " + grid[row][column - 1]);
                            turn = false;
                            GameBoard.letter = row;
                            GameBoard.number = 1 + column - 1;
                            changed = true;
                            break; // move onto player's turn
                        }
                    }// end else

            }//end switch


        } //end while

    }//end method adjSpace()

    private static boolean checkDestroyedShips() { //checks if all ships are destroyed

    /*for(int i = 0; i < gridSize; i++){
      for (int j = 0; j < gridSize; j++){

      if(grid[i][j] == 4){

      }

      }

    } */

        if (destroyedCount == numOfShips) {
            System.out.println("You Lose");
            System.out.println("All of your ships have been destroyed");
            return true;
        } else
            return false;

    }// end checkDestroyedShips

    private static void assignHit() { //keeps track of hits
        switch (successfulHitCount) {

            case 1:
                hitRow1 = row;
                hitColumn1 = column;

            case 2:
                hitRow2 = row;
                hitColumn2 = column;

            case 3:
                hitRow3 = row;
                hitColumn3 = column;

            case 4:
                hitRow4 = row;
                hitColumn4 = column;

            case 5:
                hitRow5 = row;
                hitColumn5 = column;

            case 6:
                hitRow6 = row;
                hitColumn6 = column;

        }//end switch

    }//end assignHit()

    private static void assignHitC(String C) {
        switch (successfulHitCount) {
            case 1:
                hitRow1 = C.charAt(0);
                hitRow1 = hitRow1 - 65;
                hitColumn1 = Integer.parseInt(String.valueOf(C.charAt(1)));//C.charAt(1).getNumericValue(c);

            case 2:
                hitRow2 = C.charAt(0);
                hitRow2 = hitRow2 - 65;
                hitColumn2 = Integer.parseInt(String.valueOf(C.charAt(1)));//C.charAt(1).getNumericValue(c);

            case 3:
                hitRow3 = C.charAt(0);
                hitRow3 = hitRow3 - 65;
                hitColumn3 = Integer.parseInt(String.valueOf(C.charAt(1)));//C.charAt(1).getNumericValue(c);

            case 4:
                hitRow4 = C.charAt(0);
                hitRow4 = hitRow4 - 65;
                hitColumn4 = Integer.parseInt(String.valueOf(C.charAt(1)));//C.charAt(1).getNumericValue(c);

            case 5:
                hitRow5 = C.charAt(0);
                hitRow5 = hitRow5 - 65;
                hitColumn5 = Integer.parseInt(String.valueOf(C.charAt(1)));//C.charAt(1).getNumericValue(c);

            case 6:
                hitRow6 = C.charAt(0);
                hitRow6 = hitRow6 - 65;
                hitColumn6 = Integer.parseInt(String.valueOf(C.charAt(1)));//C.charAt(1).getNumericValue(c);

        }//end switch
    }

    public static void assignHitD(int[][] asgrid, String D) {
        int hitRow = D.charAt(0);
        hitRow = hitRow -65;
        int hitColumn = Integer.parseInt(String.valueOf(D.charAt(1))) - 1;
        asgrid[hitRow][hitColumn] = 4;
        System.out.println("Changed grid["+hitRow+"]["+hitColumn+"] to 4");
        successfulHitCount = 0;
        for(int i = 0; i < asgrid.length; i++){
            for(int j = 0; j < asgrid[0].length; j++){
                if(asgrid[i][j] == 2 & (checkBottomSpace(asgrid, i, j) || checkTopSpace(asgrid, i, j) || checkRightSpace(asgrid, i, j) || checkLeftSpace(asgrid, i, j))) {
                    row = i;
                    column = j;
                    System.out.println("Found 2 at row "+row+" and column " + column);
                    break;
                }
            }
        }
        //row = rand.nextInt(asgrid.length);
        //column = rand.nextInt(asgrid[0].length);
    }


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



    public static void selectJ(int row, int column){

        if(gridSize ==7){
            if(row == 0){
                if(column == 0)
                    j = 0;

                if(column == 1)
                    j = 1;

                if(column == 2)
                    j = 2;

                if(column == 3)
                    j = 3;

                if(column == 4)
                    j = 4;

                if(column == 5)
                    j = 5;

                if(column == 6)
                    j = 6;

            }

            if(row == 1){
                if(column == 0)
                    j = 7;

                if(column == 1)
                    j = 8;

                if(column == 2)
                    j = 9;

                if(column == 3)
                    j = 10;

                if(column == 4)
                    j = 11;

                if(column == 5)
                    j = 12;

                if(column == 6)
                    j = 13;

            }

            if(row == 2){
                if(column == 0)
                    j = 14;

                if(column == 1)
                    j = 15;

                if(column == 2)
                    j = 16;

                if(column == 3)
                    j = 17;

                if(column == 4)
                    j = 18;

                if(column == 5)
                    j = 19;

                if(column == 6)
                    j = 20;

            }

            if(row == 3){
                if(column == 0)
                    j = 21;

                if(column == 1)
                    j = 22;

                if(column == 2)
                    j = 23;

                if(column == 3)
                    j = 24;

                if(column == 4)
                    j = 25;

                if(column == 5)
                    j = 26;

                if(column == 6)
                    j = 27;

            }

            if(row == 4){
                if(column == 0)
                    j = 28;

                if(column == 1)
                    j = 29;

                if(column == 2)
                    j = 30;

                if(column == 3)
                    j = 31;

                if(column == 4)
                    j = 32;

                if(column == 5)
                    j = 33;

                if(column == 6)
                    j = 34;

            }

            if(row == 5){
                if(column == 0)
                    j = 35;

                if(column == 1)
                    j = 36;

                if(column == 2)
                    j = 37;

                if(column == 3)
                    j = 38;

                if(column == 4)
                    j = 39;

                if(column == 5)
                    j = 40;

                if(column == 6)
                    j = 41;

            }

            if(row == 6){
                if(column == 0)
                    j = 42;

                if(column == 1)
                    j = 43;

                if(column == 2)
                    j = 44;

                if(column == 3)
                    j = 45;

                if(column == 4)
                    j = 46;

                if(column == 5)
                    j = 47;

                if(column == 6)
                    j = 48;

            }

        }//end 7x7

        if(gridSize ==8){
            if(row == 0){
                if(column == 0)
                    j = 0;

                if(column == 1)
                    j = 1;

                if(column == 2)
                    j = 2;

                if(column == 3)
                    j = 3;

                if(column == 4)
                    j = 4;

                if(column == 5)
                    j = 5;

                if(column == 6)
                    j = 6;

                if(column == 7)
                    j = 7;

            }

            if(row == 1){
                if(column == 0)
                    j = 8;

                if(column == 1)
                    j = 9;

                if(column == 2)
                    j = 10;

                if(column == 3)
                    j = 11;

                if(column == 4)
                    j = 12;

                if(column == 5)
                    j = 13;

                if(column == 6)
                    j = 14;

                if(column == 7)
                    j = 15;

            }

            if(row == 2){
                if(column == 0)
                    j = 16;

                if(column == 1)
                    j = 17;

                if(column == 2)
                    j = 18;

                if(column == 3)
                    j = 19;

                if(column == 4)
                    j = 20;

                if(column == 5)
                    j = 21;

                if(column == 6)
                    j = 22;

                if(column == 7)
                    j = 23;

            }

            if(row == 3){

                if(column == 0)
                    j = 24;

                if(column == 1)
                    j = 25;

                if(column == 2)
                    j = 26;

                if(column == 3)
                    j = 27;

                if(column == 4)
                    j = 28;

                if(column == 5)
                    j = 29;

                if(column == 6)
                    j = 30;

                if(column == 7)
                    j = 31;
            }

            if(row == 4){

                if(column == 0)
                    j = 32;

                if(column == 1)
                    j = 33;

                if(column == 2)
                    j = 34;

                if(column == 3)
                    j = 35;

                if(column == 4)
                    j = 36;

                if(column == 5)
                    j = 37;

                if(column == 6)
                    j = 38;

                if(column == 7)
                    j = 39;

            }

            if(row == 5){

                if(column == 0)
                    j = 40;

                if(column == 1)
                    j = 41;

                if(column == 2)
                    j = 42;

                if(column == 3)
                    j = 43;

                if(column == 4)
                    j = 44;

                if(column == 5)
                    j = 45;

                if(column == 6)
                    j = 46;

                if(column == 7)
                    j = 47;
            }

            if(row == 6){
                if(column == 0)
                    j = 48;

                if(column == 1)
                    j = 49;

                if(column == 2)
                    j = 50;

                if(column == 3)
                    j = 51;

                if(column == 4)
                    j = 52;

                if(column == 5)
                    j = 53;

                if(column == 6)
                    j = 54;

                if(column == 7)
                    j = 55;

            }

            if(row == 7){
                if(column == 0)
                    j = 56;

                if(column == 1)
                    j = 57;

                if(column == 2)
                    j = 58;

                if(column == 3)
                    j = 59;

                if(column == 4)
                    j = 60;

                if(column == 5)
                    j = 61;

                if(column == 6)
                    j = 62;

                if(column == 7)
                    j = 63;

            }

        }//end 8x8

        if(gridSize ==9){
            if(row == 0){
                if(column == 0)
                    j = 0;

                if(column == 1)
                    j = 1;

                if(column == 2)
                    j = 2;

                if(column == 3)
                    j = 3;

                if(column == 4)
                    j = 4;

                if(column == 5)
                    j = 5;

                if(column == 6)
                    j = 6;

                if(column == 7)
                    j = 7;

                if(column == 8)
                    j = 8;

            }

            if(row == 1){

                if(column == 0)
                    j = 9;

                if(column == 1)
                    j = 10;

                if(column == 2)
                    j = 11;

                if(column == 3)
                    j = 12;

                if(column == 4)
                    j = 13;

                if(column == 5)
                    j = 14;

                if(column == 6)
                    j = 15;

                if(column == 7)
                    j = 16;

                if(column == 8)
                    j = 17;

            }

            if(row == 2){

                if(column == 0)
                    j = 18;

                if(column == 1)
                    j = 19;

                if(column == 2)
                    j = 20;

                if(column == 3)
                    j = 21;

                if(column == 4)
                    j = 22;

                if(column == 5)
                    j = 23;

                if(column == 6)
                    j = 24;

                if(column == 7)
                    j = 25;

                if(column == 8)
                    j = 26;
            }

            if(row == 3){

                if(column == 0)
                    j = 27;

                if(column == 1)
                    j = 28;

                if(column == 2)
                    j = 29;

                if(column == 3)
                    j = 30;

                if(column == 4)
                    j = 31;

                if(column == 5)
                    j = 32;

                if(column == 6)
                    j = 33;

                if(column == 7)
                    j = 34;

                if(column == 8)
                    j = 35;
            }

            if(row == 4){

                if(column == 0)
                    j = 36;

                if(column == 1)
                    j = 37;

                if(column == 2)
                    j = 38;

                if(column == 3)
                    j = 39;

                if(column == 4)
                    j = 40;

                if(column == 5)
                    j = 41;

                if(column == 6)
                    j = 42;

                if(column == 7)
                    j = 43;

                if(column == 8)
                    j = 44;
            }

            if(row == 5){

                if(column == 0)
                    j = 45;

                if(column == 1)
                    j = 46;

                if(column == 2)
                    j = 47;

                if(column == 3)
                    j = 48;

                if(column == 4)
                    j = 49;

                if(column == 5)
                    j = 50;

                if(column == 6)
                    j = 51;

                if(column == 7)
                    j = 52;

                if(column == 8)
                    j = 53;
            }

            if(row == 6){

                if(column == 0)
                    j = 54;

                if(column == 1)
                    j = 55;

                if(column == 2)
                    j = 56;

                if(column == 3)
                    j = 57;

                if(column == 4)
                    j = 58;

                if(column == 5)
                    j = 59;

                if(column == 6)
                    j = 60;

                if(column == 7)
                    j = 61;

                if(column == 8)
                    j = 62;

            }

            if(row == 7){
                if(column == 0)
                    j = 63;

                if(column == 1)
                    j = 64;

                if(column == 2)
                    j = 65;

                if(column == 3)
                    j = 66;

                if(column == 4)
                    j = 67;

                if(column == 5)
                    j = 68;

                if(column == 6)
                    j = 69;

                if(column == 7)
                    j = 70;

                if(column == 8)
                    j = 71;

            }

            if(row == 8){
                if(column == 0)
                    j = 72;

                if(column == 1)
                    j = 73;

                if(column == 2)
                    j = 74;

                if(column == 3)
                    j = 75;

                if(column == 4)
                    j = 76;

                if(column == 5)
                    j = 77;

                if(column == 6)
                    j = 78;

                if(column == 7)
                    j = 79;

                if(column == 8)
                    j = 80;

            }

        }//end 9x9


    }//end selectJ

}//end class
