import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class RandomWordCreator {
  private static final int MAX_WRITTEN_CHARS_SIZE_FOR_STRING = 20;
  private static final char[] ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

  private ConsolePrinter consolePrinter;
  private DecimalFormat decimalFormater;
  private String searchingWord;
  private boolean printLessOutput;
  private String writtenChars = "";
  private long charsCounter;

  public RandomWordCreator(String searchingWord, boolean printLessOutput) {
    this.searchingWord = searchingWord.toLowerCase();
    this.printLessOutput = printLessOutput;
    decimalFormater = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);
    decimalFormater.setMaximumFractionDigits(3);
    consolePrinter = new ConsolePrinter(decimalFormater, searchingWord, printLessOutput);
  }

  public void run() throws IOException {
    long startTime = System.currentTimeMillis();
    createCharsUntilSearchedWordFound();
    long timing = System.currentTimeMillis() - startTime;

    consolePrinter.printEndLine(writtenChars, charsCounter);

    TimingFileWriter timingFileWriter = new TimingFileWriter(decimalFormater);
    timingFileWriter.writeTiming(searchingWord, charsCounter, timing);
  }

  private void createCharsUntilSearchedWordFound() {
    if (printLessOutput) {
      System.out.print("Chars-Counter: ");
    }
    while (!isWordWritten()) {
      char newChar = generateChar();
      writtenChars += newChar;
      charsCounter++;
      consolePrinter.writeLineToConsole(writtenChars, charsCounter);
      if (writtenChars.length() > MAX_WRITTEN_CHARS_SIZE_FOR_STRING) {
        removeOldCharsFromString();
      }
    }
  }

  private char generateChar() {
    return ALLOWED_CHARS[ThreadLocalRandom.current().nextInt(0, ALLOWED_CHARS.length)];
  }

  private boolean isWordWritten() {
    return writtenChars.endsWith(searchingWord);
  }

  private void removeOldCharsFromString() {
    writtenChars = writtenChars.substring(writtenChars.length() - searchingWord.length());
  }
}
