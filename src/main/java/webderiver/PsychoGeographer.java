package webderiver;

import java.lang.Math;

class PsychoGeographer {
  private Double[] location;
  private double w;

  public PsychoGeographer() {
    location = new Double[2];
  }

  public void setWidth(int width) {
    w = width;
  }

  public void setLocation(Double[] loc) {
    location = loc;
  }

  public int lilTurn(Double[] coords) {
    return Utils.compareCoords(coords, location, 1000)
        ? (Math.rint(Math.random()) == 0 ? -1 : 1) * Math.toIntExact(Math.round(w * 0.2))
        : 0;
  }

  public boolean bigTurn(Double[] coords) {
    return Utils.compareCoords(coords, location, 1000 * 1000);
  }
}
