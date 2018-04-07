package daggerok;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Getter
@SessionScoped
public class IndexBean implements Serializable {

  @Setter
  private String name;
  private String message;

  public void createMessage() {
    message = null == name || "".equals(name.trim())
        ? "" : "Hello, " + name.trim() + "!";
    setName("");
  }
}
