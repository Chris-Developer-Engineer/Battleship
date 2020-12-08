import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JFrame {

    private ArrayList<Ship> ship = new ArrayList<>();
    private ArrayList<Ship> shipAI = new ArrayList<>();
    static String[] array = new String[100];
    static String[] array1 = new String[100];
    static String[] arrayAI = new String[100];
    static String[] arrayAI1 = new String[100];
    private int numOfGuesses = 0;
    private int gameOver = 0;
    static int gameOver1 = 0;
    private int k = 0;
    private int r = 0;
    static int letter, number;
    static boolean AITurnTracker = false;
    static boolean playerTurnTracker = false;
    static boolean doClickSentHit = false;
    static boolean doClickSentMiss = false;
    static boolean doClickSentHitAI = false;
    static boolean doClickSentMissAI = false;
    static boolean clickSwitch = false;
    public static int gameBoardSize;
    String alpha1 = "ABCDEFG";
    String alpha2 = "ABCDEFGH";
    String alpha3 = "ABCDEFGHI";
    String alpha = "";
    String coordinate;

    private JPanel panel;
    static JFrame frame = null;
    private JLabel label;
    private JLabel label1 ;
    private JLabel label2;
    private JTextField tf;
    private JButton button;

    public GameBoard(){};
    public GameBoard(int size) {
        panel = new JPanel();
        frame = new JFrame();
        label = new JLabel("Please enter a new coordinate and hit enter (Example: a1, A1, etc.): ");
        label1 = new JLabel("");
        label2 = new JLabel("");
        tf = new JTextField(2);
        button = new JButton("Enter");

        gameBoardSize = size;

        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        label.setBounds(10,20,80,25);
        panel.add(label);

        tf.setBounds(100,20,165,25);
        panel.add(tf);

        label1.setBounds(20,110,300,25);
        panel.add(label1);

        label2.setBounds(20,10,300,25);
        panel.add(label2);

        pack();
        setLocationRelativeTo(null);
        //frame.setVisible(true);

        button.setBounds(10,80,80,25);
        button.addActionListener(
                e ->{
                    System.out.println("Button clicked");
                    coordinate = tf.getText();
                    System.out.println(coordinate);

                    try {
                        gameStart();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    tf.setText("");
                }
        );
        panel.add(button);
        frame.getRootPane().setDefaultButton(button);
    }

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
        }

        setAIShipLocations();
        setPlayerShipLocations();

        System.out.println("Welcome to Battleship!");
    } //End Initialize

    //gameStart is called every time the player and the AI have completed a turn each.
    //Essentially both events happen each time this method is called until either the AI or the player has won
    public void gameStart() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        String guess, result, result1, guess1;
        //int letter, number;
        Random rand = new Random();
        playerTurnTracker = true;
        result = "miss";
        numOfGuesses++;
        guess = coordinate;
        guess = guess.toUpperCase();
        for (int i = 0; i < ship.size(); i++) {
            result = ship.get(i).check(guess);
            if (result.equals("kill")) {
                result = ("you sunk " + ship.get(i).getShipName());
                ship.remove(i);
                gameOver++;
                break;
            } else if (result.equals("hit")) {
                break;
            }
        }
        if(gameBoardSize == 7) {
            if (!result.equals("miss")) {
                for (int j = 0; j < 49; j++) {
                    clickSwitch = true;
                    if ((guess.toUpperCase().equals(Game.buttons[j].getText())) && (clickSwitch)) {
                        doClickSentHit = true;
                        Game.buttons[j].doClick();
                        doClickSentHit = false;
                        clickSwitch = false;
                        break;
                    }
                }
            } else {
                for (int j = 0; j < 49; j++) {
                    clickSwitch = true;
                    if ((guess.toUpperCase().equals(Game.buttons[j].getText())) && (clickSwitch)) {
                        doClickSentMiss = true;
                        Game.buttons[j].doClick();
                        doClickSentMiss = false;
                        clickSwitch = false;
                        break;
                    }
                }
            }
        }

        if(gameBoardSize == 8) {
            if (!result.equals("miss")) {
                for (int j = 0; j < 64; j++) {
                    clickSwitch = true;
                    if ((guess.toUpperCase().equals(Game.buttons[j].getText())) && (clickSwitch)) {
                        doClickSentHit = true;
                        Game.buttons[j].doClick();
                        doClickSentHit = false;
                        clickSwitch = false;
                        break;
                    }
                }
            } else{
                for (int j = 0; j < 64; j++) {
                    clickSwitch = true;
                    if ((guess.toUpperCase().equals(Game.buttons[j].getText())) && (clickSwitch)) {
                        doClickSentMiss = true;
                        Game.buttons[j].doClick();
                        doClickSentMiss = false;
                        clickSwitch = false;
                        break;
                    }
                }
            }
        }

        if(gameBoardSize == 9) {
            if (!result.equals("miss")) {
                for (int j = 0; j < 81; j++) {
                    clickSwitch = true;
                    if ((guess.toUpperCase().equals(Game.buttons[j].getText())) && (clickSwitch)) {
                        doClickSentHit = true;
                        Game.buttons[j].doClick();
                        doClickSentHit = false;
                        clickSwitch = false;
                        break;
                    }
                }
            } else{
                for (int j = 0; j < 81; j++) {
                    clickSwitch = true;
                    if ((guess.toUpperCase().equals(Game.buttons[j].getText())) && (clickSwitch == true)) {
                        doClickSentMiss = true;
                        Game.buttons[j].doClick();
                        doClickSentMiss = false;
                        clickSwitch = false;
                        break;
                    }
                }
            }
        }
        System.out.println(result);
        label1.setText("Your result: " + result + "  ");

        //////Start AI turn////////
        playerTurnTracker = false;
        System.out.println("Computer's turn...");
        result1 = "miss";

        AITurnTracker = true;
        BattleshipAI ba = new BattleshipAI();
        if(gameBoardSize == 7) {
            ba.AI(Game.gameBoardMultiArray7, 7);
        }

        if(gameBoardSize == 8) {
            ba.AI(Game.gameBoardMultiArray8, 8);
        }

        if(gameBoardSize == 9) {
            ba.AI(Game.gameBoardMultiArray9, 9);
        }

        if(!ba.changed) {
            letter = ba.row;
            number = 1 + ba.column;
        }
        ba.changed = false;
        //letter = rand.nextInt(5);
        //number = 1 + rand.nextInt(5);
        String loc = "" + alpha.charAt(letter) + number;
        System.out.println("The computer guessed: " + loc);
        //AITurnTracker = true;
        guess1 = loc;
        guess1 = guess1.toUpperCase();
        for (int i = 0; i < shipAI.size(); i++) {
            result1 = shipAI.get(i).check(guess1);
            if (result1.equals("kill")) {
                // String Str = shipAI.get(i).getCurrLocations1();
                // String[] arrOfStr = Str.split(",");//
                String listString = String.join(", ", shipAI.get(i).getCurrLocations1());
                String[] arrOfStr = listString.split(", ");
                for (String a : arrOfStr) {
                    if(gameBoardSize == 7) {
                        ba.assignHitD(Game.gameBoardMultiArray7, a);
                    }

                    if(gameBoardSize == 8) {
                        ba.assignHitD(Game.gameBoardMultiArray8, a);
                    }

                    if(gameBoardSize == 9) {
                        ba.assignHitD(Game.gameBoardMultiArray9, a);
                    }
                }
                result1 = ("The computer sunk your " + shipAI.get(i).getShipName());
                shipAI.remove(i);
                gameOver1++;
                break;
            } else if (result1.equals("hit")) {
                break;
            }
        }

       /* if(gameBoardSize == 7) {
            if (!result1.equals("miss")) {
                for (int j = 0; j < 49; j++) {
                    clickSwitch = true;
                    if ((guess1.toUpperCase().equals(Game.buttons1[j].getText())) && (clickSwitch)) {
                        doClickSentHitAI = true;
                        Game.buttons1[j].doClick();
                        doClickSentHitAI = false;
                        clickSwitch = false;
                        break;
                    }
                }
            } else{
                for (int j = 0; j < 49; j++) {
                    clickSwitch = true;
                    if ((guess1.toUpperCase().equals(Game.buttons1[j].getText())) && (clickSwitch)) {
                        doClickSentMissAI = true;
                        Game.buttons1[j].doClick();
                        doClickSentMissAI = false;
                        clickSwitch = false;
                        break;
                    }
                }
            }
        }

        if(gameBoardSize == 8) {
            if (!result1.equals("miss")) {
                for (int j = 0; j < 64; j++) {
                    clickSwitch = true;
                    if ((guess1.toUpperCase().equals(Game.buttons1[j].getText())) && (clickSwitch)) {
                        doClickSentHitAI = true;
                        Game.buttons1[j].doClick();
                        doClickSentHitAI = false;
                        clickSwitch = false;
                        break;
                    }
                }
            } else{
                for (int j = 0; j < 64; j++) {
                    clickSwitch = true;
                    if ((guess1.toUpperCase().equals(Game.buttons1[j].getText())) && (clickSwitch)) {
                        doClickSentMissAI = true;
                        Game.buttons1[j].doClick();
                        doClickSentMissAI = false;
                        clickSwitch = false;
                        break;
                    }
                }
            }
        }

        if(gameBoardSize == 9) {
            if (!result1.equals("miss")) {
                for (int j = 0; j < 81; j++) {
                    clickSwitch = true;
                    if ((guess1.toUpperCase().equals(Game.buttons1[j].getText())) && (clickSwitch)) {
                        doClickSentHitAI = true;
                        Game.buttons1[j].doClick();
                        doClickSentHitAI = false;
                        clickSwitch = false;
                        break;
                    }
                }
            } else{
                for (int j = 0; j < 81; j++) {
                    clickSwitch = true;
                    if ((guess1.toUpperCase().equals(Game.buttons1[j].getText())) && (clickSwitch)) {
                        doClickSentMissAI = true;
                        Game.buttons1[j].doClick();
                        doClickSentMissAI = false;
                        clickSwitch = false;
                        break;
                    }
                }
            }
        } */

        System.out.println(result1);

        label2.setText("Computer's result: " + result1);
        AITurnTracker = false;
        if((gameOver == 3 && gameBoardSize == 7) || ((gameOver1 == 3 && gameBoardSize == 7))){
            gameEnd();
            button.setEnabled(false);
        }

        else if((gameOver == 4 && gameBoardSize == 8) || ((gameOver1 == 4 && gameBoardSize == 8))){
            gameEnd();
            button.setEnabled(false);
        }

        else if((gameOver == 5 && gameBoardSize == 9) || ((gameOver1 == 5 && gameBoardSize == 9))){
            gameEnd();
            button.setEnabled(false);
        }
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

                    array[i] = ship.get(i).getCurrLocations().toString();
                    int j = 0;
                    try {
                        for (; k < array1.length; k++) {
                            array1[k] = ship.get(i).getCurrLocations().get(j);
                            j++;
                        }
                    }catch(Exception exxx){}

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

                    arrayAI[i] = shipAI.get(i).getCurrLocations().toString();
                    int j = 0;
                    try {
                        for (;r < arrayAI1.length; r++) {
                            arrayAI1[r] = shipAI.get(i).getCurrLocations().get(j);
                            j++;
                        }
                    }catch(Exception exxx){}

                }//end while loop
            }//end first for loop
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }// end setPlayerShipLocations



    // This method is called when the game ends
    private void gameEnd() {
        if (gameOver == 3 || gameOver == 4 || gameOver == 5) {
            WinLoss.scoreTracker(true);
            label.setFont(label.getFont().deriveFont(30.0f));
            label.setText("Congrats, you won!");
            label1.setFont(label.getFont().deriveFont(30.0f));
            label1.setText("It took you " + numOfGuesses + " guesses.");
            label2.setText("");
            new Winner();
        } else {
            WinLoss.scoreTracker(false);
            label.setFont(label.getFont().deriveFont(30.0f));
            label.setText("The computer wins.");
            label1.setFont(label.getFont().deriveFont(30.0f));
            label1.setText("It took them " + numOfGuesses + " guesses.");
            label2.setText("");
            new Loser();
        }
    }

        public void gameRestart () {
            ship = new ArrayList<>();
            shipAI = new ArrayList<>();
            array = new String[100];
            array1 = new String[100];
            arrayAI = new String[100];
            arrayAI1 = new String[100];
            numOfGuesses = 0;
            gameOver = 0;
            gameOver1 = 0;
            k = 0;
            r = 0;
            number = 0;
            letter = 0;
            AITurnTracker = false;
            playerTurnTracker = false;
            doClickSentHit = false;
            doClickSentMiss = false;
            doClickSentHitAI = false;
            doClickSentMissAI = false;
            clickSwitch = false;
            alpha1 = "ABCDEFG";
            alpha2 = "ABCDEFGH";
            alpha3 = "ABCDEFGHI";
            alpha = "";
            coordinate = "";
        }// End gameRestart

        public static void closeGuessWindow () {
            frame.dispose();
        }
}    //End GameBoard
