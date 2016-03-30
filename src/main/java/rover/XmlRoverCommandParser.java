package rover;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import rover.command.*;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

public class XmlRoverCommandParser {
    private String filename;
    private ArrayList<RoverCommand> commands;
    private Rover rover;
    private Iterator<RoverCommand> iterator;

    public RoverCommand readNextCommand(){
        return iterator.next();
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }


    public XmlRoverCommandParser(Rover rover){
        this.rover = rover;
        commands = new ArrayList<>();
    }

    public XmlRoverCommandParser(Rover rover, String filename){
        this.rover = rover;
        this.filename = filename;
        commands = new ArrayList<>();
    }

    public ArrayList<RoverCommand> parse(String filename){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;

        SAXHandler saxHandler = new SAXHandler(rover);
        try {
            saxParser = factory.newSAXParser();
            System.out.println(filename);
            saxParser.parse(new File(this.getClass().getResource(filename).toURI())
                                                                      ,saxHandler);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        commands = saxHandler.getCommands();
        iterator = commands.iterator();
        return commands;
    }

    class SAXHandler extends DefaultHandler{
        private ArrayList<RoverCommand> commands = new ArrayList<>();
        private Rover rover;
        private boolean imp;
        public String filename;
        public SAXHandler(Rover rover){
            this.rover = rover;
        }
        public ArrayList<RoverCommand> getCommands(){
            return commands;
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equalsIgnoreCase("move")){
                int x = Integer.parseInt(attributes.getValue("x"));
                int y = Integer.parseInt(attributes.getValue("y"));
                commands.add(new LoggingCommand(new MoveCommand(rover,x,y)));
            }else if(qName.equalsIgnoreCase("turn")){
                Direction direction = Direction.valueOf(attributes.getValue("direction").toUpperCase());
                commands.add(new LoggingCommand(new TurnCommand(rover,direction)));
            }else if(qName.equalsIgnoreCase("import")){
                imp = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if(imp){
                filename = new String(ch,start,length);
                commands.add(new LoggingCommand(new ImportCommand(parse("/" + filename))));
                imp = false;
            }
        }


    }
}


