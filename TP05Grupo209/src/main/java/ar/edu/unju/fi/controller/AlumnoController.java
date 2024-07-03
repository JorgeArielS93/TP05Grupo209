package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.AlumnoDTO;
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
    
    
}
