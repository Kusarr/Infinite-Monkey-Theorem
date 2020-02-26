import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class TimingFileWriter {
  private DecimalFormat decimalFormater;

  public TimingFileWriter(DecimalFormat decimalFormater) {
    this.decimalFormater = decimalFormater;
  }

  public void writeTiming(String searchingWord, long writtenChars, long timing) throws IOException {
    FileWriter fw = new FileWriter("RandomWordCreationTimings.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(getLineForFile(searchingWord, writtenChars, timing));
    bw.newLine();
    bw.close();
  }

  private String getLineForFile(String searchingWord, long writtenChars, long timing) {
    return "Word: "
        + appendSpaces(searchingWord)
        + //
        "Wrote Chars: "
        + appendSpaces(decimalFormater.format(writtenChars))
        + //
        "Time: "
        + appendSpaces(decimalFormater.format(timing) + " ms")
        + //
        "c/ms: "
        + decimalFormater.format(writtenChars / timing);
  }

  private String appendSpaces(String value) {
    while (value.length() < 15) {
      value += " ";
    }
    return value;
  }
}
