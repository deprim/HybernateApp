package com.dandek.libratrack;


import com.dandek.libratrack.model.Item;
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
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {


            session.beginTransaction();

            Person person = session.get(Person.class, 4);
            Item item = session.get(Item.class, 1);

            item.getOwner().getItems().remove(item);

            item.setOwner(person);
            person.getItems().add(item);


            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }


    }
}
