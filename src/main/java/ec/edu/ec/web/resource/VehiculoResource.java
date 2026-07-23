package ec.edu.ec.web.resource;

import java.util.List;

import ec.edu.ec.application.service.VehiculoService;
import ec.edu.ec.domain.model.Vehiculo;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/vehiculo")
public class VehiculoResource {

    @Inject    
    private VehiculoService aud;
    
    @Path("/todos")
    @GET
    public List<Vehiculo> VehiculosTodos(){
        return this.aud.todos();
    }

    @Path("/guardar")
    @POST 
    public void guardarVehiculo(Vehiculo v){
        this.aud.guardarVehiculo(v);
    }
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminarVehiculo(@PathParam("id")Integer id){
        this.aud.eliminarVehiculo(id);
    }
    @Path("/actualizar")
    @PUT
    public void actualizarVehiculo(@PathParam("placa") String placa, Vehiculo v){
        this.aud.actualizarVehiculoPorPlaca(placa, v);
    }
    @GET
    @Path("/buscar/{placa}")
    public Response buscarPorPlaca(@PathParam("placa") String placa) {
        Vehiculo v = this.aud.buscarVehiculoPorPlaca(placa);
        if (v == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(v).build();
    }
}
