package rover;


import rover.command.MoveCommand;
import rover.command.RoverCommand;
import rover.command.TurnCommand;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTCCD on 14.03.2016.
 */
public class RoverCommandParser {
    private String filename;
    private ArrayList<RoverCommand> commands;
    private Rover rover;
    private Iterator<RoverCommand> iterator;
    private static final String WORDPATTERN = "\\D+";

    public RoverCommand readNextCommand(){
        return iterator.next();
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }

    /**
     * Constructor create collections with commands
     * @param rover
     * @see TurnCommand
     * @see MoveCommand
     */
    public RoverCommandParser(Rover rover){
        this.rover = rover;
        commands = new ArrayList<>();
    }

    public RoverCommandParser(Rover rover, String filename){
        this.rover = rover;
        this.filename = filename;
        commands = new ArrayList<>();
    }

    public void parse(String filename){
        if(filename.equals("") || filename == null)
            throw new IllegalArgumentException("Invalid file name");
        Path file = Paths.get(filename);
        String line;
        try(InputStream in = Files.newInputStream(file)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while((line = reader.readLine()) != null){
                commands.add(checkCommand(line));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        iterator = commands.iterator();
    }

    /**
     *
     * @param str String from the file
     * @return Returns the command received from file string
     */
    private RoverCommand checkCommand(String str){
        str = str.toLowerCase();
        if(str.equals("") || str == null)
            throw new IllegalArgumentException("Invalid command");
        if(str.contains("move")){
            Pattern pattern = Pattern.compile(WORDPATTERN);
            Matcher matcher = pattern.matcher(str);
            String coords = matcher.replaceFirst("");
            String[] mass = coords.split(" ");
            if(mass.length != 2)
                throw new IllegalArgumentException("Invalid command: " + str);
            int x = Integer.parseInt(mass[0]);
            int y = Integer.parseInt(mass[1]);
            return new MoveCommand(rover,x,y);
        }else if(str.contains("turn")){
            String dirStr = str.replace("turn","").trim().toUpperCase();
            return new TurnCommand(rover, Direction.valueOf(dirStr));
        }else {
            throw new IllegalArgumentException("Invalid command: " + str);
        }
    }
}
