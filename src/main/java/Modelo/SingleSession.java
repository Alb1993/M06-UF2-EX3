/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Albert
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingleSession {
    public static SingleSession instance = null;
    public Session session;


    /***
     * Declaramos los Atributos de la clase SingleSession, que seran los objetos Session y SessionFactory.
     */
    public SingleSession() {
        SessionFactory sessionFactory = new Configuration().configure("hibernateConfig/hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
    }
    /***
     * Funcion que genera una nueva Instancia de SingleSession.
     * @return 
     */
    public static SingleSession getInstance() {
        if (instance == null) {
            instance = new SingleSession();
        }
        return instance;
    }

    /***
     * A travès del metodo getSession, podemos devolver una session abierta en vez de Instanciar otra session, 
     * Al no haber dos sessiones abiertas, tendremos menos errores al hacer el RollBack.
     * 
     * @return 
     */
    public Session getSession() {
        return session;
    }
}