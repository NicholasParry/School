/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.prog2.cluedo.model.ScreenUpdates;

import nz.ac.aut.prog2.cluedo.model.Position;

/**
 *
 * @author Nick
 */
public class MovementUpdate extends ScreenUpdate{
    private Position position;
    private boolean addPlayer;
    
    public MovementUpdate(Position _position, Boolean _addPlayer){
        position = _position;
        addPlayer = _addPlayer;
    }

    public Position getPosition() {
        return position;
    }
    
    public boolean isAddPlayer(){
        return addPlayer;
    }
    
}
