package se.itu.polisen.main;
import org.json.*;
import se.itu.polisen.handelser.Handelser;
import se.itu.polisen.net.JSONFetcher;
import static se.itu.polisen.json.HandelseParser.parse;
import java.io.*;

public class AktuellaHandelserLookup {

  public static void main(String[] args) {

    try{

      String query = "";
      if (args.length != 0) {
        query = args[0];
      }
      JSONFetcher fetcher = new JSONFetcher();
      String json = fetcher.fetch(query);

      for (Handelser handelser : parse(json)) {
        System.out.println("==============================");
        System.out.println(handelser);
      }
    } catch (JSONException je) {
      System.err.println("Something went wrong: " + je.getMessage());
    }
  }
}
