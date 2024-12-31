import java.util.*;
import java.util.stream.Collectors;
import java.text.*;


public class WorkoutsByWeek {

   private Map<String, List<Workout>> groupedWorkouts = new HashMap<>();
   private SimpleDateFormat weekFormatter = new SimpleDateFormat("MM-dd-yyyy");
   
   public WorkoutsByWeek() { }

   /**
    * Group workouts by week where each week starts on Monday and ends on Sunday
    *
    * @param workouts List of Workout objects to be grouped
    */
   public void groupWorkoutsByWeek(List<Workout> workouts) {
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
    */
   public Map<String, List<Workout>> getGroupedWorkouts() {
       return groupedWorkouts;
   }

  
}
