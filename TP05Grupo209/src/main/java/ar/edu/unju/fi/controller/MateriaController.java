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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	MateriaDTO materiaDTO;

	@Autowired
	DocenteDTO docente;

	@Autowired
	IMateriaService materiaService;

	@Autowired
	ICarreraService carreraService;

	@Autowired
	IDocenteService docenteService;

	@GetMapping("formularioMateria")
	public ModelAndView getFormulario() {
		ModelAndView mv = new ModelAndView("formMateria");
		mv.addObject("materia", materiaDTO);
		mv.addObject("isEdit", false);
		mv.addObject("docentes", docenteService.getListaDocentes());
		mv.addObject("carreras", carreraService.getListaCarreras());
		mv.addObject("materias", materiaService.getListaMaterias());
		return mv;
	}

	@GetMapping("listadoMaterias")
	public ModelAndView getListaMaterias() {
		ModelAndView mv = new ModelAndView("listadoMaterias");
		mv.addObject("materias", materiaService.getListaMaterias());
		mv.addObject("docentes", docenteService.getListaDocentes());
		mv.addObject("carreras", carreraService.getListaCarreras());
		return mv;
	}
	
	 @PostMapping("guardarMateria")
	    public ModelAndView guardarMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result) {
	        if (result.hasErrors()) {
	            ModelAndView mv = new ModelAndView("formMateria");
	            mv.addObject("materia", materiaDTO);
	            mv.addObject("isEdit", materiaDTO.getCodigo() != null);
	            return mv;
	        } else {
	            if (materiaDTO.getCodigo() != null) {
	                materiaService.actualizarMateria(materiaDTO);
	            } else {
	                materiaService.agregarUnaMateria(materiaDTO);
	            }
	            return new ModelAndView("redirect:/materia/listadoMaterias");
	        }
	    }

	@GetMapping("/modificar/{codigo}")
	public ModelAndView modificarMateria(@PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView("formMateria");
		mv.addObject("docentes", docenteService.getListaDocentes());
		mv.addObject("carreras", carreraService.getListaCarreras());
		mv.addObject("materia", materiaService.findMateriaByCodigo(codigo));
		mv.addObject("isEdit", true);
		return mv;
	}

	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView deleteMateria(@PathVariable("codigo") Long codigo) {
		materiaService.eliminarUnaMateria(codigo);
		return new ModelAndView("redirect:/materia/listadoMaterias");
	}

}
