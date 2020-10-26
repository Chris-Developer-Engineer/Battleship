public class WinLoss {
    private double win = 0;
    private double loss = 0;
    private double ratio = 0;
    private double gameCount = 0;
    private String ranking = "Recruit";
    private boolean winner = true;

    public void scoreTracker(int win, int loss, boolean winner) {
        if (winner) {
            win++;
        } else {
            loss++;
        }
    }

    public static void ranking(double win, double loss, double gameCount, double ratio, String ranking){
        ratio = win/(win+loss);
        System.out.println("Win: " + win + "\nLoss: " + loss + "\nTotal: " + gameCount + "\nRatio: " + ratio);
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
        System.out.println("Your ranking is: " + ranking);
    }
}