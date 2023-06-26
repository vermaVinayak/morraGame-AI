package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.players.Human;
import nz.ac.auckland.se281.players.Player;
import nz.ac.auckland.se281.players.PlayerFactory;

public class Morra {

  private int roundTracker;
  private Player humanPlayer;
  private Player aiPlayer;
  private ArrayList<Integer> humanMovesHistory;
  private Boolean newGameCreated = false;
  private int pointsToWin;

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    // Initialize players :)
    PlayerFactory playerFactory = new PlayerFactory();
    this.humanPlayer = new Human(options[0]);
    this.aiPlayer = playerFactory.createPlayer(difficulty, "Jarvis");

    // Reset game data
    this.humanMovesHistory = new ArrayList<Integer>();
    this.roundTracker = 0;
    this.pointsToWin = pointsToWin;

    // print welcome message
    MessageCli.WELCOME_PLAYER.printMessage(this.humanPlayer.getName());

    this.newGameCreated = true;
  }

  public void play() {

    // check whether new game is initialised
    if (this.newGameCreated == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    // increment the number of times game is played.
    this.roundTracker++;

    // print round information
    MessageCli.START_ROUND.printMessage(Integer.toString(this.roundTracker));

    // print human player play info
    String[] humanInputs =
        this.humanPlayer.getMove(this.roundTracker, this.humanMovesHistory).split(" ");
    MessageCli.PRINT_INFO_HAND.printMessage(
        this.humanPlayer.getName(), humanInputs[0], humanInputs[1]);

    // print AI player play info
    String[] aiInputs = this.aiPlayer.getMove(this.roundTracker, this.humanMovesHistory).split(" ");
    MessageCli.PRINT_INFO_HAND.printMessage(this.aiPlayer.getName(), aiInputs[0], aiInputs[1]);

    // Decide on round winner
    this.decideWinner(humanInputs[0], aiInputs[0], humanInputs[1], aiInputs[1]);

    // Store current human finger move to history
    this.humanMovesHistory.add(Integer.parseInt(humanInputs[0]));

    // Check whether either player has won the whole game
    this.handleEndGame();
  }

  public void showStats() {
    if (this.newGameCreated == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // show human stats
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        this.humanPlayer.getName(),
        Integer.toString(this.humanPlayer.getAccumulatedPoints()),
        Integer.toString(this.pointsToWin - this.humanPlayer.getAccumulatedPoints()));

    // show AI stats
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        this.aiPlayer.getName(),
        Integer.toString(this.aiPlayer.getAccumulatedPoints()),
        Integer.toString(this.pointsToWin - this.aiPlayer.getAccumulatedPoints()));
  }

  // helper methods

  private void handleEndGame() {
    if (this.humanPlayer.getAccumulatedPoints() == this.pointsToWin) {
      // Declare victory for human!
      MessageCli.END_GAME.printMessage(
          this.humanPlayer.getName(), Integer.toString(this.roundTracker));
      // force player to have to reset game
      this.newGameCreated = false;
    } else if (this.aiPlayer.getAccumulatedPoints() == this.pointsToWin) {
      // Declare victory for AI!
      MessageCli.END_GAME.printMessage(
          this.aiPlayer.getName(), Integer.toString(this.roundTracker));
      // force player to have to reset game
      this.newGameCreated = false;
    }
  }

  private void decideWinner(
      String numFingersHuman, String numFingersAi, String guessedSumHuman, String guessedSumAi) {
    /*
     * Takes in the finger numbers and guessed sum for both human and AI. Then decides
     * the winner based off of who guessed the correct sum of fingers.
     */
    // Convert all values to integer type
    int intFingersHuman = Integer.parseInt(numFingersHuman);
    int intFingersAi = Integer.parseInt(numFingersAi);
    int intGuessedSumHuman = Integer.parseInt(guessedSumHuman);
    int intGuessedSumAi = Integer.parseInt(guessedSumAi);

    // calculate finger sum
    int correctSum = intFingersHuman + intFingersAi;

    // condition for if they both get it correct
    boolean bothCorrect = (intGuessedSumHuman == correctSum) & (intGuessedSumAi == correctSum);

    // check the winner and print report according
    if (bothCorrect) {
      // Both guessed it correct, it's a draw!
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    } else if (intGuessedSumAi == correctSum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      // Increment AI accumulated points
      this.aiPlayer.incrementAccumulatedPoints();
    } else if (intGuessedSumHuman == correctSum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      // Increment Human accumulated points
      this.humanPlayer.incrementAccumulatedPoints();
    } else {
      // no one got it, so it's a draw!
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    }
  }
}
