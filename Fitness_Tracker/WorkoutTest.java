

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * The test class WorkoutTest.
 *
 * @author  cee-vance
 * @version 1.0.0
 */
public class WorkoutTest
{
    private final String TOSTRING = "Type: CARDIO Date: 01-01-2024 Duration (Mins): 15";
    
    @Test
    public void TestConstructor_Success() throws java.text.ParseException{
            Workout workout = new Workout(WorkoutType.CARDIO,15);
            Workout workout2 = new Workout(WorkoutType.STRENGTH_TRAINING_UPPER,
            15,
            WorkoutUtils.dateFromString("1-1-2024"));
            
            assertNotNull(workout);
            assertNotNull(workout2);
    }
    
    @Test
    public void TestConstructor_ErrorNegativeMins() {
        assertThrows(IllegalArgumentException.class,
        ()->{new Workout(WorkoutType.CARDIO,0);});
        
        assertThrows(IllegalArgumentException.class,
        ()->{new Workout(WorkoutType.CARDIO,-1);});
    }
    
    @Test
    public void TestToString_Success() throws java.text.ParseException {
        Workout workout = new Workout(WorkoutType.CARDIO,15, WorkoutUtils.dateFromString("1-1-2024"));
        
        assertEquals(TOSTRING,workout.toString());
    }
    
    @Test
    public void TestCompareTo_Success() throws java.text.ParseException {
        
        
        Workout workout1st = new Workout(WorkoutType.CARDIO,
                                            30,
                                            WorkoutUtils.dateFromString("1-1-2024"));
                                            
        Workout workout2nd = new Workout(WorkoutType.CARDIO,
                                            30,
                                            WorkoutUtils.dateFromString("1-2-2024"));
                                            
        Workout workout3rd = new Workout(WorkoutType.CARDIO,
                                            30,
                                            WorkoutUtils.dateFromString("1-3-2024"));
                                            
        ArrayList<Workout> workouts = new ArrayList<>();
        
        // add workouts out of order
        workouts.add(workout2nd);
        
        workouts.add(workout1st);
        
        workouts.add(workout3rd);
        
        // sort
        Collections.sort(workouts);
        
        // check that workouts are in descending order by date
        assertEquals(workout1st.getWorkoutDate(),
                    workouts.get(0).getWorkoutDate());
                    
        assertEquals(workout2nd.getWorkoutDate(),
                    workouts.get(1).getWorkoutDate());
                    
        assertEquals(workout3rd.getWorkoutDate(),
                    workouts.get(2).getWorkoutDate());
                                
    }
        
    
}
