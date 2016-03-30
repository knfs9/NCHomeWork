package rover.command;

import rover.Rover;

import java.util.List;

/**
 * A command that allows you to import a file with instructions to another.
 */
public class ImportCommand implements RoverCommand {
    private List<RoverCommand> commands;
    private String filename;

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

    public String getFilename(){
        return filename;
    }
}
