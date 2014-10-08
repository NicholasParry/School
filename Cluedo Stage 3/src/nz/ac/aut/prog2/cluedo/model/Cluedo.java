package nz.ac.aut.prog2.cluedo.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Main Cluedo game class
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.03: Updated for Stage 2
 * @version 1.2 - 2012.04: Updated for Stage 3
 */
public class Cluedo
{
    //constants for managing player's action points
    public static final int MOVEMENT_COST         = 1;
    public static final int EXAMINE_COST          = 10;
    public static final int DISCOVER_GAIN         = 10;
    public static final int WRONG_ACCUSATION_COST = 20;
    
    private Board           board;
    private Player          player;
    private List<Suspect>   suspects;
    private MurderDetails   murderDescription;
    private GameState       gameState;

    /**
     * Constructor for a Cluedo game object.
     */
    public Cluedo()
    {
        board  = null;
        player = null;
        murderDescription = null;
        setupBoard();
        player = new Player("Player");
        suspects = new LinkedList<>();
        gameState = GameState.NOTSTARTED;
    }
    
    /**
     * Creates a cluedo board from data in Tudor Mansion.txt.
     */
    private void setupBoard()
    {
        board = new Board("./Tudor Mansion.txt");
    }
    
    /**
     * Starts a new cluedo game.
     */
    public void startGame()
    {
        setupBoard();
        
        // Place the player in a random room
        player.resetClues();
        BoardSquare destination = board.getEmptyRandomSquareInRoom();
        destination.addOccupant(player);
        
        // Initialise details of the murder case and create and distribute the clues
        setupCase();           
        List<Clue> clues = createClues();
        
        giveCluesToPlayer(clues);
        
        setupSuspects();
        giveCluesToSuspects(clues);
        gameState = GameState.PLAYING;
    }
    
    
    /**
     * Starts a new cluedo game that has known details for testing purposes.
     * 
     * Board layout:
     *     Col
     * Row 0 1 2 3 4 5 ...
     * 0   W W 1 1 1 W . .
     * 1   W 1 1 1 1 W . .
     * 2   1 1 1 1 1 W . .
     * 3   1 1 1 S 1 W . .
     * 4   W 1 1 1 P W . .
     * 5   W W W W D W . .
     * 6   . . . . . . . .
     * ...
     */
    public void startNonRandomTestGame()
    {
        final int ROW = 4;
        final int COLUMN = 4;
        setupBoard();
        
        player.resetClues();
        
        // Place the player in a known position in kitchen        
        Position playerPosition = new Position(board, ROW, COLUMN);
        BoardSquare destination = board.getSquare(playerPosition);
        destination.addOccupant(player);
        
        // Initialise known murder details
        murderDescription = new MurderDetails(SuspectName.GREEN, WeaponName.DAGGER, RoomName.STUDY);          
       
        //Give player clues
        player.collectClue(new Clue(SuspectName.RED));
        player.collectClue(new Clue(WeaponName.ROPE));
        player.collectClue(new Clue(RoomName.KITCHEN));
        
        //Set up a single suspect in same room as player
        Suspect suspect = new Suspect(SuspectName.WHITE);
        Position suspectPosition = new Position(board, ROW-1, COLUMN-1);
        destination = board.getSquare(suspectPosition);
        destination.addOccupant(suspect); 
        suspects.add(suspect);
        
        //Give suspect clues
        suspect.collectClue(new Clue(SuspectName.BLUE));
        suspect.collectClue(new Clue(WeaponName.SPANNER));
        suspect.collectClue(new Clue(RoomName.LOUNGE));        
       
        gameState = GameState.PLAYING;
    }    
    
    /**
     * Gets the number of rows of the board.
     * 
     * @return number of rows on board
     */
    public int getNumberOfRows()
    {
        return board.getNumRows();
    }
    
    /**
     * Gets the number of columns of the board.
     * 
     * @return number of columns on board
     */    
    public int getNumberOfColumns()
    {
        return board.getNumColumns();
    }
    
    /**
     * Gets the player instance.
     * 
     * @return the player instance
     */ 
    public Player getPlayer()
    {
        return player;
    }
    
    /**
     * Gets the player's current room.
     * 
     * @return player's current room, or null if the player is not in a room
     */
    public Room getCurrentRoom()
    {
        return board.getRoomAtPosition(player.getPosition());
    }

