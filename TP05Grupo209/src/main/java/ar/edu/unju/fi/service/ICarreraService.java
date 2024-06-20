package ar.edu.unju.fi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Carrera;

@Service
public interface ICarreraService {
    List<Carrera> getListaCarreras();
    Carrera findCarreraById(String id);
    void agregarUnaCarrera(Carrera carrera);
    void actualizarCarrera(Carrera carrera);
    void eliminarUnaCarrera(String id);
}
