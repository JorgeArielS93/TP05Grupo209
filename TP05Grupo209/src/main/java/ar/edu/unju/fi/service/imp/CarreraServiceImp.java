package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;

@Service
public class CarreraServiceImp implements ICarreraService {
	@Autowired
	CarreraRepository carreraRepository;

	@Override
	public List<Carrera> getListaCarreras() {
		return carreraRepository.findByEstado(true);
	}

	@Override
	public Carrera findCarreraById(String id) {
		return carreraRepository.findById(id).orElse(null);
	}

	@Override
	public void agregarUnaCarrera(Carrera carrera) {
		carrera.setId(UUID.randomUUID().toString()); // Asigna un ID único
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	}

	@Override
	public void actualizarCarrera(Carrera carrera) {
		Carrera existingCarrera = carreraRepository.findById(carrera.getId()).orElse(null);
		if (existingCarrera != null) {
			existingCarrera.setCodigo(carrera.getCodigo());
			existingCarrera.setNombre(carrera.getNombre());
			existingCarrera.setCantidadAnios(carrera.getCantidadAnios());
			existingCarrera.setDuracion(carrera.getDuracion());
			existingCarrera.setEstado(carrera.getEstado()); // Conserva el estado existente
			carreraRepository.save(existingCarrera);
		} else {
			throw new RuntimeException("La carrera con ID " + carrera.getId() + " no existe.");
		}
	}

	@Override
	public void eliminarUnaCarrera(String id) {
		Carrera carrera = findCarreraById(id);
		if (carrera != null) {
			carrera.setEstado(false);
			carreraRepository.save(carrera);
		} else {
			throw new RuntimeException("La carrera con ID " + id + " no existe.");
		}
	}
}