    /**
     * Gets the board instance. 
     * This is a shortcut method for unit tests. 
     * The board already accessible via getPlayer().getPosition().getBoard().
     * 
     * @return the board instance
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Gets a specific board square.
     *
     * @param row the row to get
     * @param col the column to get
     * @return the board square at that position or null if position is invalid
     */
    public BoardSquare getBoardSquare(int row, int col)
    {
        BoardSquare bs = null;
        try 
        {
            Position pos = new Position(board, row, col);
            bs = board.getSquare(pos);
        }
        catch (IllegalArgumentException e)
        {
            // position is invalid, do nothing and return null
        }
        return bs;
    }
    
    /**
     * @return current game state
     */
    public GameState getGameState()
    {
        return gameState;
    }
    
    /**
     * Checks if a game is in play.
     * 
     * @return true if there is a player and game state is PLAYING 
     */
    public boolean gameInPlay()
    {
         return (gameState == GameState.PLAYING) && (player != null);
    }

    /**
     * Print the details of the board and the murder case.
     * This method exists mainly for debugging purposes
     */
    public void printBoard()
    {
        board.printBoard();
        System.out.println("The case to solve is this: " +
                murderDescription.getFullDescription());       
    }
    
    /**
     * Initialises the suspects and their positions.
     */
    private void setupSuspects()
    {
        // ensure first that there are no suspects left on list
        suspects.clear();        
        for ( SuspectName suspectName : SuspectName.values() ) 
        {
            // get a random position in a room for a suspect
            BoardSquare square = board.getEmptyRandomSquareInRoom();
           
            // put suspect onto the board
            Suspect suspect = new Suspect(suspectName);
            square.addOccupant(suspect);
            
            // add to suspect array
            suspects.add(suspect);
        }
    }

    /**
     * Selects murderer, weapon, and room for case.
     */
    private void setupCase()
    {
        // select random suspect, weapon, and room
        int suspectIndex = (int) (SuspectName.values().length * Math.random());
        int weaponIndex  = (int) (WeaponName.values().length * Math.random());
        int roomIndex    = (int) (RoomName.values().length * Math.random());
        // create the correct murder details
        murderDescription = new MurderDetails(
                SuspectName.values()[suspectIndex],
                WeaponName.values()[weaponIndex],
                RoomName.values()[roomIndex]);

    }
    
    /**
     * Create all the clues needed for the game.
     * 
     * @return a list of all possible clues.
     */
    private List<Clue> createClues()
    {
        List<Clue> clues = new LinkedList<>();

        // create clues for suspects that were NOT the murderer
        for ( SuspectName suspect : SuspectName.values() ) 
        {
            if ( murderDescription.getSuspectName() != suspect )
            {
                clues.add(new Clue(suspect));
            }
        }

        // create clues for weapons that were NOT the murder weapon
        for ( WeaponName weapon : WeaponName.values() ) 
        {
            if ( murderDescription.getWeaponName() != weapon )
            {
                clues.add(new Clue(weapon));
            }
        }

        // create clues for rooms that were NOT the room the murder took place
        for ( RoomName room : RoomName.values() ) 
        {
            if ( murderDescription.getRoomName() != room )
            {
                clues.add(new Clue(room));
            }
        }
        
        // make the order of clue list random 
        Collections.shuffle(clues);
        
        return clues;
    }
    
    /**
     * Distributes the first three clues to the player.
     * The player's clues will be removed from the list.
     */     
    private void giveCluesToPlayer(List<Clue> clues)
    {
        final int NUM_CLUES = 3;
        for ( int i = 0 ; i < NUM_CLUES ; i++ )
        {
            player.collectClue(clues.remove(0));
        }
    }
    
    /**
     * Distributes clues to the suspects.
     * 
     * @param clues the list of all clues to distribute. 
     */ 
    private void giveCluesToSuspects(List<Clue> clues)
    {
        int suspectIdx = 0; // index of the suspect that gets the next clue
        for ( Clue clue : clues )
        {
            Suspect suspect = suspects.get(suspectIdx);
            suspect.collectClue(clue);
            // calculate next suspect idx (wrap around with number of suspects)
            suspectIdx = (suspectIdx + 1) % suspects.size();
        }
    }
    
    // ------------------------------ Movement related methods -------------------------------------
    
    /**
     * Checks if this move is valid.
     * 
     * @param direction the direction to move the player
     * @return true if the player move in this direction is possible
     */
    public boolean moveIsPossible(MoveDirection direction)
    {
        Position newPosition  =  board.calculateDestination(player.getPosition(), direction);
        boolean  validMoveDirection = (newPosition != null);
        boolean  enoughPoints = (player.getActionPoints() >= MOVEMENT_COST);
        return gameInPlay() && validMoveDirection && enoughPoints;
    }
    
