package zooologico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Data_animal data_animal= new Data_animal();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menú de Gestión de Animales ---");
            System.out.println("1. Insertar Animal");
            System.out.println("2. Actualizar Animal");
            System.out.println("3. Eliminar Animal");
            System.out.println("4. Ver Animal");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();  

            switch (option) {
                case 1:
                    Animal newAnimal = new Animal();
                    System.out.print("Ingrese el número de animal: ");
                    newAnimal.setNo_animal(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    newAnimal.setNombre(scanner.nextLine());
                    System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
                    newAnimal.setNacimiento(scanner.nextLine());
                    System.out.print("Ingrese la especie: ");
                    newAnimal.setEspecie(scanner.nextLine());

                    animalDAO.insertAnimal(newAnimal);
                    break;

                case 2:  
                    Animal updateAnimal = new Animal();
                    System.out.print("Ingrese el número del animal a actualizar: ");
                    updateAnimal.setNo_animal(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre: ");
                    updateAnimal.setNombre(scanner.nextLine());
                    System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                    updateAnimal.setNacimiento(scanner.nextLine());
                    System.out.print("Ingrese la nueva especie: ");
                    updateAnimal.setEspecie(scanner.nextLine());

                    animalDAO.updateAnimal(updateAnimal);
                    break;

                case 3: 
                    System.out.print("Ingrese el número del animal a eliminar: ");
                    int deleteId = scanner.nextInt();
                    animalDAO.deleteAnimal(deleteId);
                    break;

                case 4:  
                    System.out.print("Ingrese el número del animal a ver: ");
                    int viewId = scanner.nextInt();
                    Animal animal = data_animal.selectAnimal(viewId);
                    if (animal != null) {
                        System.out.println("Número de Animal: " + animal.getNo_animal());
                        System.out.println("Nombre: " + animal.getNombre());
                        System.out.println("Fecha de Nacimiento: " + animal.getNacimiento());
                        System.out.println("Especie: " + animal.getEspecie());
                    } else {
                        System.out.println("Animal no encontrado.");
                    }
                    break;

                case 5: 
                    exit = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }
}
