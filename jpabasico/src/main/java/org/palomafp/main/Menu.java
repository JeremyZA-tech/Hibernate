package org.palomafp.main;

import java.util.*;

import ModeloClases.Departamento;
import ModeloClases.Empleado;
import ModeloClases.Proyecto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Menu {

	static EntityManagerFactory entityManagerFactory;
	static EntityManager em = null;
	static EntityTransaction transaction;
	    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        em = entityManagerFactory.createEntityManager();
        transaction = em.getTransaction();
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    agregarDepartamento();
                    break;
                case 2:
                    eliminarDepartamento();
                    break;
                case 3:
                    agregarEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    agregarProyecto();
                    break;
                case 6:
                    eliminarProyecto();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 7);

        em.close();
        em.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n-- Menú --");
        System.out.println("1. Agregar Departamento");
        System.out.println("2. Eliminar Departamento");
        System.out.println("3. Agregar Empleado");
        System.out.println("4. Eliminar Empleado");
        System.out.println("5. Agregar Proyecto");
        System.out.println("6. Eliminar Proyecto");
        System.out.println("7. Salir");
    }

    private static void agregarDepartamento() {
        System.out.print("Ingrese el nombre del Departamento: ");
        String nombreDepartamento = scanner.nextLine();

        em.getTransaction().begin();
        Departamento departamento = new Departamento();
        departamento.setNombre(nombreDepartamento);
        em.persist(departamento);
        em.getTransaction().commit();
        System.out.println("Departamento agregado exitosamente.");
    }

    private static void eliminarDepartamento() {
        System.out.print("Ingrese el ID del Departamento a eliminar: ");
        int idDepartamento = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        em.getTransaction().begin();
        Departamento departamento = em.find(Departamento.class, idDepartamento);
        if (departamento != null) {
            em.remove(departamento);
            em.getTransaction().commit();
            System.out.println("Departamento eliminado exitosamente.");
        } else {
            em.getTransaction().rollback();
            System.out.println("No se encontró el Departamento con ese ID.");
        }
    }
    
    private static void agregarEmpleado() {
        System.out.print("Ingrese el nombre del Empleado: ");
        String nombreEmpleado = scanner.nextLine();

        System.out.print("Ingrese el ID del Departamento al que pertenece el Empleado: ");
        int idDepartamento = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        em.getTransaction().begin();
        Departamento departamento = em.find(Departamento.class, idDepartamento);
        if (departamento != null) {
            Empleado empleado = new Empleado();
            empleado.setNombre(nombreEmpleado);
            empleado.setDepartamento(departamento);
            em.persist(empleado);
            em.getTransaction().commit();
            System.out.println("Empleado agregado exitosamente al Departamento.");
        } else {
            em.getTransaction().rollback();
            System.out.println("No se encontró el Departamento con ese ID.");
        }
    }
    
    private static void eliminarEmpleado() {
        System.out.print("Ingrese el ID del Empleado a eliminar: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        em.getTransaction().begin();
        Empleado empleado = em.find(Empleado.class, idEmpleado);
        if (empleado != null) {
            em.remove(empleado);
            em.getTransaction().commit();
            System.out.println("Empleado eliminado exitosamente.");
        } else {
            em.getTransaction().rollback();
            System.out.println("No se encontró el Empleado con ese ID.");
        }
    }
    private static void agregarProyecto() {
        System.out.print("Ingrese el nombre del Proyecto: ");
        String nombreProyecto = scanner.nextLine();

        em.getTransaction().begin();
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombreProyecto);
        em.persist(proyecto);
        em.getTransaction().commit();
        System.out.println("Proyecto agregado exitosamente.");
    }
    
    private static void eliminarProyecto() {
        System.out.print("Ingrese el ID del Proyecto a eliminar: ");
        Long idProyecto = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer de entrada

        em.getTransaction().begin();
        Proyecto proyecto = em.find(Proyecto.class, idProyecto);
        if (proyecto != null) {
            em.remove(proyecto);
            em.getTransaction().commit();
            System.out.println("Proyecto eliminado exitosamente.");
        } else {
            em.getTransaction().rollback();
            System.out.println("No se encontró el Proyecto con ese ID.");
        }
    }
    
}