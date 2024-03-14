//Meetkumar Saspara
//115971301
//R:01
//HW4

import java.util.Arrays;
import java.util.LinkedList;

/**
 * The RandomGenerator class provides methods for selecting random elements from a list based on given probabilities.
 */
public class RandomGenerator {

  /**
   * Selects a random ride from the provided list of rides based on the given probabilities.
   *
   * @param rides        A LinkedList of Ride objects from which to select.
   * @param probabilities An array of probabilities corresponding to the likelihood of selecting each ride.
   *                     The probabilities should sum up to 1.
   * @return The selected Ride object, or null if the list of rides is empty or probabilities are not valid.
   */
  public static Ride selectRide(
    LinkedList<Ride> rides,
    double[] probabilities
  ) {
    // Calculate a random value within the sum of probabilities
    double rideRandom = Math.random() * Arrays.stream(probabilities).sum();
    // Iterate through the rides and select based on probabilities
    for (int i = 0; i < probabilities.length; i++) {
      rideRandom -= probabilities[i];
      if (rideRandom <= 0) {
        return rides.get(i);
      }
    }
    // Return null if no ride was selected
    return null;
  }
}