    /**
     * Moves the player in a specific direction.
     * 
     * @param direction the direction to move the player
     * @return true if the player move in this direction was possible
     */
    public boolean movePlayer(MoveDirection direction)
    {
        boolean success = false;
        if ( moveIsPossible(direction) )
        {
            // remove player from current square
            Position    from       = player.getPosition();
            BoardSquare fromSquare = board.getSquare(from);
            fromSquare.removeOccupant(player);
            
            // calculate destination
            Position    to       = board.calculateDestination(from, direction);
            BoardSquare toSquare = board.getSquare(to);
            // add player to the destination square
            toSquare.addOccupant(player);
            
            //Will this move cost the player any points?
            if ( board.getRoomAtPosition(to) == null )
            {
                // player is NOT inside a room: movement costs
                player.removeActionPoints(MOVEMENT_COST);
                if(player.getActionPoints() <= 0)
                {
                    gameState = GameState.LOST;
                }
             }
            success = true;
        }
        return success;
    }
    
    //======================================== Clue related methods =============================================
    
    /**
     * A clue is available to the player if:
     * - the game is in play. 
     * - there is at least one suspect in the same room as the player.
     * - the player has enough points to examine a clue.
     * 
     * @return true if a clue is available
     */
    public boolean isClueAvailable()
    {
        boolean suspectInRoom = false;
        Room room = getCurrentRoom();
        if ( room != null )
        {
            suspectInRoom = room.containsSuspect();        
        }
        return gameInPlay() &&
               suspectInRoom &&
               (player.getActionPoints() >= EXAMINE_COST);    
    }

    /**
     * Gets a random clue from a suspect in the same room as the player.
     * A random suspect is chosen if there is more than one in the room.
     * That clue is added to the clues held by the player.
     * 
     * @return the clue that the player has just collected, 
     *         or null if no new clue was collected
     */
    public Clue getClueForPlayer()
    {
        Clue collectedClue = null;
        
        // update actionPoints to remove cost of getting a clue
        player.removeActionPoints(EXAMINE_COST);
        
        //Get a clue if available
        if ( isClueAvailable() )
        {
            Room     room       = board.getRoomAtPosition( player.getPosition());
            Suspect  suspect    = room.getRandomSuspect();
            Clue     randomClue = null;
            
            // is there at least one suspect in the room
            if ( suspect != null )
            {
                // there is a random suspect: get a random clue
                randomClue = suspect.getRandomClue();
            }

            // is this a new clue?
            boolean newClue = false;
            if ( randomClue != null )
            {
                // try to collect it -> false indicates a duplicate = no new clue
                newClue = player.collectClue(randomClue);
            }
            if ( newClue )
            {
                // it was a new clue: return it
                collectedClue = randomClue;
            }
        }
        
        // give actionPoint bonus if a new clue was collected
        if ( collectedClue != null )
        {
            player.addActionPoints(DISCOVER_GAIN);
        }
       
        return collectedClue;        
    }
    
    /**
     * Check if it is possible to make an accusation.
     * An accusation can be made if:
     * - the game is in play. 
     * 
     * @return true if an accusation is possible
     */
    public boolean isAccusationPossible()
    {
        return gameInPlay();  
    }

    /**
     * Makes an accusation.
     * 
     * @param accusation the accusation that is made
     * @return true if the accusation is correct and the game is therefore won
     */
    public boolean makeAccusation(MurderDetails accusation)
    {
        boolean correct = false;
       
        // check if accusation is possible
        if ( isAccusationPossible() )
        {
            correct = accusation.equals(this.murderDescription);
            
            // a correct accusation wins game
            if ( correct )
            {
                gameState = GameState.WON;
            }
            else
            {
                // incorrect accusations are expensive
                player.removeActionPoints(WRONG_ACCUSATION_COST);
            }
        }
        return correct;
    }
    
    /** 
     * Quits the game.
     */
    public void quitGame()
    {
        gameState = GameState.QUIT;
    }
    
    
    /**
     * Method used to take commands as String and exitcute them on the game
     * eg: sg = start game
     * 
     * Many functions that reqire a screen update already return stuff so we have to find another way to
     * return the screen update linked list
     * 
     * @param input String input
     * @return a linked list of screen updates
     */
    public Set takeInput(String input){
        switch(input){
            case "sg":
                startGame();
                return getFullUpdate();
            case "mp-n":
                
        }
        return null;
    }
    
    public Set getFullUpdate(){
        return null; //todo
    }
}
