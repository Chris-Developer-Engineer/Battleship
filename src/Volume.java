public class Volume {
    static double x = 0.5; //preset volume;
    static double y; //For mute, place holder for previous sound volume;
    public Volume() {
    }


    public  static void upSound(){ //turn volume up
        if(x<1)
            x = x + 0.06;
        else
            x = 1;
    }
    public static void downSound(){
        if(x>0)
            x = x - 0.06;
        else
            x = 0;
    }
    public static void Mute(){
        if(x>0) {
            y = x;
            x = 0;
        }
        else
            x = y;
    }
}
