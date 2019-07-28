package pl.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  @Column(name = "id")
  private int user_id;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;

  public String getUsername() {
    return username;
  }

  public int getUserId() {
    return user_id;
  }

  public String getEmail() {
    return email;
  }
}
