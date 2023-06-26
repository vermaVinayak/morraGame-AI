package nz.ac.auckland.se281.players;

import java.util.ArrayList;
import nz.ac.auckland.se281.strategies.Strategy;
import nz.ac.auckland.se281.strategies.StrategyAverage;
import nz.ac.auckland.se281.strategies.StrategyRandom;
import nz.ac.auckland.se281.strategies.StrategyTop;

public class AiMaster implements Player {
  private String name;
  private Strategy strategy;
  private int accumulatedPoints;

  public AiMaster(String name) {
    this.name = name;
    // Initially make use on random Strategy
    this.setStrategy(new StrategyRandom());
    this.accumulatedPoints = 0;
  }

  @Override
  public String getMove(int roundTracker, ArrayList<Integer> numFingersHistory) {
    // implement AI hard strategy
    if (roundTracker > 3) {
      if (this.strategy instanceof StrategyRandom) {
        this.setStrategy(new StrategyAverage());
      } else if (this.strategy instanceof StrategyAverage) {
        this.setStrategy(new StrategyTop());
      } else {
        // current strategy is Top so change to average strategy
        this.setStrategy(new StrategyAverage());
      }
    }
    return this.strategy.generateMove(roundTracker, numFingersHistory);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getAccumulatedPoints() {
    return this.accumulatedPoints;
  }
  
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void incrementAccumulatedPoints() {
    // increase the count of games won.
    this.accumulatedPoints++;
  }

}
