package nz.ac.auckland.se281.strategies;

import java.util.ArrayList;
import nz.ac.auckland.se281.Utils;

public class StrategyAverage implements Strategy {

  @Override
  public String generateMove(int roundTracker, ArrayList<Integer> numFingersHistory) {
    // implement Average strategy
    int numFingersAi = Utils.getRandomNumber(1, 5);

    // calcuate average of human finger moves
    float sum = 0;
    for (Integer numFinger : numFingersHistory) {
      sum += numFinger;
    }
    // calculate AI guessed sum
    float guessedSumAi = numFingersAi + sum / numFingersHistory.size();
    guessedSumAi = Math.round(guessedSumAi);

    // back to integer conversion
    int guessedSumAiInt = (int) guessedSumAi;

    return Integer.toString(numFingersAi) + " " + Integer.toString(guessedSumAiInt);
  }
}
