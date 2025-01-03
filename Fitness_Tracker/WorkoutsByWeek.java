import java.util.*;
import java.util.stream.Collectors;
import java.text.*;


public class WorkoutsByWeek {

   private Map<String, List<Workout>> groupedWorkouts = new HashMap<>();
   private SimpleDateFormat weekFormatter = new SimpleDateFormat("MM-dd-yyyy");
    
   public WorkoutsByWeek() throws ParseException {
        this.groupWorkoutsByWeek(WorkoutUtils.getWorkouts());
    }

   /**
    * Group workouts by week where each week starts on Monday and ends on Sunday
    *
    * @param workouts List of Workout objects to be grouped
    */
   private void groupWorkoutsByWeek(List<Workout> workouts) {
       Calendar cal = Calendar.getInstance();
       for (Workout workout : workouts) {
           cal.setTime(workout.getWorkoutDate());
           cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
           String weekStart = weekFormatter.format(cal.getTime());
           groupedWorkouts.computeIfAbsent(weekStart, k -> new ArrayList<>()).add(workout);
       }
   }
     
   /**
    * Get the grouped workouts
    *
    * @return A Map where the key is the starting date of the week and the value is the list of workouts in that week
    
   public Map<String, List<Workout>> getGroupedWorkouts() {
       return groupedWorkouts;
   }
   */
  
   public List<Workout> getWorkoutByWeek(String dateStr) throws ParseException {
        return this.groupedWorkouts.get(this.getMondayOfWeek(dateStr));   
   }
   
  
    /**
     * Get the Monday of the week for the given date string
     *
     * @param dateString Date string in the format "MM-dd-yyyy"
     * @return Monday of the week as a string in the format "MM-dd-yyyy"
     * @throws ParseException If the date string is not in the correct format
     */
    public  String getMondayOfWeek(String dateString) throws ParseException {
        Date date = weekFormatter.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        // Set the calendar to the previous or same Monday
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return weekFormatter.format(cal.getTime()); 
    }
    
    public void addWorkout(Workout workout) {
        try
        {
            String monStr = this.getMondayOfWeek( 
            this.weekFormatter.format(workout.getWorkoutDate()));
                
            this.groupedWorkouts.get(monStr).add(workout);
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
                
    }
    
    public void saveWorkouts() {
        ArrayList<Workout> workouts = new ArrayList<>();
        this.groupedWorkouts.forEach((k,v) -> workouts.addAll(v) );
        // sort
        
        WorkoutUtils.saveWorkouts(workouts);
    }
    
    
}
