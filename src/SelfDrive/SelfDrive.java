/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SelfDrive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javafx.util.Pair;

/**
 *
 * @author shamil, shamil.mehdiyev@gmail.com, Score : 49244166
 */
public class SelfDrive {
   
    public static int R, C, F, N, B, T;
    
    public static List<Ride> RD;
    public static List<Vehicle> VH;
    
    public static List<Ride> RD_done;
    public static List<Vehicle> VH_done;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {       
        List<Pair<String, String>> inputs = new ArrayList<>();
        inputs.add(new Pair("a_example.in", "a_example.out"));
        inputs.add(new Pair("b_should_be_easy.in", "b_should_be_easy.out"));
        inputs.add(new Pair("c_no_hurry.in", "c_no_hurry.out"));
        inputs.add(new Pair("d_metropolis.in", "d_metropolis.out"));
        inputs.add(new Pair("e_high_bonus.in", "e_high_bonus.out"));
        
        for(Pair<String, String> input : inputs) {
            Solve(input.getKey(), input.getValue());
        }
    }
    
    public static void Solve(String inputFile, String outputFile) throws FileNotFoundException, IOException {
        RD = new ArrayList<>();
        VH = new ArrayList<>();

        RD_done = new ArrayList<>();
        VH_done = new ArrayList<>();
    
        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        StringTokenizer tk = new StringTokenizer(in.readLine());
        R = parseInt(tk.nextToken());
        C = parseInt(tk.nextToken());
        F = parseInt(tk.nextToken());
        N = parseInt(tk.nextToken());
        B = parseInt(tk.nextToken());
        T = parseInt(tk.nextToken());
           
        for(int i=0; i<N; i++) {
            tk = new StringTokenizer(in.readLine());
            Ride r = new Ride();
            r.id = i;
            r.a = parseInt(tk.nextToken());
            r.b = parseInt(tk.nextToken());
            r.x = parseInt(tk.nextToken());
            r.y = parseInt(tk.nextToken());
            r.s = parseInt(tk.nextToken());
            r.f = parseInt(tk.nextToken());
            RD.add(r);
        }
        
        for(int i=0; i<F; i++) {
            VH.add(new Vehicle(i));
        }
        
        PrintWriter pr = new PrintWriter(new File(outputFile));
        String newLine = System.getProperty("line.separator");
        
        List<Vehicle> VehicleRemoveList;
        
        // While we have vehicle and Ride to process
        while(!VH.isEmpty() && !RD.isEmpty()) {
            VehicleRemoveList = new ArrayList<>();
            
            // For each vehicle
            for(Vehicle v : VH) {
                
                Ride BestRide = null;
                int MaxScore = Integer.MIN_VALUE;
                
                // Find best Ride for this vehicle
                for(Ride rd : RD) {
                    int score = v.GetScoreForRide(rd, B);
                    
                    if(v.CanTake(rd) && score > MaxScore) {
                        BestRide = rd;
                        MaxScore = score;
                    }
                }
                
                if(BestRide != null) {
                    RD_done.add(BestRide);
                    RD.remove(BestRide);
                    v.RideTo(BestRide);
                }
                
                if(BestRide == null) {
                    VH_done.add(v);
                    VehicleRemoveList.add(v);
                }
            }
            
            VH.removeAll(VehicleRemoveList);
        }
        
        // Print Results
        int i=0;
        for(Vehicle v: VH) {
            i++;
            System.out.print(v.Rides.size());
            pr.write(v.Rides.size()+"");
            for(int rideId : v.Rides) {
                pr.write(" " + rideId);
                System.out.print(" " + rideId);
            }
            
            if(i < F) {
                pr.write(newLine);
                System.out.println();
            }
        }
            
        for(Vehicle v: VH_done) {
            i++;
            System.out.print(v.Rides.size());
            pr.write(v.Rides.size()+"");
            for(int rideId : v.Rides) {
                pr.write(" " + rideId);
                System.out.print(" " + rideId);
            }
            
            if(i < F) {
                pr.write(newLine);
                System.out.println();
            }
        }

        System.out.println();
       
        pr.close();
        
        /*System.out.println(R+" "+C+" "+F+" "+N+" "+B+" "+T);
        for(int i=0; i<N; i++) {
            System.out.println(RD[i].a+" "+RD[i].b+" "+RD[i].x+" "+RD[i].y+" "+RD[i].s+" "+RD[i].f);
        }*/
    }
}
