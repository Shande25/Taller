package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Peliculas;
import com.taller.Taller.Servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PeliculaControlador {

    @Autowired
    private PeliculaServicio peliculasServicio;

    @GetMapping("/peliculas")
    public String listarPeliculas(Model model) {
        model.addAttribute("peliculas", peliculasServicio.obtenerTodasLasPeliculas());
        return "peliculas"; // Nombre del archivo HTML en la carpeta templates
    }

    @PostMapping("/agregarPelicula")
    public String agregarPelicula(
            @RequestParam String nombre,
            @RequestParam String genero,
            @RequestParam String director,
            @RequestParam Integer anio,
            Model model) {

        Peliculas pelicula = new Peliculas();
        pelicula.setNombre(nombre);
        pelicula.setGenero(genero);
        pelicula.setDirector(director);
        pelicula.setAnio(anio);

        peliculasServicio.guardarPelicula(pelicula);
        return "redirect:/peliculas"; // Redirige a la página de lista de películas
    }

    @GetMapping("/buscar")
    public String buscarPeliculas(@RequestParam String query, Model model) {
        try {
            List<Peliculas> peliculas = peliculasServicio.buscarPeliculas(query);
            model.addAttribute("peliculas", peliculas);
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar. Inténtalo de nuevo más tarde.");
        }
        return "resultadosBusqueda"; // Asegúrate de que este es el nombre correcto del archivo HTML
    }

    @GetMapping("/vistaPelicula/{id}")
    public String verPelicula(@PathVariable Long id, Model model) {
        Peliculas pelicula = peliculasServicio.obtenerPeliculaPorId(id);
        if (pelicula != null) {
            model.addAttribute("pelicula", pelicula);
            return "vistaPelicula"; // Asegúrate de que este es el nombre correcto del archivo HTML
        }
        return "error"; // O alguna página de error si no se encuentra la película
    }
}
