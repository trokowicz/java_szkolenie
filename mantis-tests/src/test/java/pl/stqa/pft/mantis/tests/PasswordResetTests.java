package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;
import pl.stqa.pft.mantis.appmanager.HttpSession;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase
{

  @BeforeMethod
  public void startMailServer()
  {
    app.mail().start();
  }

  @Test
  public void PasswordReset() throws IOException, MessagingException {
    int user_id = 1;
    long now = System.currentTimeMillis();
    String username = app.db().getUserFromDB(user_id);
    String email = app.db().getEmailFromDB(user_id);
    String newPassword = String.format("password", now);
    app.ui().loginUI("administrator","root");
    app.ui().passwordReset(username);
    app.ui().logout();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.ui().passwordResetPage(confirmationLink,newPassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(username,newPassword));
    assertTrue(session.isLoggedInAs(username));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer()
  {
    app.mail().stop();
  }

}
