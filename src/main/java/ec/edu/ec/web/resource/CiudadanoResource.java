package ec.edu.ec.web.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ec.edu.ec.application.service.CiudadanoService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

@Path("/ciudadanos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CiudadanoResource {
    @Inject
    CiudadanoService ciudadanoService;
    @POST
    @Blocking
    @Path("/cargar")
    @Consumes(MediaType.TEXT_PLAIN) 
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> cargarCiudadanos(String rutaArchivo) {
        return ciudadanoService.procesarArchivoMasivo(rutaArchivo);
    }
}