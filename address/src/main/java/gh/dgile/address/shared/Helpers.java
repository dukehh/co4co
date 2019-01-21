package gh.dgile.address.shared;

import org.springframework.stereotype.Component;

@Component
public class Helpers {


  public static int getRandomNumberNoDecimals(int min, int max, int size) {
     return (int) Math.floor((int) (min + Math.random() * (max - min) * size));
  }

  public static double getRandomNumber() {
    double min = 10000.00;
    double max = 25000.00;
    return Math.floor(( min + Math.random() * (max - min)) * 100) / 100;
  }


}



