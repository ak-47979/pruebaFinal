package ec.edu.ec.infraestructure.repository;

import ec.edu.ec.domain.model.Auditar;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class AuditarRepositoryImpl implements PanacheRepositoryBase<Auditar, Integer> {

    public void actualizarAudi(Auditar auditar) {
        Auditar existingAuditar = find("placa", auditar.getPlaca()).firstResult();
        if (existingAuditar != null) {
            existingAuditar.setInsert(auditar.getInsert());
            existingAuditar.setSelect(auditar.getSelect());
            existingAuditar.setUpdate(auditar.getUpdate());
            existingAuditar.setDelete(auditar.getDelete());
            existingAuditar.setMetodo(auditar.getMetodo());
            persist(existingAuditar);
        }
    }
   
}
