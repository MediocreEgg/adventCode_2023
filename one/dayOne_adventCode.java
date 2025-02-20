package adventOne_java;

import java.io.*;
import java.util.stream.*;

public class dayOne_adventCode {

  static int totalSum() {
    int result = 0;

    try (
        FileReader file = new FileReader("D:\\Coding Projects\\.adventOfCode\\adventOne_java\\advent_one_input.txt");
        BufferedReader br = new BufferedReader(file);) {

      StringBuilder builder = new StringBuilder();

      do {
        char[] charArr = br.readLine().toCharArray();

        if(charArr == null)
          break;

        for (char character : charArr)
          if (Character.isDigit(character))
            builder.append(character);

        if (builder.length() == 1)
          builder.append(builder.charAt(0));

        if (builder.length() > 2)
          builder.delete(1, builder.length() - 1);

        result += Integer.parseInt(builder.toString());
        builder.setLength(0);

      } while (builder != null);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(dayOne_adventCode.totalSum());
  }
}
