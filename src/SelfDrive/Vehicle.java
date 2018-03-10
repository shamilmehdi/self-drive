/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SelfDrive;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shamil
 */
public class Vehicle {
    // Id of vehicle
    int id;
    
    // Row coordinate
    public int X;
    
    // Column coordinate
    public int Y;
    
    // Time steps
    public int T;
    
    public List<Integer> Rides = new ArrayList<Integer>();
    
    public Vehicle() {
        
    }
    
    public Vehicle(int _id) {
        this.id = _id;
        this.X = 0;
        this.Y = 0;
        this.T = 0;
    }
    
    public void SetCoord(int x, int y) {
        X = x;
        Y = y;
    }
    
    public boolean CanTakeRideBonus(Ride r) {
        return T + DistanceToRide(r) <= r.s;
    }
    
    public int DistanceToRide(Ride r) {
        return Math.abs(X-r.a) + Math.abs(Y-r.b);
    }
    
    public int RideCost(Ride r) {
        return DistanceToRide(r) + WaitTimeForRide(r) + r.dist();
    }
    
    public void RideTo(Ride r) {
        Rides.add(r.id);
        T += RideCost(r);
        SetCoord(r.x, r.y); 
    }
    
    public int WaitTimeForRide(Ride r) {
        return Math.max(0, r.s - (T + DistanceToRide(r)));
    }
    
    public boolean CanTake(Ride r) {
        return T + DistanceToRide(r) + r.dist() <= r.f ;
    }
    
    public int GetScoreForRide(Ride r, int BonusScore) {        
        if(CanTake(r)) {
            return (CanTakeRideBonus(r) ? BonusScore : 0) - WaitTimeForRide(r) - DistanceToRide(r);
        }
        
        return -RideCost(r);
    }
}
