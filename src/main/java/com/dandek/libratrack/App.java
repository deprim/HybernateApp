package com.dandek.libratrack;


import com.dandek.libratrack.model.Item;
import com.dandek.libratrack.model.Passport;
import com.dandek.libratrack.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import java.util.List;

public class App
{
    public static void main( String[] args ) {

        // Connect file hibernate.properties and Entity
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {


            session.beginTransaction();


            Person person = session.get(Person.class, 1);


            Passport passport = new Passport(123321, person);
            person.setPassport(passport);
            session.save(passport);




            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }


    }
}
