package ec.edu.ec.application.service;

import ec.edu.ec.domain.model.Auditar;
import ec.edu.ec.domain.model.Vehiculo;
import ec.edu.ec.infraestructure.repository.AuditarRepositoryImpl;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.transaction.Transactional;

@Auditarcion
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class IntercetorAuditoria {

    @Inject
    AuditarRepositoryImpl auditarRepository;

    @AroundInvoke
    @Transactional
    public Object intercept(InvocationContext context) throws Exception {
        Object result = context.proceed();

        String accion = context.getMethod().getName().toLowerCase();
        String placa = obtenerPlaca(context.getParameters(), result);

        if (placa != null && !placa.isBlank()) {
            Auditar auditar = auditarRepository.find("placa", placa).firstResult();
            
            if (auditar == null) {
                auditar = new Auditar();
                auditar.setPlaca(placa);
                auditar.setInsert(0);
                auditar.setSelect(0);
                auditar.setUpdate(0);
                auditar.setDelete(0);
            }

            if (accion.contains("guardar") || accion.contains("insert")) {
                auditar.setMetodo("Insertar");
                auditar.setInsert(auditar.getInsert() + 1);
            } else if (accion.contains("actualizar") || accion.contains("update")) {
                auditar.setMetodo("Actualizar");
                auditar.setUpdate(auditar.getUpdate() + 1);
            } else if (accion.contains("eliminar") || accion.contains("delete")) {
                auditar.setMetodo("Eliminar");
                auditar.setDelete(auditar.getDelete() + 1);
            } else if (accion.contains("buscar") || accion.contains("consult") || accion.contains("todos")) {
                auditar.setMetodo("Consultar");
                auditar.setSelect(auditar.getSelect() + 1);
            }

            auditarRepository.persist(auditar);
        }

        return result;
    }

    private String obtenerPlaca(Object[] parametros, Object resultado) {
        for (Object param : parametros) {
            if (param instanceof String str) {
                return str;
            }
            if (param instanceof Vehiculo v && v.getPlaca() != null) {
                return v.getPlaca();
            }
        }
        if (resultado instanceof Vehiculo v) {
            return v.getPlaca();
        }
        return null;
    }
}