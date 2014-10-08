package nz.ac.aut.prog2.cluedo.model;

/**
 * Suspect represents someone who could have murdered Dr Black.
 * 
 * @author Anne Philpott and Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class Suspect extends Occupant 
{
    // the name of the suspect
    private SuspectName name;

    /**
     * Creates a suspect.
     * 
     * @param name the name of the suspect
     */
    public Suspect(SuspectName name)
    {
        super();
        this.name = name;
    }
   
    /**
     * Gets the suspect's name.
     * 
     * @return suspect's name.
     */
    public SuspectName getName()
    {
        return this.name;
    } 
    
    /**
     * Gets a random clue from the collected clues.
     * 
     * @return a random clue from clues
     */
    public Clue getRandomClue()
    {
        Clue   clue     = null;
        Clue[] arrClues = getCluesAsArray();
        if ( arrClues.length > 0 )
        {
            int clueIndex = (int) (arrClues.length * Math.random());   
            clue = arrClues[clueIndex];
        }
        return clue;
    }

    @Override
    public String getStringRepresentation()
    {
        return "S";
    }
    
}
