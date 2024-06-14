package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.IDocenteService;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	Docente docente;
	@Autowired
	IDocenteService docenteService;
	
	@GetMapping("formularioDocente")
	public ModelAndView getDormulario() {
		ModelAndView mv= new ModelAndView("formDocente");
		mv.addObject("maestro",docente);
		mv.addObject("isEdit", false);
        return mv;
	}
	
	@GetMapping("listaDocentes")
	public ModelAndView getListaDocentes() {
		ModelAndView mv= new ModelAndView("listaDocentes");
		mv.addObject("docentes",docenteService.getListaDocentes());
		return mv;
	}
	
	@PostMapping("guardarDocente")
	public ModelAndView guardarDocente(@ModelAttribute("maestro") Docente docente) {
		if (docente.getLegajo() != null && !docente.getLegajo().isEmpty()) {
			docenteService.agregarUnDocente(docente);
		}else {
			docenteService.agregarUnDocente(docente);
		}
		ModelAndView mv = new ModelAndView("redirect:listaDocentes");
		return mv;
	}
	
	 @GetMapping("/modificar/{legajo}")
	    public ModelAndView modificarDocente(@PathVariable("legajo") String legajo) {
	    	ModelAndView mv = new ModelAndView("formDocente");
	    	mv.addObject("maestro",docenteService.findDocenteByLegajo(legajo));
	    	mv.addObject("isEdit", true);
	    	return mv;
	    }
	    
	    @GetMapping("/borrarDocente/{legajo}")
	    public ModelAndView deleteDocente(@PathVariable("legajo") String legajo) {
	    	docenteService.eliminarUnDocente(legajo);
	    	ModelAndView mv= new ModelAndView("redirect:/docente/listaDocentes");
	    	return mv;
	    }
}