import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners

    public class BoardSelection {
        private final int WIDTH = 500, LENGTH = 500;
        private Style style = new Style();
        private JFrame gameScreen = style.frame("Welcome", WIDTH, LENGTH);

        //Menu Instantiation
        private JMenuBar mainBar = new JMenuBar();
        private JMenu fileMenu = style.menu("File");
        private JMenuItem exitItem = style.menuItem("Exit");

        //Button/Labels Instantiation
        private JButton board1 = style.button("Play with a 7x7 board");
        private JButton board2 = style.button("Play with a 8x8 board");
        private JButton board3 = style.button("Play with a 9x9 board");

        //Panel
        private JPanel gamePanel = new JPanel(new GridBagLayout());
        private JPanel board1Panel = new JPanel(new GridBagLayout());
        private JPanel board2Panel = new JPanel(new GridBagLayout());
        private JPanel board3Panel = new JPanel(new GridBagLayout());

        //Box Layout
        private BoxLayout layout = new BoxLayout(gamePanel, BoxLayout.Y_AXIS);

        public BoardSelection() {
            setScreen();
            gameScreen.setVisible(true);
        }

        public void setScreen(){

            gameScreen.add(board1Panel);
            gameScreen.add(board2Panel);
            gameScreen.add(board3Panel);

            board1Panel.add(board1);
            board2Panel.add(board2);
            board3Panel.add(board3);

            gameScreen.setLocationRelativeTo(null);
            gameScreen.setLayout(new FlowLayout());

            mainBar.add(fileMenu);
            fileMenu.add(exitItem);

            gameScreen.setJMenuBar(mainBar);

            exitItem.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            System.exit(0);
                        }
                    }
            );

            board1.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            gameScreen.dispose();
                            GameBoard game = new GameBoard(7); //Creates a 7x7 gameboard object
                            game.initialize(); // Initializes the game
                        }
                    }
            );

            board2.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            gameScreen.dispose();
                            GameBoard game = new GameBoard(8); //Creates a 8x8 gameboard object
                            game.initialize(); // Initializes the game
                        }
                    }
            );

            board3.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            gameScreen.dispose();
                            //edit this constructor for 9x9
                            GameBoard game = new GameBoard(9); //Creates a 9x9 gameboard object
                            game.initialize(); // Initializes the game
                        }
                    }
            );

        }
    }