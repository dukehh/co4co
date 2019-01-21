package gh.dgile.address.shared;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("address")
public class GHproperties {

  @Data
  private static class Csv {
    private String val1;
    private String val2;
    private String val3;

    // standard getters and setters
  }

  private String addresses;
  private String delimiter;
  private String hasTitle;
  private Csv csv;
  private List<String> defaultRecipients;
  private Map<String, String> csvProps;

// standard getters and setters

}
