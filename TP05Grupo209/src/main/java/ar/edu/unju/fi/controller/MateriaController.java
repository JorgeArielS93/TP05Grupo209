package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.IMateriaService;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	 	@Autowired
	    Materia materia;
	 	
	 	@Autowired
		IMateriaService materiaService;

	    @GetMapping("formularioMateria")
	    public ModelAndView getFormulario() {
	        ModelAndView mv = new ModelAndView("formMateria");
	        mv.addObject("materia", materia);
	        mv.addObject("isEdit", false);
	        return mv;
	    }

	    @GetMapping("listadoMaterias")
	    public ModelAndView getListaMaterias() {
	        ModelAndView mv = new ModelAndView("listadoMaterias");
	        mv.addObject("materias", materiaService.getListaMaterias());
	        return mv;
	    }

	    @PostMapping("guardarMateria")
	    public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia) {
	        if (materia.getCodigo() != null && !materia.getCodigo().isEmpty()) {
	            materiaService.actualizarMateria(materia);
	        } else {
	            materiaService.agregarUnaMateria(materia);
	        }
	        return new ModelAndView("redirect:listadoMaterias");
	    }

	    @GetMapping("/modificar/{codigo}")
	    public ModelAndView modificarMateria(@PathVariable("codigo") String codigo) {
	        ModelAndView mv = new ModelAndView("formMateria");
	        mv.addObject("materia", materiaService.findMateriaByCodigo(codigo));
	        mv.addObject("isEdit", true);
	        return mv;
	    }

	    @GetMapping("/borrarMateria/{codigo}")
	    public ModelAndView deleteMateria(@PathVariable("codigo") String codigo) {
	        materiaService.eliminarUnaMateria(codigo);
	        return new ModelAndView("redirect:/materia/listadoMaterias");
	    }

}
