package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents the board on which the Cluedo game is played.
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class Board
{
    // maximum size of the board
    private static final int MAX_ROWS    = 50;
    private static final int MAX_COLUMNS = 50;
	
    // actual number of rows and columns of the board
    private int rows;
    private int columns;
    // the 2D array of board squares
    private BoardSquare[][] squares;
    // the list of the rooms
    private Room[] rooms;

    /**
     * Constructs an empty board of a specified size.
     *
     * @param rows the number of rows for this board
     * @param columns the number of columns for this board
     *
     * @throws IllegalArgumentException if the dimensions of the board 
     *                                  are too big or too small
     */
    public Board(int rows, int columns) 
            throws IllegalArgumentException
    {
        checkBoardDimensions(rows, columns);        
        initialiseBoard(rows, columns);
    }
	
    /**
     * Constructs a board with details loaded from a file.
     *
     * @param filename the name of the data file to use for loading
     *
     * @throws IllegalArgumentException if the dimensions of the board
     *                                  are too big or too small
     */
    public Board(String filename) 
            throws IllegalArgumentException
    {
        loadBoardFromFile(filename);
    }
    
    /**
     * Ensures valid dimensions for board.
     * 
     * @param rows the number of rows that the board should have
     * @param columns the number of columns that the board should have
     * 
     * @throws IllegalArgumentException if the number of rows or columns
     *                                  is too small or too large
     */
    private void checkBoardDimensions(int rows, int columns) 
            throws IllegalArgumentException
    {
        if ( (rows < 1) || (rows > MAX_ROWS) )
        {
            throw new IllegalArgumentException(
                    "Number of rows on the board must be between 1 and " +
                    MAX_ROWS + " inclusive.");
        }
        if ( (columns < 1) || (columns > MAX_COLUMNS) )
        {
            throw new IllegalArgumentException(
                    "Number of rows on the board must be between 1 and " +
                    MAX_COLUMNS + " inclusive.");
        }
    }
        
    /**
     * Uses data from file to initialise the board.
     *
     * @param filename the filename of the data file to load
     */
    private void loadBoardFromFile(String filename)
            throws IllegalArgumentException
    {
        try ( Scanner input = new Scanner(new File(filename)) )
        {
            input.useDelimiter("\\s*,\\s*");

            // create the rooms
            createRooms(input);
            
            // create the board
            skipBlockComment(input, "board dimensions");
            int fileRows    = input.nextInt();
            int fileColumns = input.nextInt();
            checkBoardDimensions(fileRows, fileColumns);
            skipBlockComment(input, "board layout");
            initialiseBoard(fileRows, fileColumns);

            // read and setup the squares
            setUpSquares(input);
			
            // don't forget to clean up
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Unable to find data file '" + filename + "'");
        }
    }
   
    /**
     * Initialises the details of the board.
     * All squares are initialised to white floor squares.
     *
     * @param rows the number of rows to initialise
     * @param columns the number of columns to initialise
     */
    private void initialiseBoard(int rows, int columns)
    {
        this.rows    = rows;
        this.columns = columns;
        squares = new BoardSquare[rows][columns];
        for (int row = 0; row < rows; row++) 
        {
            for (int col = 0; col < columns; col++)
            {
                Position pos = new Position(this, row, col);
                squares[row][col] = new Floor(pos, Color.WHITE);
            }            
        }
    }
    
    /**
     * Uses file data to create the rooms.
     *
     * @param input the scanner input from the data file
     */
    private void createRooms(Scanner input)
            throws IllegalArgumentException
    {
        skipBlockComment(input, "room count");
        int roomCount = input.nextInt();
        skipBlockComment(input, "room definitions");
        rooms = new Room[roomCount];
        for ( int i = 0; i < roomCount ;  i++ )
        {
            int    roomNo   = input.nextInt();
            String roomName = input.next();
            for ( RoomName enumName : RoomName.values() )
            {
                if ( roomName.equals(enumName.toString()) )
                {
                    rooms[roomNo] = new Room(enumName);
                }
            }
            if ( rooms[roomNo] == null )
            {
                throw new IllegalArgumentException(
                        "Room name '" + roomName + "' is invalid");
            }
        }        
    }
    
    /**
     * Lines in file that start with # are a comment. 
     * These lines need to be skipped when file is processed.
     *
     * @param input the scanner input from the data file
     * @param blockName the block that was loaded so far 
     *                  to indicate a more exact location for errors
     */
    private void skipBlockComment(Scanner input, String blockName)
            throws IllegalArgumentException
    {
        // read the block comment (skipping any remaining ',' 
        // from the previous data
        String comment;
        do
        {
            comment = input.nextLine().trim();
        } while ( comment.equals(",") );
        
        // check if it is the comment (starting with #)
        if ( comment.isEmpty() || (comment.charAt(0) != '#') )
        {
            throw new IllegalArgumentException(
                    "Error in board data file before " + blockName + " block.");
        }
    }
    
    /**
     * Creates the array of squares that are represented on the map in the file.
     *
     * @param input the scanner input from the data file
     */
    private void setUpSquares(Scanner input) 
    {
        for ( int row = 0 ; row < rows ; row++ ) 
        {
            String boardRow = input.next();
            for ( int col = 0 ; col < columns ; col++ )
            {
                // read the single character that indicates what sort of square this is
                char squareID = boardRow.charAt(col);
                // create the square
                BoardSquare square = createSquare(row, col, squareID);
                
                if ( square != null ) 
                {
                    squares[row][col] = square;
                }
            }
        }
    }
    
    /**
     * Creates a single square of type represented by squareID.
	 *
	 * @param row the row to create the square in
	 * @param column the column to create the square in
	 * @param squareID the character with the ID of the square to create
	 *
	 * @return the created BoardSquare instance or null if not possible
     */
    private BoardSquare createSquare(int row, int col, char squareID)
    {
        BoardSquare square = null;
        Position    pos    = new Position(this, row, col);
        switch ( squareID )
        {
            case '.' : square = new Floor(pos, Color.WHITE); break;
            case 'D' : square = new Doorway(pos); break;
            case 'W' : square = new Wall(pos);    break;
            default  :
            {
                // if it is a number, assign to a room
                int roomNo = (int) squareID - '0';
                if ( (roomNo >= 0) && 
                     (roomNo < rooms.length) )
                {
                    square = new Floor(pos, Color.YELLOW);
                    rooms[roomNo].addBoardSquare(square);
                }
            }
        }
        return square;
    }
    
    /**
     * Gets the square at a given position.
	 *
     * @param position of square
	 *
     * @return BoardSquare at this position
     */
    public BoardSquare getSquare(Position pos)
    {
        return squares[pos.getRow()][pos.getColumn()];
    }
 
    /**
     * Selects a random floor square with no occupants.
     * 
     * @return a random board square without occupants
     */ 
    private BoardSquare getEmptyRandomSquare()
    {
        BoardSquare square;
        do
        {
            // generate random position
            Position pos = new Position(this, 
                (int) (Math.random() * rows), 
                (int) (Math.random() * columns));
            square = getSquare(pos);
            // repeat as long as square at that position is not empty
        } while ( !(square instanceof Floor) ||
                    square.hasOccupants() );
        
        return square;
    }
    
    /**
     * Gets a random board square that is inside of a room.
     *
     * @return a random empty square that is inside a room
     */
    public BoardSquare getEmptyRandomSquareInRoom()
    {
        BoardSquare square;
        Room        room;
        do
        {
            square = getEmptyRandomSquare();
            room   = getRoomAtPosition(square.getPosition());
            // repeat as long as empty square is not inside a room
        } while ( room == null );
        
        return square;
    }
    
    /**
     * Gets the room at a given position.
     *
     * @param pos any position on the board
     * @return the room at this position or null if the position is not in a room
     */
    public Room getRoomAtPosition(Position pos)
    {
        Room retRoom = null;
        for ( Room room : rooms ) 
        {
            if ( room.containsPosition(pos) )
            {
                retRoom = room;
            }
        }
        return retRoom;
    }
    
    /**
     * Gets the number of rows on this board.
     *
     * @return the number of rows on this board
     */
    public int getNumRows()
    {
        return rows;
    }
    
    /**
     * Gets the number of columns on this board.
     *
     * @return the number of columns on this board
     */
    public int getNumColumns()
    {
        return columns;
    }

    /**
     * Calculates the destination of a move from a specific position.
     * Destinations must be a valid square that can be occupied. 
     * Floor & Doorway squares can be occupied, walls cannot.
     * 
     * @param from the position from which to move
     * @param moveDirection the direction to move
     * @return valid destination or null if move is not valid 
     */
    public Position calculateDestination(Position from, MoveDirection moveDirection)
    {
        Position destination = null;

        try
        {
            // get the from row/column
            int row = from.getRow();
            int col = from.getColumn();
            
            // "blindly" calculate the destination row/column
            switch ( moveDirection )
            {
                case NORTH: row--; break;
                case EAST:  col++; break;
                case SOUTH: row++; break;
                case WEST:  col--; break;
            }
        
            // create the destination position - this might fail
            destination = new Position(from.getBoard(), row, col);
        }
        catch (NullPointerException | IllegalArgumentException e)
        {
            // either from was null, or destination is invalid
            // -> do nothing and thus return null position
        }
        
        // check if destination is a wall
        if( (destination != null) && (getSquare(destination) instanceof Wall) )
        {
            // if so, don't go there
            destination = null;
        }
        
        return destination;
    }
    
    /**
     * Prints the board to standard out.
     *
     * This exists mainly for debug purposes
     */
    public void printBoard()
    {
        final int PAD = 3;
        for ( BoardSquare[] row : squares ) 
        {
            for (BoardSquare square : row) 
            {
                String str = square.getStringRepresentation();
                String occ = square.getOccupantStringRepresentation();
                String output = str; // start with sq str;
                output += occ; // add occupant if there is one
                while ( output.length() < PAD ) 
                {
                    output += str; // fill up with square
                }
                System.out.print(output);
            }
            System.out.println();
        }
    } 
}

