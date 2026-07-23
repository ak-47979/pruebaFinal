/*package com.uce.edu.pa2.as.application.service;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.uce.edu.pa2.as.domain.model.Vendedor;
import com.uce.edu.pa2.as.domain.model.venta;
import com.uce.edu.pa2.as.infraestructure.repository.vendedorRepositoryImpl;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class vendedorService {
@Inject
    private vendedorRepositoryImpl vendedorRepository;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void registrarVendedor(Vendedor vendedor) {
        executorService.submit(() -> {
            this.vendedorRepository.persist(vendedor);
            System.out.println("Vendedor insertado correctamente: " + vendedor.getNombre());
        });

        executorService.submit(() -> {
            this.generarReporteVendedor(vendedor);
        });
    }

    public void actualizarVendedor(Vendedor vendedor) {
        Vendedor vendedorE = this.buscarVendedor(vendedor.getCedula());
        if (vendedor != null) {
            vendedorE.setNombre(vendedor.getNombre());
            vendedorE.setCorreo(vendedor.getCorreo());
            vendedorE.setVentas(vendedor.getVentas());
        }
    }

    public void eliminarVendedor(String id) {
        Vendedor vendedorE = this.buscarVendedor(id);
                if(vendedorE != null) {
            this.vendedorRepository.delete(vendedorE);
        }
    }

    public Vendedor buscarVendedor(String id) {
        return this.vendedorRepository.findById(id);
    }
    @Auditarcion
    public List<Vendedor> buscarTodos() {
        return this.vendedorRepository.findAll().list();
    }
  
    public void generarReporteVendedor(Vendedor vendedor) {
        List<venta> ventas = vendedor.getVentas();
        double totalVentas = ventas.stream().mapToDouble(venta -> venta.getTotal().doubleValue()).sum();
        System.out.println("Reporte de ventas del vendedor " + vendedor.getNombre() + ":");
        System.out.println("Total de ventas: " + totalVentas);
        System.out.println("Detalle de ventas:");
        for (venta v : ventas) {
            System.out.println("Venta ID: " + v.getId() + ", Nombre: " + v.getNombreVen() + ", Total: " + v.getTotal() + ", Fecha: " + v.getFecha());
        }
    }
    @Auditarcion
    public Uni<Tuple2<Vendedor, List<venta>>> buscarVendedorConVentas(String cedula) {
        return Uni.combine().all().unis(
                Uni.createFrom().item(this.buscarVendedor(cedula)),
                Uni.createFrom().item(this.vendedorRepository.findById(cedula).getVentas())
        ).asTuple();
    }
     
    
public Uni<Void> insertarYEliminarVendedor(Vendedor vendedor, String cedula) {
        return Uni.createFrom().voidItem()
                .onItem().invoke(() -> this.registrarVendedor(vendedor))
                .onItem().invoke(() -> this.eliminarVendedor(cedula));
    }
    //metodo que obtenga los 10 primeros vendedores con mas ventas y el total de ventas de cada uno
    public List<Tuple2<Vendedor, Double>> obtenerTop10VendedoresConMasVentas() {
        List<Vendedor> vendedores = this.vendedorRepository.findAll().list();
        return vendedores.stream()
                .map(vendedor -> {
                    double totalVentas = vendedor.getVentas().stream()
                            .mapToDouble(venta -> venta.getTotal().doubleValue())
                            .sum();
                    return Tuple2.of(vendedor, totalVentas);
                })
                .sorted((v1, v2) -> Double.compare(v2.getItem2(), v1.getItem2()))
                .limit(10)
                .toList();  
    }
}
*/