/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package m06uf2ex3;

import Modelo.Employee;
import Modelo.SingleSession;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;

/**
 *
 * @author Albert
 */
public class M06UF2EX3 {

    private static final Logger logger = LogManager.getLogger(M06UF2EX3.class);
    public static Faker faker = new Faker();

    public static void main(String[] args) {

        SingleSession session = new SingleSession();

        logger.trace("Iniciamos transaccion...");
        Transaction transaction = session.getSession().beginTransaction();

        logger.trace("Creamos el objeto Faker");

        logger.trace("Añadimos 1000 objetos inventados al ArrayList y guardamos la sesion por cada objeto creado.");

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 1000; i++) { 
            /**
             * *
             * Generamos un objeto Employee y creamos datos Fake para este.
             */
            Employee employee = new Employee();
            employee.setId(Integer.valueOf(faker.idNumber().valid()));
            employee.setNom(faker.name().name());
            employee.setDataNaixement(faker.date().birthday());
            employee.setAntiguitat(faker.number().numberBetween(1, 30));
            /**
             * *
             * Creamos dos objetos LocalDate, el DateNow y el birthDate, en
             * dateNow parseamos la fecha actual del sistema mientras que en
             * birthDate, parseamos la fecha generada por Java Faker.
             */
            LocalDate dateNow = LocalDate.now();
            LocalDate birthDate = LocalDate.parse(employee.getDataNaixement()+"");

            /**
             * *
             * Generamos un objeto Period y, con la funcion between, podemos
             * saber la edad del Empleado.
             */
            Period period = Period.between(birthDate, dateNow);
            
            /***
             * Comprobamos la edad del empleado y si cumple una franja de edad o otra, le actualizaremos el nombre o lo borraremos de la base de datos con session.delete.
             */
            if ((period.getYears() > 16) && (period.getYears() < 18)) {
                employee.setNom(employee.getNom() + "junior");
            } else if ((period.getYears() > 50) && (period.getYears() < 66)) {
                employee.setNom(employee.getNom() + "senior");

            } else if (period.getYears() > 66) {
                session.getSession().delete(employee);
            }
            /***
             * Mostramos los datos del Empleado y lo añadimos al ArrayList de Empleados.
             */
            logger.trace("Nombre: " + employee.getNom());
            logger.trace("Antiguedad: " + employee.getAntiguitat());
            logger.trace("Edad: " + period.getYears());
            employees.add(employee);
            try {
                /***
                 * Paramos el bucle de forma temporal para mostrar la cantidad de empleados recorridos.
                 */
                logger.trace("Cantidad Empleados: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /***
         * Persistimos los datos de los Empleados.
         */
        for (Employee employee : employees) {
            session.getSession().persist(employee);
        }

    }

}
