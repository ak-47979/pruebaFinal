package ec.edu.ec.web.resource;

import ec.edu.ec.application.service.VehiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/vehiculo")
public class VehiculoResource {

    @Inject
    
    private VehiculoService aud;
    @Path("/todos")
    @GET
    public void VehiculosTodos(){
        this.aud.todos();
    }

    @Path("/guardar")
    @POST 
    public void guardarVehiculo(ec.edu.ec.domain.model.Vehiculo v){
        this.aud.guardarVehiculo(v);
    }
    @Path("/eliminar")
    @POST
    public void eliminarVehiculo(Integer id){
        this.aud.eliminarVehiculo(id);
    }
    @Path("/actualizar")
    @POST
    public void actualizarVehiculo(ec.edu.ec.domain.model.Vehiculo v){
        this.aud.actualizarVehiculo(v);
    }
}
