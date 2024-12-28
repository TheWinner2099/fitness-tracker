import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Write a description of class WorkoutIO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WorkoutUtils
{
    // instance variables - replace the example below with your own
    private static final String workoutPath = "workouts.txt";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
    
    public static ArrayList<Workout> getWorkouts()  throws java.text.ParseException {
        File workoutFile = new File(workoutPath);
        Scanner scanFile;
        
        // initalize workouts
        ArrayList<Workout> workouts = new ArrayList<>();
        try {
            
        scanFile = new Scanner(workoutFile);
        
        }catch(FileNotFoundException exc) {
            System.out.println("File not found");
            return workouts;
        }
        
        // read the workouts
        while(scanFile.hasNextLine()){
            String line = scanFile.nextLine();
            
            // in case this is the last line
            if(line.isBlank())
                break;
                
            String[] tokens = line.split(",");
            
            Workout workout = new Workout( WorkoutType.valueOf(tokens[0]),
                                            Integer.parseInt(tokens[1]),
                                            formatter.parse(tokens[2]));
                                            
            workouts.add(workout);
            
        }
        
        scanFile.close();
        return workouts;
        
    }
    
    public static void saveWorkouts(ArrayList<Workout> workouts) {
        File workoutFile = new File(workoutPath);
        
        PrintWriter writer;
        
        try {
           writer = new PrintWriter(workoutFile);
        } catch(FileNotFoundException exc) {
            System.out.println("File not found");
            return;
        }
        
        for(Workout workout: workouts) {
            
            writer.println(workoutToCsvLine(workout));
        }
        
        writer.close();
        
    }
    
    public static String workoutToCsvLine(Workout workout){
       return String.format("%s,%d,%s",workout.getWorkoutType(),
                           workout.getMins(),
                           formatter.format(workout.getWorkoutDate()));
   }
   
   public static Date dateFromString(String date)  throws java.text.ParseException {
       
        return formatter.parse(date);
   }
    
}
