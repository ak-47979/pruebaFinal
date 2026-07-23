package ec.edu.ec.application.service;

import ec.edu.ec.domain.model.Ciudadano;
import ec.edu.ec.infraestructure.repository.CiudadanoRepositoryImpl;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Paths;

@ApplicationScoped
public class CiudadanoService {

    @Inject
    CiudadanoRepositoryImpl ciudadanoRepository;

    public Uni<String> procesarArchivoMasivo(String rutaArchivo) {
        return Uni.createFrom().item(() -> {
            try {
                return Files.readAllLines(Paths.get(rutaArchivo));
            } catch (Exception e) {
                throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
            }
        })
                .emitOn(Infrastructure.getDefaultWorkerPool())
                .onItem().transformToUni(lineas ->
                Uni.combine().all().unis(
                        lineas.stream()
                                .filter(linea -> !linea.isBlank() && !linea.startsWith("cedula"))
                                .map(this::guardarLinea)
                                .toList())
                        .discardItems())
                .onItem().transform(v -> "Procesamiento masivo completado exitosamente.");
    }

    private Uni<Ciudadano> guardarLinea(String linea) {
        String[] datos = linea.split(",");
        if (datos.length >= 4) {
            Ciudadano ciudadano = new Ciudadano();
            ciudadano.setCedula(datos[0].trim());
            ciudadano.setNombre(datos[1].trim());
            ciudadano.setApellido(datos[2].trim());
            ciudadano.setGenero(datos[3].trim());

            return Uni.createFrom().item(() -> {
                QuarkusTransaction.requiringNew().run(() -> ciudadanoRepository.persist(ciudadano));
                return ciudadano;
            });
        }
        return Uni.createFrom().nullItem();
    }
}