import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Workout here.
 *
 * @author cee-vance
 * @version 1.0.0
 */
public class Workout implements Comparable<Workout>
{
   private int mins;
   private Date workoutDate;
   private  WorkoutType workoutType;
   
   private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
   
   public Workout( WorkoutType workoutType,int mins, Date workoutDate) {
       this.setWorkoutType(workoutType);
       this.setMins(mins);
       this.setWorkoutDate(workoutDate);
   }
   
   public Workout(WorkoutType workoutType,int mins){    
       this(workoutType,mins,new Date());
   }
   
   /*
    * Getters and setters
    */
   public WorkoutType getWorkoutType() {
       return this.workoutType;
   }
   
   public int getMins() {
       return this.mins;
   }
   
   public Date getWorkoutDate() {
       return this.workoutDate;
   }
   
   public void setWorkoutType(WorkoutType workoutType) {
       this.workoutType = workoutType;
   }
   
   public void setMins(int mins) {
       if(mins <= 0) {
           throw new IllegalArgumentException("Workout minutes must be greater than zero.");
       }
       this.mins = mins;
   }
   
   public void setWorkoutDate(Date workoutDate) {
       this.workoutDate = workoutDate;
   }
   
   @Override
   public int compareTo(Workout other) {
       return this.getWorkoutDate().compareTo(other.getWorkoutDate());
   }
   
   @Override
   public String toString() {
       return String.format("Type: %s Date: %s Duration (Mins): %d",
       this.getWorkoutType(),
       this.formatter.format(this.getWorkoutDate()),
       this.getMins());
   }
   
   public String workoutLine(){
       return String.format("%s,%d,%s",this.getWorkoutType(),
                           this.getMins(),
                           this.formatter.format(this.getWorkoutDate()));
   }
}
