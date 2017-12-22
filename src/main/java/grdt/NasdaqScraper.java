package grdt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NasdaqScraper {

  private static final byte CALL_NAME_INDEX = 0;
  private static final byte CALL_LAST_INDEX = 1;
  private static final byte CALL_CHANGE_INDEX = 2;
  private static final byte CALL_BID_INDEX = 3;
  private static final byte CALL_ASK_INDEX = 4;
  private static final byte CALL_VOLUME_INDEX = 5;
  private static final byte CALL_OPEN_INTEREST_INDEX = 6;

  private static final byte TICKER_INDEX = 7;
  private static final byte STRIKE_INDEX = 8;

  private static final byte PUT_NAME_INDEX = 9;
  private static final byte PUT_LAST_INDEX = 10;
  private static final byte PUT_CHANGE_INDEX = 11;
  private static final byte PUT_BID_INDEX = 12;
  private static final byte PUT_ASK_INDEX = 13;
  private static final byte PUT_VOLUME_INDEX = 14;
  private static final byte PUT_OPEN_INTEREST_INDEX = 15;

  public static void main(String[] args) {

    Document doc;
    try {
      String symbol = "ibm";
      String url = "https://www.nasdaq.com/symbol/" + symbol + "/option-chain?money=all&expir=stan&dateindex=-1&page=1";

      doc = org.jsoup.Jsoup.connect(url).timeout(100*1000).get();

      Element form = doc.getElementById("optionchain");
      Elements tables = form.getElementsByTag("table");
      Element table = tables.get(0);

      Elements trs = table.getElementsByTag("tr");


      List<Option> calls = new ArrayList<>();
      List<Option> puts = new ArrayList<>();

      for (Element tr : trs) {
        Elements tds = tr.getElementsByTag("td");
        if (tds.size() == 16){
          Option call = new Option(OptionType.CALL);
          call.setExpiry      ( getOptionExpiry( tds.get(CALL_NAME_INDEX)         .text() ) );
          call.setContractName( getContractName( tds.get(CALL_NAME_INDEX).getElementsByTag("a").get(0).attr("href") ) );
          call.setLast        ( parsesFloat(tds.get(CALL_LAST_INDEX)              .text() ) );
          call.setChange      ( parsesFloat(tds.get(CALL_CHANGE_INDEX)            .text() ) );
          call.setBid         ( parsesFloat(tds.get(CALL_BID_INDEX)               .text() ) );
          call.setAsk         ( parsesFloat(tds.get(CALL_ASK_INDEX)               .text() ) );
          call.setVolume      ( parsesInt(tds.get(CALL_VOLUME_INDEX)              .text() ) );
          call.setOpenInterest( parsesInt(tds.get(CALL_OPEN_INTEREST_INDEX)       .text() ) );
          call.setStrike      ( parsesFloat(tds.get(STRIKE_INDEX)                 .text() ) );

          Option put = new Option(OptionType.PUT);
          put.setExpiry        ( getOptionExpiry( tds.get(PUT_NAME_INDEX)         .text() ) );
          put.setContractName  ( getContractName( tds.get(PUT_NAME_INDEX).getElementsByTag("a").get(0).attr("href")));
          put.setLast          ( parsesFloat(tds.get(PUT_LAST_INDEX)              .text() ) );
          put.setChange        ( parsesFloat(tds.get(PUT_CHANGE_INDEX)            .text() ) );
          put.setBid           ( parsesFloat(tds.get(PUT_BID_INDEX)               .text() ) );
          put.setAsk           ( parsesFloat(tds.get(PUT_ASK_INDEX)               .text() ) );
          put.setVolume        ( parsesInt(tds.get(PUT_VOLUME_INDEX)              .text() ) );
          put.setOpenInterest  ( parsesInt(tds.get(PUT_OPEN_INTEREST_INDEX)       .text() ) );
          put.setStrike        ( parsesFloat(tds.get(STRIKE_INDEX)                .text() ) );

          calls.add(call);
          puts.add(put);

        }
      }
      for(Option call : calls) {
        System.out.println(call.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  
  private static float parsesFloat(String floatLiteral) {
    if(StringUtils.isNotBlank(floatLiteral)) {
      return Float.parseFloat(floatLiteral);
    }
    return 0;
  }

  private static int parsesInt(String intLiteral) {
    if(StringUtils.isNotBlank(intLiteral)) {
      return Integer.parseInt(intLiteral);
    }
    return 0;
  }
  
  private static LocalDate getOptionExpiry(String dateLiteral) {
    return LocalDate.parse(dateLiteral, DateTimeFormatter.ofPattern("MMM d, yyyy"));
  }
  
  private static String getContractName(String urlWithContractName) {
    try {
      String token = "/option-chain/";
      int tokenLen = token.length();
      int index = urlWithContractName.indexOf(token);
      String urlEnd = urlWithContractName.substring(index + tokenLen);
      String[] arr = urlEnd.split("-");
      String contractName = arr[1].toUpperCase() + arr[0].toUpperCase();
      return contractName;

    }
    catch(Exception e) {
      return "";
    }
  }

}