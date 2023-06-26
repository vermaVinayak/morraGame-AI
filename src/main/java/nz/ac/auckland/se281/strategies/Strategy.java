package nz.ac.auckland.se281.strategies;

import java.util.ArrayList;

public interface Strategy {
  public String generateMove(int roundTracker, ArrayList<Integer> numFingersHistory);
}
