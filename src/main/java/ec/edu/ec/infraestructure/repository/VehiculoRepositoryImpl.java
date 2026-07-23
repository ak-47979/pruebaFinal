package ec.edu.ec.infraestructure.repository;

import ec.edu.ec.domain.model.Vehiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
@ApplicationScoped
@Transactional
public class VehiculoRepositoryImpl implements PanacheRepositoryBase<Vehiculo,Integer> {
    public Vehiculo findByPlaca(String placa) {
        return find("placa", placa).firstResult();
    }

    public boolean deleteByPlaca(String placa) {
        return delete("placa", placa) > 0;
    }
}
