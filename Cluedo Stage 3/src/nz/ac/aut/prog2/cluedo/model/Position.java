package nz.ac.aut.prog2.cluedo.model;

/**
 * Represents the location of any square/occupant in the game.
 * Positions must always be valid for the particular game board.
 *
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2011.03: Created
 * @version 2.0 - 2011.06: Adapted for Lemur Island
 * @version 3.0 - 2012.02: Adapted for Cluedo game
 * @version 3.1 - 2012.03: Fixed constructor bug
 * @version 3.2 - 2012.04: Updated for assignment stage 3
 */
public class Position 
{
    // the board for which the position is valid
    private Board board;

    // the row number for this position
    private int row;

    // the column number for this position
    private int column;
       
    /**
     * Creates a new position instance for a specific board
     * 
     * @param row the row part of the position
     * @param column the column part of the position
     *
     * @throws IllegalArgumentException when the position is not valid on the board 
     *                                  or a null board has been passed
     */
    public Position(Board board, int row, int column) 
            throws IllegalArgumentException
    {
        // check vor valid board parameter
        if ( board == null )
        {
            throw new IllegalArgumentException(
                    "Cannot create a board position without a board reference.");
        }
        
        // check for valid row
        int maxRow = board.getNumRows() - 1;
        if ( (row < 0) || (row > maxRow) )
        {
            throw new IllegalArgumentException(
                    row + " is invalid. Must be between 0 to " + 
                    maxRow + " inclusive.");
        }
        
        // check for valid column
        int maxCol = board.getNumColumns() - 1;
        if ( (column < 0) || (column > maxCol) )
        {
            throw new IllegalArgumentException(
                    column + " is invalid. Must be between 0 to " + 
                    maxCol + " inclusive.");
        }
        
        // all fine -> create the position instance
        this.row    = row;
        this.column = column;
        this.board  = board;
    }
    
    /**
     * Gets the row number of this position.
     * 
     * @return row number
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Gets the column number of this position.
     * 
     * @return column number
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Get the board for this position
     * 
     * @return board associated with this position
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Override equals so that positions can be correctly compared.
     * 
     * @param position to compare with this one
     * @return true if other position has the same coordinates on the same board
     */
    @Override
    public boolean equals(Object other)
    {
        boolean equals = false;
        if ( other == this )
        {
            equals = true;
        }
        else if ( other instanceof Position )
        {
            Position pos = (Position) other;
            equals = (pos.row    == this.row) && 
                     (pos.column == this.column) &&
                     (pos.board  == this.board);
        }
        return equals;
    }    
}