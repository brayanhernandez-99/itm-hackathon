package com.co.hackathon.itm_hackathon_web;

import static org.assertj.core.api.Assertions.assertThat;
import com.co.hackathon.itm_hackathon_web.repositories.AcompananteRepository;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaRepository;
import com.co.hackathon.itm_hackathon_web.repositories.EventoRepository;
import com.co.hackathon.itm_hackathon_web.repositories.MiembroRepository;
import com.co.hackathon.itm_hackathon_web.services.AcompananteService;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaService;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class ItmHackathonWebApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AcompananteService acompananteServicio;

	@Autowired
	private AsistenciaService asistenciaServicio;

	@Autowired
	private EventoService eventoServicio;

	@Autowired
	private MiembroService miembroServicio;

	@Autowired
	private ApplicationContext contextoAplicacion;

	@Autowired
	private DataSource fuenteDeDatos;

	@Autowired
	private EntityManager manejadorEntidades;

	@Autowired
	private AcompananteRepository acompananteRepositorio;

	@Autowired
	private AsistenciaRepository asistenciaRepositorio;

	@Autowired
	private EventoRepository eventoRepositorio;

	@Autowired
	private MiembroRepository miembroRepositorio;

	// Verifica que los servicios se carguen correctamente en el contexto de la aplicación
	@Test
	void debeCargarElContexto() {
		assertThat(acompananteServicio).isNotNull();
		assertThat(asistenciaServicio).isNotNull();
		assertThat(eventoServicio).isNotNull();
		assertThat(miembroServicio).isNotNull();
	}

	// Verifica que los controladores estén registrados en el contexto de la aplicación
	@Test
	void debeCargarControladores() {
		assertThat(contextoAplicacion.containsBean("acompananteController")).isTrue();
		assertThat(contextoAplicacion.containsBean("asistenciaController")).isTrue();
		assertThat(contextoAplicacion.containsBean("eventoController")).isTrue();
		assertThat(contextoAplicacion.containsBean("miembroController")).isTrue();
	}

	// Verifica que los endpoints devuelvan una respuesta HTTP 200 (OK)
	@Test
	void debeRetornarOkDesdeTodosLosEndpoints() {
		String[] endpoints = {"/evento", "/acompanante", "/asistencia", "/reportes", "/acompanante/asistencia"};

		for (String endpoint : endpoints) {
			ResponseEntity<String> respuesta = restTemplate.getForEntity(endpoint, String.class);
			assertThat(respuesta.getStatusCode())
					.as("Validando endpoint: " + endpoint)
					.isEqualTo(HttpStatus.OK);
		}
	}

	// Verifica que las dependencias de la base de datos estén correctamente cargadas
	@Test
	void debeCargarDependenciasDeBaseDeDatos() {
		assertThat(fuenteDeDatos).isNotNull();
		assertThat(manejadorEntidades).isNotNull();
	}

	// Verifica que los repositorios sean correctamente inyectados
	@Test
	void debeCargarRepositorios() {
		assertThat(acompananteRepositorio).isNotNull();
		assertThat(asistenciaRepositorio).isNotNull();
		assertThat(eventoRepositorio).isNotNull();
		assertThat(miembroRepositorio).isNotNull();
	}
}
