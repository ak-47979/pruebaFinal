package ec.edu.ec.application.service;

import ec.edu.ec.domain.model.Auditar;
import ec.edu.ec.infraestructure.repository.AuditarRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AuditarService {
    @Inject
    private AuditarRepositoryImpl ari;

    public void guardarAudi(Auditar a){
        this.ari.persist(a);

    }
   
}
