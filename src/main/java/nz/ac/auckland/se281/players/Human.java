package nz.ac.auckland.se281.players;

import java.util.ArrayList;
import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Utils;
import nz.ac.auckland.se281.strategies.Strategy;

public class Human implements Player {

  private String name;
  private ArrayList<Integer> numFingersHistory;
  private int accumulatedPoints;

  public Human(String name) {
    this.name = name;
    this.numFingersHistory = new ArrayList<Integer>();
    this.accumulatedPoints = 0;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getAccumulatedPoints() {
    return this.accumulatedPoints;
  }

  public ArrayList<Integer> getMoveHistory() {
    return this.numFingersHistory;
  }

  public String getMove(int roundTracker, ArrayList<Integer> numFingersHistory) {
    // keep checking for valid inputs
    String input;
    Boolean invalidInput = false;
    do {
      if (invalidInput) {
        MessageCli.INVALID_INPUT.printMessage();
      }
      MessageCli.ASK_INPUT.printMessage();
      input = Utils.scanner.nextLine();
      invalidInput = checkInput(input);
    } while (invalidInput);

    // Add numFingers of current move to history
    String[] splittedInput = input.split(" ");
    this.addMoveToHistory(Integer.parseInt(splittedInput[0]));

    return input;
  }

  private boolean checkInput(String input) {
    // Checks whether inputs during the execution of the play method are valid.
    //
    // Return
    //  true - if values are invalid; false otherwise.

    // check if number of fingers are valid
    try {
      // fetch num. of fingers.
      String numFingersString = input.split(" ")[0];
      int numFingers = Integer.parseInt(numFingersString);

      if (numFingers < 1 | numFingers > 5) {
        // Number of fingers not in range.
        return true;
      }
    } catch (Exception e) {
      // could not convert input to integer.
      return true;
    }

    // check if sum is valid
    try {
      // Fetch sum amount.
      String sumString = input.split(" ")[1];
      int numSum = Integer.parseInt(sumString);

      if (numSum < 1 | numSum > 10) {
        // sum is not in range.
        return true;
      }
    } catch (Exception e) {
      // could not convert input to integer.
      return true;
    }
    return false;
  }

  @Override
  public void setStrategy(Strategy strategy) {
    // Humans dont need a strategy 😛
    return;
  }

  public void addMoveToHistory(int numFinger) {
    this.numFingersHistory.add(numFinger);
  }

  

  @Override
  public void incrementAccumulatedPoints() {
    // increase the count of games won.
    this.accumulatedPoints++;
  }

}
