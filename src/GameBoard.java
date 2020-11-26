import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
 */

public class GameBoard /*extends JFrame*/ {

    private ArrayList<Ship> ship = new ArrayList<Ship>();
    int numOfGuesses = 0;
    int gameBoardSize;
    String alpha1 = "ABCDEFG";
    String alpha2 = "ABCDEFGI";
    String alpha3 = "ABCDEFGIJ";
    String alpha = "";

    public GameBoard(int size) {
        gameBoardSize = size;
    }
/*
    public GameBoard() {

        int[][] a = new int[size][size];

        this.setSize(600,600);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("BattleShip");

        JPanel thePanel = new JPanel();

        JLabel label1 = new JLabel("Game board");

        JButton button1 = new JButton("Enter");

        thePanel.add(label1);

        this.add(thePanel);

        this.setVisible(true);

    }

 */

    //Begins the game
    public void initialize() {

        if(gameBoardSize == 7){
            ship.add(new Ship("Submarine", 2));
            ship.add(new Ship("Battleship", 3));
            ship.add(new Ship("Cruiser", 4));
            alpha = alpha1;
            setAIShipLocations();
        }

        else if(gameBoardSize == 8){
            ship.add(new Ship("Submarine", 2));
            ship.add(new Ship("Battleship", 3));
            ship.add(new Ship("Cruiser", 4));
            ship.add(new Ship("War Machine", 5));
            alpha = alpha2;
            setAIShipLocations();
        }

        else if(gameBoardSize == 9){
            ship.add(new Ship("Submarine", 2));
            ship.add(new Ship("Battleship", 3));
            ship.add(new Ship("Cruiser", 4));
            ship.add(new Ship("War Machine", 5));
            ship.add(new Ship("Carrier", 6));
            alpha = alpha3;
            setAIShipLocations();
        }

        System.out.println("Welcome to Battleship!");
        gameStart();
    }

    private void gameStart() {
        String guess, result;
        Scanner input = new Scanner(System.in);
        while(!ship.isEmpty()) {
            result = "miss";
            numOfGuesses++;
            System.out.println("Enter a guess");
            guess = input.nextLine();
            guess = guess.toUpperCase();
            for(int i = 0; i < ship.size(); i++) {
                result = ship.get(i).check(guess);
                if(result.equals("kill")) {
                    result = ("you sunk " + ship.get(i).getShipName());
                    ship.remove(i);
                    break;
                }
                else if (result.equals("hit")) {
                    break;
                }
            }
            System.out.println(result);
        }
        input.close();
        gameEnd();
    }

    // This method sets the ship locations for the AI board
    // (the player will try to kill all of the ships placed on this board
    private void setAIShipLocations () {
        // variables
        Random rand = new Random();
        ArrayList<String> location = new ArrayList<String>();
        ArrayList<String> temp = null;
        int letter, number, vert, horz;
        boolean worked;
        try {
            //Start first for loop
            for (int i = 0; i < ship.size(); i++) {
                worked = false;

                // Continue here if ship could not be placed
                start:
                //start while loop
                while (!worked) {
                    location.clear(); //clear the location setter
                    worked = true;
                    letter = rand.nextInt(5);
                    number = 1 + rand.nextInt(5);

                    //this code is for vertical or horizontal ship placement
                    if (number % 2 == 0) {
                        vert = 1;
                        horz = 0;
                    } else {
                        vert = 0;
                        horz = 1;
                    }

                    //start second for loop
                    for (int j = 0; j < ship.get(i).getShipSize(); j++) {

                        String loc = "" + alpha.charAt(letter) + number;
                        letter += vert;
                        number += horz;

                        //start third for loop
                        for (int t = 0; t < ship.size(); t++) {
                            if (t != i) {
                                temp = ship.get(t).getCurrLocations();
                                if (temp.contains(loc)) {
                                    worked = false;
                                    continue start;
                                }//end second if
                            }//end first if
                        }//end third for loop

                        location.add(loc); //add loc to location

                    }//end second for loop

                    ship.get(i).setShipLocations(location); //set the ship location

                }//end while loop
            }//end first for loop
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }// end setAIShipLocations

    // This method is called when the game ends
    private void gameEnd() {
        if(numOfGuesses == 9) {
            System.out.println("Congrats, perfect score!");
        }
        else if(numOfGuesses < 20) {
            System.out.println("Not bad! It took you " + numOfGuesses + " guesses.");
        }
        else {
            System.out.println("It took you " + numOfGuesses + " guesses.");
        }
    }
}
