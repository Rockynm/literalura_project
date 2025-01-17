package com.math.literalura;

import com.math.literalura.principal.GranRepertorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private GranRepertorio granRepertorio; // Inyección automática de GranRepertorio

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		granRepertorio.mostrarMenu(); // Usar el bean inyectado por Spring
	}
}


/*
package com.math.literalura;
import com.math.literalura.principal.GranRepertorio;
import com.math.literalura.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepositorio repository;
	//Indica a Spring que yo quiero que él haga una inyección de dependencia
	//--después de haber insertado esta anotación, necesitaremos indicar cuál va a ser la inyección, cuál va a ser la dependencia que queremos que él maneje para nosotros:

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {


		GranRepertorio miGranRepertorio = new GranRepertorio();
		miGranRepertorio.mostrarMenu();

	}
}
*/