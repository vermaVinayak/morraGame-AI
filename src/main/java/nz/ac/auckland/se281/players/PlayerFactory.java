package nz.ac.auckland.se281.players;

import nz.ac.auckland.se281.Main;

public class PlayerFactory {
  public Player createPlayer(Main.Difficulty difficulty, String playerName) {
    // Factory Design pattern approach
    // return appropriate Ai level competitor for battling human player :)
    switch (difficulty) {
      case EASY:
        return new AiEasy(playerName);
      case MEDIUM:
        return new AiMedium(playerName);
      case HARD:
        return new AiHard(playerName);
      case MASTER:
        return new AiMaster(playerName);
      default:
        System.out.println("Error: Unknown player specified!");
        break;
    }
    return null;
  }
}
