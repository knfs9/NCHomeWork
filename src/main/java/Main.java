import ground.Ground;
import rover.Rover;

/**
 * Created by RTCCD on 06.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        Rover r = new Rover();
        //r.getVisor().setGround(new Ground(11,11));
        r.getVisor().setGround(new Ground(30,30));
        //r.executeProgramFile("wrongcommands");
        //r.executeProgramFile("wrongcommands2");
        r.executeProgramFile("commands");

    }

}
