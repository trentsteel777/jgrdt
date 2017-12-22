package grdt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tester {

  public static void main(String[] args) {
    String dateLiteral = "Dec 15, 2017";
   LocalDate d = LocalDate.parse(dateLiteral, DateTimeFormatter.ofPattern("MMM d, yyyy"));
    
    
   System.out.println(d);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM,yyyy");

    String date = "16/08,2016";

    //convert String to LocalDate
    LocalDate localDate = LocalDate.parse(date, formatter);
    System.out.println(localDate);
    
  }

}
