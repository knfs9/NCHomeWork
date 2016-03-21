package rover.command;

import rover.Moveable;


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

    @Override
    public String toString() {
        return "MOVE";
    }
}
