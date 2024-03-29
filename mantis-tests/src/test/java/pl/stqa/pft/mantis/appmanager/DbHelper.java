package pl.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.stqa.pft.mantis.model.UserData;
import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper()

  {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  public UserData users()
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData").list();
    for ( UserData user : result)  {
      System.out.println(user.getUserId() + " " + user.getUsername());
    }
    session.getTransaction().commit();
    session.close();
    return new UserData();
  }

  public String getUserFromDB(int id)
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData").list();
    session.getTransaction().commit();
    session.close();
    return result.get(id).getUsername();
  }

  public String getEmailFromDB(int id)
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData").list();
    session.getTransaction().commit();
    session.close();
    return result.get(id).getEmail();
  }
}
