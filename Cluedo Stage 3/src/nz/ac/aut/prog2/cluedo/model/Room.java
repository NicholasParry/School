package nz.ac.aut.prog2.cluedo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents rooms in Tudor Mansion.
 * 
 * @author Anne Philpot and Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class Room
{
    // the name of the room
    private RoomName name;

    // a list of board squares inside the room
    private Collection<BoardSquare> boardSquares;
    
    /**
     * Create a new room with a specific name.
     * 
     * @param name the name of the room.
     */
    public Room(RoomName name) 
    {
        this.name         = name;
        this.boardSquares = new ArrayList<>();
    }

    /**
     * Gets the name of the room.
     * 
     * @return name of the room
     */
    public RoomName getName()
    {
        return name;
    }

    /**
     * Adds a square to the list of board squares that the room contains.
     * 
     * @param square the board square to add
     * @return true if square is added, false if not
     */
     public boolean addBoardSquare(BoardSquare square)
     {
        return boardSquares.add(square);
     }
    
    /**
     * Checks if a specific position is inside of the room.
     * 
     * @param pos the position to check
     * @return true if the position is in the room, false if not
     */
    public boolean containsPosition(Position pos)
    {
        boolean contains = false;
        for ( BoardSquare boardSquare : boardSquares )
        {
            if ( boardSquare.getPosition().equals(pos) )
            {
                contains = true;
            }
        }
        return contains;
    }
    
    /**
     * Checks if a room contains a suspect.
     *
     * @return true if this room contains at least one suspect
     */
    public boolean containsSuspect()
    {
        boolean suspectInRoom = false;
    
        for ( BoardSquare boardSquare : boardSquares )
        {
            Suspect suspect = boardSquare.getSuspect();
            if ( suspect != null )
            {
                suspectInRoom = true;
            }
        }
        
        return suspectInRoom;
    } 
    
    /**
     * Returns an array of suspects in the room.
     * 
     * @return an array of suspects in the room
     */
    public Suspect[] getSuspectsAsArray()
    {
        List<Suspect> suspects = new LinkedList<>();
        for ( BoardSquare boardSquare : boardSquares )
        {
            Suspect s = boardSquare.getSuspect();
            if ( s != null )
            {
                suspects.add(s);
            }
        }
        return suspects.toArray(new Suspect[suspects.size()]);
    }   
    
    /**
     * Returns a random suspect, if there are any.
     *
     * @return a random suspect, or null if there is none in the room
     */
    public Suspect getRandomSuspect()
    {
        Suspect suspect = null;
        Suspect[] suspects = getSuspectsAsArray();
        if ( suspects.length > 0 )
        {
            int idx = (int) (Math.random() * suspects.length);
            suspect = suspects[idx];
        }
        return suspect;
    } 
    
}

