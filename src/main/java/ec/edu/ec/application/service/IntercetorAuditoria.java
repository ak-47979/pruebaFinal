package ec.edu.ec.application.service;

import java.time.LocalDateTime;

import ec.edu.ec.domain.model.Auditar;
import ec.edu.ec.domain.model.Vehiculo;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Auditarcion
@Interceptor
public class IntercetorAuditoria {
    @Inject
    private AuditarService auditarService;
    @Inject
    private VehiculoService vehiculoService;
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        String accion = context.getMethod().getName();
        LocalDateTime fecha = LocalDateTime.now();
        String placa = obtenerPlaca(context.getParameters());

        System.out.println("Acción: " + accion + ", Fecha: " + fecha + ", Placa: " + placa);

        Auditar auditar = this.vehiculoService.buscarVehiculoPorPlaca(placa);
        if (auditar == null) {
            auditar = new Auditar();
            auditar.setPlaca(placa);
            auditar.setInsert(0);
            auditar.setSelect(0);
            auditar.setUpdate(0);
            auditar.setDelete(0);
        }

        if (accion.toLowerCase().contains("guardar") || accion.toLowerCase().contains("insert")) {
            auditar.setMetodo("Insertar");
            auditar.setInsert(auditar.getInsert() == null ? 1 : auditar.getInsert() + 1);
        } else if (accion.toLowerCase().contains("actualizar") || accion.toLowerCase().contains("update")) {
            auditar.setMetodo("Actualizar");
            auditar.setUpdate(auditar.getUpdate() == null ? 1 : auditar.getUpdate() + 1);
        } else if (accion.toLowerCase().contains("eliminar") || accion.toLowerCase().contains("delete")) {
            auditar.setMetodo("Eliminar");
            auditar.setDelete(auditar.getDelete() == null ? 1 : auditar.getDelete() + 1);
        } else if (accion.toLowerCase().contains("buscar") || accion.toLowerCase().contains("consult") || accion.toLowerCase().contains("todos")) {
            auditar.setMetodo("Consultar");
            auditar.setSelect(auditar.getSelect() == null ? 1 : auditar.getSelect() + 1);
        }

        this.auditarService.guardarAudi(auditar);
        return context.proceed();
    }

    private String obtenerPlaca(Object[] parametros) {
        for (Object parametro : parametros) {
            if (parametro instanceof Vehiculo vehiculo && vehiculo.getPlaca() != null) {
                return vehiculo.getPlaca();
            }
        }
        return "PCT9585";
    }
}
