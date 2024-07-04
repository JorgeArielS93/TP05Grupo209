package ar.edu.unju.fi.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoDTO alumnoDTO;

    @Autowired
    private MateriaDTO materiaDTO;
    
    @Autowired
    private CarreraDTO carreraDTO;
    
    @Autowired
    private IAlumnoService alumnoService;

    @Autowired
    private ICarreraService carreraService;

    @Autowired
    private IMateriaService materiaService;

    @GetMapping("formularioAlumno")
    public ModelAndView getFormulario() {
        ModelAndView mv = new ModelAndView("formAlumno");
        mv.addObject("alumno", alumnoDTO);
        mv.addObject("isEdit", false);
        mv.addObject("listaCarreras", carreraService.getListaCarreras());
        mv.addObject("listaMaterias", materiaService.getListaMaterias());
        return mv;
    }

    @GetMapping("listaAlumnos")
    public ModelAndView getListaAlumnos() {
        ModelAndView mv = new ModelAndView("listaAlumnos");
        mv.addObject("alumnos", alumnoService.getListaAlumnos());
        return mv;
    }

    @PostMapping("guardarAlumno")
    public ModelAndView guardarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, BindingResult result) {
        if(result.hasErrors()){
        	ModelAndView mv = new ModelAndView("formAlumno");
            mv.addObject("alumno", alumnoDTO);
            mv.addObject("isEdit", alumnoDTO.getLu() != null);
            mv.addObject("listaCarreras", carreraService.getListaCarreras());
            mv.addObject("listaMaterias", materiaService.getListaMaterias());
            return mv;
        }else {
        	if (alumnoDTO.getLu() != null) {
                alumnoService.actualizarAlumno(alumnoDTO);
            } else {
                alumnoService.agregarUnAlumno(alumnoDTO);
            }
            return new ModelAndView("redirect:/alumno/listaAlumnos");
        }
    }

    @GetMapping("/modificar/{lu}")
    public ModelAndView modificarAlumno(@PathVariable("lu") Long lu) {
        ModelAndView mv = new ModelAndView("formAlumno");
        mv.addObject("alumno", alumnoService.findAlumnoByLu(lu));
        mv.addObject("isEdit", true);
        mv.addObject("listaCarreras", carreraService.getListaCarreras());
        mv.addObject("listaMaterias", materiaService.getListaMaterias());
        return mv;
    }

    @GetMapping("/borrarAlumno/{lu}")
    public ModelAndView deleteAlumno(@PathVariable("lu") Long lu) {
        alumnoService.eliminarUnAlumno(lu);
        return new ModelAndView("redirect:/alumno/listaAlumnos");
    }
    
    @GetMapping("/filtrarPorMateria")
    public ModelAndView seleccionarMateria() {
        ModelAndView mv = new ModelAndView("formAlumnosPorMateria");
        mv.addObject("listaMaterias", materiaService.getListaMaterias());
        mv.addObject("materia", materiaDTO);
        return mv;
    }
    
    @PostMapping("/filtrarPorMateria")
    public String redirigirAlumnosPorMateria(@RequestParam("materiaId") Long materiaId) {
        return "redirect:/alumno/filtrarPorMateria/" + materiaId;
    }
    
    @GetMapping("/filtrarPorMateria/{codigo}")
    public ModelAndView alumnosPorMateria(@PathVariable("codigo")Long codigo) {
        ModelAndView mv = new ModelAndView("listaAlumnosPorMateria");
        List<AlumnoDTO> alumnos = alumnoService.getListaAlumnos();
        List<AlumnoDTO> filtrados = new ArrayList<AlumnoDTO>();

        for (AlumnoDTO a : alumnos) {
        	for (Materia materia : a.getMaterias()) {
        		if(materia.getCodigo().equals(codigo)) {
        			filtrados.add(a);
        		}
        	}
        }
        mv.addObject("alumnos", filtrados);
        return mv;
    }
    
    @GetMapping("/filtrarPorCarrera")
    public ModelAndView seleccionarCarrera() {
        ModelAndView mv = new ModelAndView("formAlumnosPorCarrera");
        mv.addObject("listaCarreras", carreraService.getListaCarreras());
        mv.addObject("carrera", carreraDTO);
        return mv;
    }
    
    @PostMapping("/filtrarPorCarrera")
    public String redirigirAlumnosPorCarrera(@RequestParam("carreraId") Long carreraId) {
        return "redirect:/alumno/filtrarPorCarrera/" + carreraId;
    }
    
    @GetMapping("/filtrarPorCarrera/{codigo}")
    public ModelAndView alumnosPorCarrera(@PathVariable("codigo") Long codigo) {
        ModelAndView mv = new ModelAndView("listaAlumnosPorCarrera");
        List<AlumnoDTO> alumnos = alumnoService.getListaAlumnos();
        List<AlumnoDTO> filtrados = new ArrayList<AlumnoDTO>();

        for (AlumnoDTO a : alumnos) {
        		if(a.getCarrera().getId().equals(codigo)) {
        			filtrados.add(a);
        		}
        }
        mv.addObject("alumnos", filtrados);
        return mv;
    }
    
}
