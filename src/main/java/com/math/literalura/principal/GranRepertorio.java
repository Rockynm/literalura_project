package com.math.literalura.principal;

import com.math.literalura.models.Autor;
import com.math.literalura.models.DatosAutor;
import com.math.literalura.models.DatosLibro;
import com.math.literalura.models.Libro;
import com.math.literalura.repository.AutorRepositorio;
import com.math.literalura.repository.LibroRepositorio;
import com.math.literalura.service.ConsultorApi;
import com.math.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Repository
@Component // Marca esta clase como un componente gestionado por Spring
public class GranRepertorio {

    private Scanner scanner = new Scanner(System.in);
    private ConsultorApi consultorApi = new ConsultorApi();
    private List<DatosLibro> listaDeLibros = new ArrayList<>();

    private LibroRepositorio repository; // Cambiado a un nombre más claro
    private AutorRepositorio repositoryAutor;

    private List<Libro> libross;
    private List<Autor> autoress;

    @Autowired // Anotación para inyección automática
    public GranRepertorio(LibroRepositorio repository, AutorRepositorio repositoryAutor){
        this.repository = repository;
        this.repositoryAutor = repositoryAutor;
    }

    public void mostrarMenu() {
        int continuidad = -1;
        while (continuidad != 0) {
            String menu = """
                    1 - Buscar libro por título
                    2 - Mostrar lista de todos los libros
                    3 - Mostrar lista de autores
                    4 - Mostrar lista de autores en determinados años
                    5 - Exhibir cantidad de libros en un determinado idioma
                    0 - Salir
                    """;

            System.out.println(menu);
            int opcionSeleccion = scanner.nextInt();
            scanner.nextLine();

            switch (opcionSeleccion) {
                case 1:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Ingrese el TITULO del libro que desee buscar");
                    String tituloBuscado = scanner.nextLine();
                    String json = consultorApi.obtenerDatosGenerales(
                            "https://gutendex.com/books/?search=" + tituloBuscado.replace(" ", "%20"));

                    ConvierteDatos conversor = new ConvierteDatos();
                    //--Obtiene los datos del Libro y los convierte a un objeto Libro y se almacena a la base de datos
                    var libro = conversor.obtenerDatos(json, DatosLibro.class);
                    System.out.println(libro);
                    Libro miLibro = new Libro(libro);
                    System.out.println(miLibro);
                    repository.save(miLibro); // Guardar en la base de datos

                    //--Obtiene los datos del Autor y los convierte a un objeto Autor y se almacena a la base de datos
                    String json2 = consultorApi.obtenerDatosEspecificos("https://gutendex.com/books/?search=" + tituloBuscado.replace(" ", "%20"));
                    var autor = conversor.obtenerDatos(json2, DatosAutor.class);
                    System.out.println(autor);
                    Autor miAutor = new Autor(autor);
                    System.out.println(miAutor);
                    repositoryAutor.save(miAutor);



                    break;
                case 2:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Mostrando la lista de todos los libros buscados");
                    libross = repository.findAll();
                    libross.forEach(System.out::println);

                    //listaDeLibros.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Mostrando la lista de autores de los libros buscados");
                    autoress = repositoryAutor.findAll();
                    autoress.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Elige el año mínimo");
                    int fechaBuscadaMinima = scanner.nextInt();
                    scanner.nextLine();
                    //System.out.println("Elige el año máximo");
                    //int fechaBuscadaMaxima = scanner.nextInt();
                    //scanner.nextLine();

                    autoress = repositoryAutor.findByNacimientoGreaterThanEqual(fechaBuscadaMinima);

                    autoress.forEach(System.out::println);

                    break;
                case 5:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Escribe el idioma de los libros que quieres obtener");
                    String idiomaBuscado = scanner.nextLine();
                    libross = repository.findAll();
                    libross.stream()
                            .filter(l -> l.getIdiomas().contains(idiomaBuscado))
                            .forEach(System.out::println);

                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    continuidad++;
                    break;
                default:
                    System.out.println("Por favor, ingrese un número correcto");
            }
        }
    }
}

/*
package com.math.literalura.principal;

import com.math.literalura.models.DatosLibro;
import com.math.literalura.models.Libro;
import com.math.literalura.repository.LibroRepositorio;
import com.math.literalura.service.ConsultorApi;
import com.math.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GranRepertorio {
    //Aplicaciones generales
    private Scanner scanner = new Scanner(System.in);
    private ConsultorApi consultorApi = new ConsultorApi();
    private List<DatosLibro> listaDeLibros = new ArrayList<>();


    //lo de abajo es con el fin de poder usar los métodos del LibroRepositorio, por así decirlo
    private LibroRepositorio repositorys;
    @Autowired
    public GranRepertorio(LibroRepositorio repositorio){
        this.repositorys = repositorio;
    }

    public GranRepertorio(){
    }



    public void mostrarMenu(){
        //PASO 2: SE EJECUTA EL CONTROLADOR PARA EL LADO DEL USUARIO
        int continuidad = -1;
        while(continuidad != 0){
            String menu = """
                    1 - Buscar libro por título
                    2 - Mostrar lista de todos los libros
                    3 - Mostrar lista de autores
                    4 - Mostrar lista de autores en determinados años
                    5 - Exhibir cantidad de libros en un determinado idioma
                    0 - Salir
                    """;

            System.out.println(menu);
            int opcionSeleccion = scanner.nextInt();
            scanner.nextLine();

            switch (opcionSeleccion){
                case 1:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Ingrese el TITULO del libro que desee buscar");
                    String tituloBuscado = scanner.nextLine();
                    String json = consultorApi.obtenerDatosGenerales("https://gutendex.com/books/?search=" + tituloBuscado.replace(" ", "%20"));
                    ConvierteDatos conversor = new ConvierteDatos();
                    var libro = conversor.obtenerDatos(json, DatosLibro.class);
                    System.out.println(libro);
                    //Guardando el libro en una base de datos
                    Libro miLibro = new Libro(libro);
                    System.out.println(miLibro);
                    repositorys.save(miLibro);

                    //listaDeLibros.add(datos); - Anticuado
                    break;
                case 2:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Mostrando la lista de todos los libros buscados");
                    //Mostrar libros +
                    listaDeLibros.stream().forEach(System.out::println);
                    System.out.println("Lista aquí" + "------------------------------------------------------------");
                    break;
                case 3:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Mostrando la lista de autores de los libros buscados");

                    //Mostrar solo autores
                    break;
                case 4:
                    System.out.println("Mostrar lista de autores en determinados años");

                    //Mostrar solo autores y aplicar filtros para que los autores estén
                    break;
                case 5:
                    System.out.println("Exhibir cantidad de libros en un determinado idioma");

                    //Mostrar la cantidad de libros
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    continuidad++;
                    break;
                default:
                    System.out.println("Por favor, ingrese un numero correcto");
            }
        }
    }
}
*/
