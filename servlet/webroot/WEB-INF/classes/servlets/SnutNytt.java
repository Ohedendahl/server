package servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import se.itu.polisen.handelser.*;
import se.itu.polisen.net.*;
import static se.itu.polisen.json.HandelseParser.parse;
import filter.CustomCharacterEncodingFilter;

import org.json.JSONException;

public class SnutNytt extends HttpServlet{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException{
    
    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out =
    new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
    StandardCharsets.UTF_8), true);

    String json = null;
    try{

      String query = "";
      JSONFetcher fetcher = new JSONFetcher();
      json = fetcher.fetch(query);

      for (Handelser handelser : parse(json)) {
        System.out.println("==============================");
        System.out.println(handelser);
      }
    } catch (JSONException je) {
      System.err.println("Something went wrong: " + je.getMessage());
    }

    StringBuilder sb = new StringBuilder("<!DOCTYPE html>\n")
    .append("<html>\n")
    .append("<head>\n")
    .append("  <title>SNUTNYTT</title>\n")
    .append("</head> \n")
    .append("<h1>Välkommen till Snutnytt!</h1>\n")
    .append("<br>")
    .append("<h2>Detta har hänt i Göteborg: </h2>\n")
    .append("<body>\n");
    try {
      for (Handelser handelse : parse(json)) {
        sb.append(handelse.toString());
        sb.append("<hr> <br> \n");
      }
    } catch (JSONException e) {
      System.err.println(e.getMessage());
    }
    sb.append("</body>\n")
      .append("</html>\n");
    out.println(sb.toString());
    out.close();

  }
}
