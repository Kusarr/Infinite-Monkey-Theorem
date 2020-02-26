import java.text.DecimalFormat;

public class ConsolePrinter {
  private static final int LINE_LENGTH_FULL_OUTPUT = 290;
  private static final int PRINTS_PER_LINE = 20;

  private DecimalFormat decimalFormater;
  private String searchingWord;
  private int moduloValueForPrintChars;
  private boolean printLessOutput;

  public ConsolePrinter(
      DecimalFormat decimalFormater, String searchingWord, boolean printLessOutput) {
    this.decimalFormater = decimalFormater;
    this.searchingWord = searchingWord;
    this.printLessOutput = printLessOutput;
    setModuloValueForPrintingCharCounter();
  }

  public void printEndLine(String writtenChars, long charsCounter) {
    if (printLessOutput) {
      int preChars = writtenChars.length() < 20 ? writtenChars.length() : 20;
      System.out.println("\n..." + writtenChars.substring(writtenChars.length() - preChars));
    } else {
      System.out.println();
    }
    System.out.println(
        "\nWord '"
            + searchingWord
            + "' found after typing randomly "
            + decimalFormater.format(charsCounter)
            + " characters!");
  }

  public void writeLineToConsole(String writtenChars, long charsCounter) {
    if (!printLessOutput) {
      System.out.print(writtenChars.substring(writtenChars.length() - 1));
      if (charsCounter % LINE_LENGTH_FULL_OUTPUT == 0) {
        System.out.println("\t" + decimalFormater.format(charsCounter));
      }
    }
    if (printLessOutput && charsCounter % moduloValueForPrintChars == 0) {
      System.out.print(
          decimalFormater.format(charsCounter)
              + ", "
              + ((charsCounter % (moduloValueForPrintChars * PRINTS_PER_LINE) == 0) ? "\n" : ""));
    }
  }

  private void setModuloValueForPrintingCharCounter() {
    int repeater = searchingWord.length() <= 8 ? searchingWord.length() : 8;
    moduloValueForPrintChars = Integer.valueOf("1" + "0".repeat(repeater));
  }
}
