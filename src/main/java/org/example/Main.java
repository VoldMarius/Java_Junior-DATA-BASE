package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.connect();
            databaseManager.createDatabase("SchoolDB");
            databaseManager.useDatabase("SchoolDB");
            databaseManager.createTable("Courses");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SessionFactory sessionFactory = new Configuration()
                .configure("Homework4.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Course course1 = new Course("course1", 21);
        Course course2 = new Course("course2", 22);
        Course course3 = new Course("course3", 23);
        Course course4 = new Course("course4", 24);
        Course newCourse;

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.save(course1);
            session.save(course2);
            session.save(course3);
            session.save(course4);

            session.getTransaction().commit();
        }
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            newCourse = session.get(Course.class, course3.getId());
            System.out.println("Retrieved course object: " + newCourse);

            session.getTransaction().commit();
        }
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            newCourse.setTitle("newCourse");
            newCourse.setDuration(45);
            session.update(newCourse);

            session.getTransaction().commit();
        }
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.delete(course4);

            session.getTransaction().commit();
        }
    }
}