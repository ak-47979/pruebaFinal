package ec.edu.ec.application.service;

import ec.edu.ec.domain.model.Vehiculo;
import ec.edu.ec.infraestructure.repository.VehiculoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@Transactional
@ApplicationScoped
public class VehiculoService {
    @Inject
    public VehiculoRepositoryImpl vri;
    public void todos(){
        this.vri.listAll();
    }

    public void guardarVehiculo(ec.edu.ec.domain.model.Vehiculo v){
        this.vri.persist(v);
    }
    public void eliminarVehiculo(Integer id){
        this.vri.deleteById(id);
    }
    public void actualizarVehiculo(Vehiculo v){
        Vehiculo vehiculoExistente = new Vehiculo(); 
        vehiculoExistente.setId(v.getId());
        vehiculoExistente.setPlaca(v.getPlaca());
        vehiculoExistente.setMarca(v.getMarca());
        vehiculoExistente.setModelo(v.getModelo());
        
    
    }
    public Vehiculo buscarVehiculoPorId(Integer id){
        return this.vri.findById(id);
    }
    public Vehiculo buscarVehiculoPorPlaca(String placa){
        return this.vri.findByPlaca(placa);
    }
}
