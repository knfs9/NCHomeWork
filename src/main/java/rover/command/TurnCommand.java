package rover.command;

import rover.Direction;
import rover.Turnable;


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

    @Override
    public String toString() {
        return "TURN";
    }
}
