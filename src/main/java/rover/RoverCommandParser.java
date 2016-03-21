package rover;


import exceptions.FileCycleException;
import rover.command.*;
import sun.rmi.runtime.Log;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RoverCommandParser {
    private String filename;
    private ArrayList<RoverCommand> commands;
    private Rover rover;
    private Iterator<RoverCommand> iterator;
    private static final String WORDPATTERN = "\\D+";
    private static ArrayList<String> files = new ArrayList<>();

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

    /**
     * Parse file
     * @param filename
     * @return Collection of RoverCommands
     * @see RoverCommand
     */
    private ArrayList<RoverCommand> parseFile(String filename){
        ArrayList<RoverCommand> temp = new ArrayList<>();
        Path file = Paths.get(filename);
        String line;
        try(InputStream in = Files.newInputStream(file)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while((line = reader.readLine()) != null){
                //check for line break or comment
                if(line.equals("") || line.trim().startsWith("//"))
                    continue;
                temp.add(checkCommand(line));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        iterator = temp.iterator();
        return temp;
    }

    /**
     * Parse given file
     * @param filename
     */
    public void parse(String filename){
        if(filename == null || filename.equals(""))
            throw new IllegalArgumentException("Invalid file name");
        files.add(filename);
        this.commands =  parseFile(filename);
    }

    /**
     * Parse line from file
     * @param str String from the file
     * @return Returns the command received from file string
     * @see RoverCommand
     */
    private RoverCommand checkCommand(String str) throws FileNotFoundException {
        str = str.toLowerCase();
        if(str.equals(""))
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
            return new LoggingCommand(new MoveCommand(rover,x,y)) ;
        }else if(str.contains("turn")){
            String dirStr = str.replace("turn","").trim().toUpperCase();
            return new LoggingCommand(new TurnCommand(rover, Direction.valueOf(dirStr)));
        }else if(str.contains("import")){
            String filepath = str.replace("import","").trim();
            if(!files.contains(filepath)){
                files.add(filepath);
            }else {
                throw new FileCycleException("Cycle file : " + filepath);
            }
            if(!Files.exists(Paths.get(filepath)))
                throw new FileNotFoundException("File not found");
            return new LoggingCommand(new ImportCommand(parseFile(filepath)));
        }else {
            throw new IllegalArgumentException("Invalid command: " + str);
        }
    }
}
