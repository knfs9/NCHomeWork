package rover.command;

import rover.Moveable;

/**
 * Created by RTCCD on 14.03.2016.
 */
public class MoveCommand implements RoverCommand {
    private Moveable rover;
    private int x;
    private int y;

    @Override
    public void execute() {
        rover.move(x,y);
    }

    public MoveCommand(Moveable rover, int x, int y){
        this.x = x;
        this.y = y;
        this.rover = rover;
    }
}
