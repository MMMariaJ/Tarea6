package demo;

import java.util.List;

import modelos.Driver;
import modelos.Constructors;
import repositorios.DriverRepository;
import repositorios.ConstructorsRepository;

public class Main {
    public static void main(String[] args) {
        DriverRepository driverRepository = new DriverRepository();
        List<Driver> drivers = driverRepository.getAllDrivers();

        System.out.println("Lista de Conductores:");
        for (Driver driver : drivers) {
            System.out.printf("ID: %d, Referencia: %s, Número: %d, Código: %s, Nombre: %s, Apellido: %s, Fecha de Nacimiento: %s, Nacionalidad: %s, URL: %s\n",
                    driver.getDriverId(), driver.getDriverRef(), driver.getNumber(), driver.getCode(),
                    driver.getForename(), driver.getSurname(), driver.getDob(), driver.getNationality(), driver.getUrl());
        }

        ConstructorsRepository constructorsRepository = new ConstructorsRepository();
        List<Constructors> constructors = constructorsRepository.getAllConstructors();

        System.out.println("\nLista de Constructores:");
        for (Constructors constructor : constructors) {
            System.out.printf("ID: %d, Referencia: %s, Nombre: %s, Nacionalidad: %s, URL: %s\n",
                    constructor.getConstructorId(), constructor.getConstructorRef(), constructor.getName(),
                    constructor.getNationality(), constructor.getUrl());
        }
    }
}