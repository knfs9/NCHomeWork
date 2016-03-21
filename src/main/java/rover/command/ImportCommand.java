package rover.command;

import rover.Rover;

import java.util.List;


public class ImportCommand implements RoverCommand {
    private List<RoverCommand> commands;
    public ImportCommand(List<RoverCommand> commands){
        this.commands = commands;
    }
    @Override
    public void execute() {
        for(RoverCommand c: commands){
            c.execute();
        }
    }

    @Override
    public String toString() {
        return "IMPORT ";
    }
}
