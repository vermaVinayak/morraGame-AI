package nz.ac.auckland.se281.players;

import java.util.ArrayList;
import nz.ac.auckland.se281.strategies.Strategy;

public interface Player {
  public String getMove(int roundTracker, ArrayList<Integer> numFingersHistory);

  public String getName();

  public void setStrategy(Strategy strategy);

  public void incrementAccumulatedPoints();

  public int getAccumulatedPoints();
}
