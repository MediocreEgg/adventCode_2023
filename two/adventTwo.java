package two;

import java.io.*;
import java.util.Objects;
import java.lang.StringBuilder;

/*
 *  TITLE:: Cube Conundrum
 *  Determine which games would have been possible if the bag had been loaded with only... 
 *  12 red cubes, 13 green cubes, and 14 blue cubes. 
 *  What is the sum of the IDs of those games?
 */

/*
 *  GAME ENTRY STRUCTURE:
 *    GAME #INTEGET#: #INTEGER# #STRING#, ... #INTEGER #STRING#; ...
 *    Example => Game 1: 7 blue, 5 red; 10 red, 7 blue; 5 blue, 4 green, 15 red; 4 green, 6 red, 7 blue; 5 green, 8 blue, 4 red; 5 red, 4 blue, 3 green
 *
 *    NOTE: Each ';' is draw. Therefore, the Game 1 above has 6 draws.
 */

// ATTTEMPT 1: 2477 - WRONG - ATTEMPT 1

public class adventTwo {
  static  String url = ".\\two\\input.txt";


  static final int redLimit = 12,
            greenLimit = 13,
            blueLimit = 14;


  public static boolean isWithinConstraints(int qty, String color, int gameInstance){
    //if(qty > 11)
    //System.out.println(gameInstance + ":: ["+qty+"] | ["+color+"]");

    return switch (color) {
      case "red" -> (qty <= redLimit); 
      case "green" -> (qty <= greenLimit);
      case "blue" -> (qty <= blueLimit);
      default -> false;
    };
  }


  public static int numOfLegitimateGame(){
    int countOfLegitimateGames = 0;
    String gameRecord = null;

    try (BufferedReader reader = new BufferedReader(new FileReader(url))) { 
      int gameInstance = 1;

      gameRecord = reader.readLine();

      while ((gameRecord = reader.readLine()) != null) {
        
        // String processing
        // Splits draws from a game instance
        String[] rawSplit = gameRecord.substring(gameRecord.indexOf(":")+1).split(";");
        boolean isRig = false;

        for (String instance : rawSplit) {
          if(isRig)
            break;

          String[] draws = instance.trim().split("[,]*\s");
          
          for (int draw_index = 1; draw_index < (draws.length); draw_index += 2)
            if(!isWithinConstraints(Integer.parseInt(draws[draw_index-1]) , draws[draw_index], gameInstance)){
              System.out.println("RIGGED::\n\t\tGame #"+(gameInstance+1)+" => "+draws[draw_index-1]+" | "+draws[draw_index]+"");
              isRig = true;
              break;
            }
          
        }

        // Sums up Game IDs that abide the rules/conditions
        if(!isRig)
          countOfLegitimateGames += gameInstance;

        gameInstance++;
        // For debugging purposes
        //System.out.println("\nReviewed:: Game #" + gameInstance++);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    // For debugging purposes
    System.out.println("adventTwo::numOfLegitimateGame => DONE COUNTING");
    return countOfLegitimateGames;
  }
}
