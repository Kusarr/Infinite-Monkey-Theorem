import java.io.IOException;

public class Main {
  private static final String SEARCH_FOR_WORD = "Madrid";
  private static final boolean PRINT_LESS_OUTPUT = false;

  public static void main(String[] args) throws IOException {
    RandomWordCreator randomWordCreator = new RandomWordCreator(SEARCH_FOR_WORD, PRINT_LESS_OUTPUT);
    randomWordCreator.run();
  }
}
