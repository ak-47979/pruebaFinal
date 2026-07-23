package ec.edu.ec.application.service;

import java.util.List;

import ec.edu.ec.domain.model.Vehiculo;
import ec.edu.ec.infraestructure.repository.VehiculoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@Transactional
@ApplicationScoped
@Auditarcion
public class VehiculoService {
    @Inject
    public VehiculoRepositoryImpl vri;
    public List<Vehiculo> todos(){
        return this.vri.listAll();
    }

    public void guardarVehiculo(Vehiculo v){
        this.vri.persist(v);
    }
    public void eliminarVehiculo(Integer id){
        this.vri.deleteById(id);
    }
    public void actualizarVehiculoPorPlaca(String placa, Vehiculo vehiculoActualizado) {
        Vehiculo v = this.vri.findByPlaca(placa);
        if (v != null) {
            v.setMarca(vehiculoActualizado.getMarca());
            v.setModelo(vehiculoActualizado.getModelo());
            this.vri.persist(v);
        }
    }
    public Vehiculo buscarVehiculoPorId(Integer id){
        return this.vri.findById(id);
    }
    public Vehiculo buscarVehiculoPorPlaca(String placa){
        return this.vri.findByPlaca(placa);
    }
}
