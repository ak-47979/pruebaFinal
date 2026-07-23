package ec.edu.ec.infraestructure.repository;

import ec.edu.ec.domain.model.Auditar;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class AuditarRepositoryImpl implements PanacheRepositoryBase<Auditar, Integer> {
   
}
