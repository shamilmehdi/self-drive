/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SelfDrive;

import static SelfDrive.SelfDrive.RD;

/**
 *
 * @author shamil
 */
    public class Ride {
        int id;
        
        int a, b, x, y, s, f;
        
        public boolean taken = false;
        
        Ride() {

        }
        
        Ride(int id, int a, int b, int x, int y, int s, int f) {
            this.id = id;
            this.a = a;
            this.b = b;
            this.x = x;
            this.y = y;
            this.s = s;
            this.f = f;
        }
        
        public int dist() {
            return Math.abs(a-x) + Math.abs(b-y);
        }
    }
