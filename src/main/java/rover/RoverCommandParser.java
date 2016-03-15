package rover;


import rover.command.MoveCommand;
import rover.command.RoverCommand;
import rover.command.TurnCommand;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTCCD on 14.03.2016.
 */
public class RoverCommandParser {
    private String filename;
    private ArrayList<RoverCommand> commands;
    private Rover rover;
    private int pos;
    private int size;

    public RoverCommand readNextCommand(){
        return commands.get(pos++);
    }

    public int getSize(){
        return commands.size();
    }

    /**
     * Constructor create collections with commands
     * @param rover
     * @param filename
     * @see TurnCommand
     * @see MoveCommand
     */
    public RoverCommandParser(Rover rover, String filename){
        if(filename.equals("") || filename == null)
            throw new IllegalArgumentException("Invalid file name");
        this.filename = filename;
        this.rover = rover;
        commands = new ArrayList<>();
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
    }

    /**
     *
     * @param str String from the file
     * @return Returns the command received from file string
     */
    private RoverCommand checkCommand(String str){
        RoverCommand command = null;
        str = str.toLowerCase();
        if(str.equals("") || str == null)
            throw new IllegalArgumentException("Invalid command");
        if(str.contains("move")){
            Pattern pattern = Pattern.compile("\\D+");
            Matcher matcher = pattern.matcher(str);
            String coords = matcher.replaceFirst("");
            String[] mass = coords.split(" ");
            if(mass.length != 2)
                throw new IllegalArgumentException("Invalid command: " + str);
            int x = Integer.parseInt(mass[0]);
            int y = Integer.parseInt(mass[1]);
            return new MoveCommand(rover,x,y);
        }else if(str.contains("turn")){
            String dirStr = str.replace("turn","");
            Direction direction = parseDirection(dirStr);
            return new TurnCommand(rover, direction);
        }else {
            throw new IllegalArgumentException("Invalid command: " + str);
        }

    }

    /**
     *
     * @param str Direction string
     * @return Direction
     */
    private Direction parseDirection(String str){
        Direction dir = null;
        switch (str.trim().toLowerCase()){
            case "north" : dir = Direction.NORTH; break;
            case "east" : dir =  Direction.EAST; break;
            case "south" : dir =  Direction.SOUTH; break;
            case "west" : dir =  Direction.WEST; break;

        }
        return dir;
    }
}
