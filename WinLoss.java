public class WinLoss {
    static double win = 91;
    static double loss = 9;
    private static double ratio = 0;
    private static double gameCount = win + loss;
    static String ranking = "Recruit";
    private boolean winner = true;

    public void scoreTracker(int win, int loss, boolean winner) {
        if (winner) {
            win++;
        } else {
            loss++;
        }
    }

    public static String ranking(){
        ratio = win/(win+loss);
        //System.out.println("Win: " + win + "\nLoss: " + loss + "\nTotal: " + gameCount + "\nRatio: " + ratio);
        if (ratio > (0.2) && ratio <= (0.6)){
            if (gameCount >= 20){
                ranking = "Petty Officer";
            }
        } else if (ratio > (0.6) && ratio <= (0.8)){
            if (gameCount >= 40){
                ranking = "Chief Warrant Officer";
            }
        } else if (ratio > (0.8) && ratio <= (0.9)){
            if (gameCount >= 60){
                ranking = "Ensign";
            }
        } else if (ratio > (0.9)){
            if (gameCount >= 100){
                ranking = "Captain";
            }
        }
        return ("Your ranking is: " + ranking);
    }
}
