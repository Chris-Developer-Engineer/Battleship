import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
 */


public class GameBoard/*extends JFrame*/ {

    private final ArrayList<Ship> ship = new ArrayList<>();
    private final ArrayList<Ship> shipAI = new ArrayList<>();
    int numOfGuesses = 0;
    public int gameBoardSize;
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

    //Start the game
    public void initialize() {

        if(gameBoardSize == 7){
            ship.add(new Ship("Submarine", 2));
            ship.add(new Ship("Battleship", 3));
            ship.add(new Ship("Cruiser", 3));
            shipAI.add(new Ship("Submarine", 2));
            shipAI.add(new Ship("Battleship", 3));
            shipAI.add(new Ship("Cruiser", 3));
            alpha = alpha1;
            setAIShipLocations();
            setPlayerShipLocations();
        }

        else if(gameBoardSize == 8){
            ship.add(new Ship("Submarine", 2));
            ship.add(new Ship("Battleship", 3));
            ship.add(new Ship("Cruiser", 3));
            ship.add(new Ship("War Machine", 4));
            shipAI.add(new Ship("Submarine", 2));
            shipAI.add(new Ship("Battleship", 3));
            shipAI.add(new Ship("Cruiser", 3));
            shipAI.add(new Ship("War Machine", 4));
            alpha = alpha2;
            setAIShipLocations();
            setPlayerShipLocations();
        }

        else if(gameBoardSize == 9){
            ship.add(new Ship("Submarine", 2));
            ship.add(new Ship("Battleship", 3));
            ship.add(new Ship("Cruiser", 3));
            ship.add(new Ship("War Machine", 4));
            ship.add(new Ship("Carrier", 4));
            shipAI.add(new Ship("Submarine", 2));
            shipAI.add(new Ship("Battleship", 3));
            shipAI.add(new Ship("Cruiser", 3));
            shipAI.add(new Ship("War Machine", 4));
            shipAI.add(new Ship("Carrier", 4));
            alpha = alpha3;
            setAIShipLocations();
            setPlayerShipLocations();
        }

        System.out.println("Welcome to Battleship!");
        gameStart();
    }

    public void gameStart() {
        String guess, result,result1,guess1;
        int letter, number;
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        while(!ship.isEmpty() || !shipAI.isEmpty()) {
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


            System.out.println("Computer's turn...");
            result1 = "miss";

            letter = rand.nextInt(5);
            number = 1 + rand.nextInt(5);
            String loc = "" + alpha.charAt(letter) + number;
            System.out.println("The computer guessed: " + loc);

            guess1 = loc;
            guess1 = guess1.toUpperCase();
            for(int i = 0; i < shipAI.size(); i++) {
                result1 = shipAI.get(i).check(guess1);
                if(result1.equals("kill")) {
                    result1 = ("The computer sunk your " + shipAI.get(i).getShipName());
                    shipAI.remove(i);
                    break;
                }
                else if (result1.equals("hit")) {
                    break;
                }
            }

            System.out.println(result1);

        }
        input.close();
        gameEnd();
    }

    // This method sets the ship locations for the AI board
    // (the player will try to destroy all of the ships placed on this board
        private void setAIShipLocations () {
        // variables
        Random rand = new Random();
        ArrayList<String> location = new ArrayList<>();
        ArrayList<String> temp;
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



    private void setPlayerShipLocations() {
        // variables
        Random rand = new Random();
        ArrayList<String> location = new ArrayList<>();
        ArrayList<String> temp;
        int letter, number, vert, horz;
        boolean worked;
        try {
            //Start first for loop
            for (int i = 0; i < shipAI.size(); i++) {
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
                    for (int j = 0; j < shipAI.get(i).getShipSize(); j++) {

                        String loc = "" + alpha.charAt(letter) + number;
                        letter += vert;
                        number += horz;

                        //start third for loop
                        for (int t = 0; t < shipAI.size(); t++) {
                            if (t != i) {
                                temp = shipAI.get(t).getCurrLocations();
                                if (temp.contains(loc)) {
                                    worked = false;
                                    continue start;
                                }//end second if
                            }//end first if
                        }//end third for loop

                        location.add(loc); //add loc to location

                    }//end second for loop

                    shipAI.get(i).setShipLocations(location); //set the ship location

                }//end while loop
            }//end first for loop
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }// end setPlayerShipLocations



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