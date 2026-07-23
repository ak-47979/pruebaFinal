package ec.edu.ec.infraestructure.repository;

import ec.edu.ec.domain.model.Ciudadano;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CiudadanoRepositoryImpl implements PanacheRepositoryBase<Ciudadano, Integer>{

}
