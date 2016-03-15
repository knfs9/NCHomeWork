package rover.command;

import rover.Direction;
import rover.Turnable;

/**
 * Created by RTCCD on 14.03.2016.
 */
public class TurnCommand implements RoverCommand {
    private Turnable rover;
    private Direction direction;
    @Override
    public void execute() {
        rover.turnTo(direction);
    }

    public TurnCommand(Turnable rover, Direction direction){
        this.rover = rover;
        this.direction = direction;
    }
}
