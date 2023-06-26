package nz.ac.auckland.se281.strategies;

import java.util.ArrayList;
import nz.ac.auckland.se281.Utils;

public class StrategyRandom implements Strategy {

  @Override
  public String generateMove(int roundTracker, ArrayList<Integer> numFingersHistory) {
    // implement Random strategy
    int numFingersAi = Utils.getRandomNumber(1, 5);
    int guessedSumAi = Utils.getRandomNumber(numFingersAi + 1, numFingersAi + 5);
    return Integer.toString(numFingersAi) + " " + Integer.toString(guessedSumAi);
  }
}
