package webderiver;

class Utils {
  public static void sleeper(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
    return;
  }

  public static Double[] parseXY(String x, String y) {
    return new Double[] { Double.parseDouble(x),  Double.parseDouble(y) };
  }

  public static Double[] parseCoords(String url) {
    String[] coordinates = url.split("@")[1].split(",");
    return parseXY(coordinates[0], coordinates[1]);
  }

  public static Boolean compareCoords(Double[] a, Double[] b, int factor) {
    Boolean[] comparisons = new Boolean[2];
    for (int i = 0; i < 2; i++) {
      comparisons[i] = Math.round(a[i] * factor) == Math.round(b[i] * factor);
    }
    return (comparisons[0] == true && comparisons[1] == true);
  }
}