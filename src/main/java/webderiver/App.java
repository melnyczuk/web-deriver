package webderiver;

import java.lang.Math;
import webderiver.Deriver;

class App {

  public static void main(String args[]) {
    Deriver guy = new Deriver();

    guy.init(args[0], args[1]);

    while (true) {
      guy.walk();
    }
  }
}    
    