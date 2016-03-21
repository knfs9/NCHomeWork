package rover.command;


public class LoggingCommand implements RoverCommand {
    private RoverCommand command;

    public LoggingCommand(RoverCommand command){
        this.command = command;
    }

    @Override
    public void execute(){
        System.out.println("COMMAND : " + command.toString());
        command.execute();
    }
}
