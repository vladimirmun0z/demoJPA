package com.demojpa;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.demojpa.models.Categoria;
import com.demojpa.repository.ICategoriasRepository;

@SpringBootApplication


public class DemoJpaApplication implements CommandLineRunner {
	
	
	@Autowired
	private ICategoriasRepository repoCategoria;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//testConexion();
		//guardar();
		//buscarPorId();
		//modificar();
		//eliminarPorId();
		//cantidadCategorias();
		//eliminarTodo();
		//encontrarPorIds();
		//buscarTodos();
		//existeId();
		//guardarTodas();
		
		
		//Metodos con JPAPepository
		
		//buscarTodosJpa();
		//borrarEnBatch();
		//buscarTodosOrdenados();
		//buscarTodoEnPaginacion();
		
	}
	
	
	
	//metodo guardar
	private void guardar() {
		Categoria categoria = new Categoria();
		categoria.setNombre("Trips en la playa");
		categoria.setDescripcion("Paseos en la playa para ver el atardecer");
		repoCategoria.save(categoria);
	}
	
	//metodo buscar por id
	private void buscarPorId() {
		Optional<Categoria> optional = repoCategoria.findById(1);
		if (optional.isPresent()) {
			System.out.println(optional.get() .getNombre());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	//metodo midificar
	private void modificar() {
	    Optional<Categoria> optional = repoCategoria.findById(1);
	    if (optional.isPresent()) {
	        Categoria catTemp = new Categoria();
	        catTemp = optional.get();
	        catTemp.setNombre("Caminatas en el Volcan");
	        catTemp.setDescripcion("Exigentes caminatas para....");
	        repoCategoria.save(catTemp);
	        System.out.println(optional.get());
	    } else
	        System.out.println("Categoria no econtrada");

	}
	
	//metodo eliminar por id
	private void eliminarPorId() {
		repoCategoria.deleteById(1);
	}
	
	//metodo cantidad categorias
	private void cantidadCategorias() {
		long cantidad = repoCategoria.count();
		System.out.println("Cantidad: " + cantidad);
	}
	
	//metodo eliminar todo
	private void eliminarTodo() {
		repoCategoria.deleteAll();
	}
	
	//metodo encontrar por ids
	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(3);
		ids.add(6);
		Iterable<Categoria> categoria = repoCategoria.findAllById(ids);
		for (Categoria cat : categoria)
			System.out.println(cat.getNombre() + " " + cat.getDescripcion());
	}
	
	//metodo buscar todos
	private void buscarTodos() {
		Iterable<Categoria> categoria = repoCategoria.findAll();
		for (Categoria cat : categoria)
			System.out.println(cat.getNombre() + " " + cat.getDescripcion());
	}
	
	//metodo existe (verificar si existe una categoria)
	private void existeId() {
		boolean existe = repoCategoria.existsById(4);
		System.out.println("La categoria existe: " + existe);
		
		
	}
	
	//metodo que crea y devuelve la lista de categorías
	private List<Categoria> getCategoria() {
			
		List<Categoria> lista = new LinkedList<Categoria>();
			
		Categoria cat1 = new Categoria();
		cat1.setNombre("Trips en la playa");
		cat1.setDescripcion("Paseos en la playa...");
			
		Categoria cat2 = new Categoria();
		cat2.setNombre("Trips en la Ciudad");
		cat2.setDescripcion("Paseos en la Ciudad...");
			
		lista.add(cat1);
		lista.add(cat2);
			
		return lista;
	}
		
	//metodo que obtiene la lista y la manda a guardar a la base de datos
	private void guardarTodas() {
		List<Categoria> lista = getCategoria();
		repoCategoria.saveAll(lista);
	}
	
	
	
	
	
	//Metodos con JPARepository
	
	
	//metodo para buscar todos y retornar una List directamente
	private void buscarTodosJpa() {
	    List<Categoria> lista = repoCategoria.findAll();
	    for (Categoria cat : lista) {
	        System.out.println(cat.getId() + " " + cat.getNombre());
	    }
	}
	
	//metodo borrar en batch
	private void borrarEnBatch() {
		repoCategoria.deleteAllInBatch();
	}
	
	//metodo buscar todos ordenados
	private void buscarTodosOrdenados() {
	    List<Categoria> lista = repoCategoria.findAll(Sort.by("nombre"));
	    for (Categoria cat : lista) {
	        System.out.println(cat.getId() + " " + cat.getNombre());
	    }
	}
	
	//metodo buscar todo en paginacion
	private void buscarTodoEnPaginacion() {
	    Page<Categoria> page = repoCategoria.findAll(PageRequest.of(0, 5));
	    System.out.println("Total Categorias: " + page.getTotalElements());
	    System.out.println("Total Paginas: " + page.getTotalPages());
	    for (Categoria cat : page) {
	        System.out.println(cat.getId() + " " + cat.getNombre());
	    }
	}
	
	

	
	
	
	private void testConexion() {
		System.out.println("probando conexion...");

		if (repoCategoria != null) {
			System.out.println("Conexion Exitosa : " + repoCategoria);
		} else {
			System.out.println("error de Conexion");
		}

	}
}

