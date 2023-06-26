package nz.ac.auckland.se281.strategies;

import java.util.ArrayList;
import nz.ac.auckland.se281.Utils;

public class StrategyTop implements Strategy {

  @Override
  public String generateMove(int roundTracker, ArrayList<Integer> numFingersHistory) {
    // implement Average strategy
    int numFingersAi = Utils.getRandomNumber(1, 5);

    // create an dummy array of size 5
    // each element represents the frequenct of fingers that range from 1 to 5
    // Index 0 is not used as you cannot do a move of 0 fingers
    // array of primitive integer is loaded with zeros by default
    int[] frequencyMap = new int[6];

    // Count the frequencies of performed moves
    for (int move : numFingersHistory) {
      frequencyMap[move]++;
    }

    // Get the highest performed move
    int mostFrequentMove = 1;
    for (int i = 2; i < frequencyMap.length; i++) {
      if (frequencyMap[i] > frequencyMap[mostFrequentMove]) {
        mostFrequentMove = i;
      }
    }
    // calculate guessed sum for top
    int guessedSumAi = numFingersAi + mostFrequentMove;

    return Integer.toString(numFingersAi) + " " + Integer.toString(guessedSumAi);
  }
}
