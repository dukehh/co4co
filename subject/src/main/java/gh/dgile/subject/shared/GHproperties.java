package gh.dgile.subject.shared;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("subject")
public class GHproperties {

  @Data
  private static class Credentials {
    private String authMethod;
    private String username;
    private String password;

   // standard getters and setters
}
private String host;
private int port;
private String from;
private Credentials credentials;
private List<String> defaultRecipients;
private Map<String, String> csvProps;

// standard getters and setters

}
