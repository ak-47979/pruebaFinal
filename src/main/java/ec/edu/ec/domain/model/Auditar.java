package ec.edu.ec.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="auditar")
public class Auditar {
    @Id
    @SequenceGenerator(name="seq_audi_g", sequenceName="seq_audi",allocationSize=1)
    @GeneratedValue(generator="seq_audi_g")
    @Column(name = "au_id")
    private Integer id;

    @Column(name= "au_placa")
    private String placa;
    
    @Column(name="au_insert")    
    private Integer insert;

    @Column(name = "au_up")
    private Integer update;

    @Column(name = "au_select")
    private Integer select;

    @Column(name ="au_delete")
    private Integer delete;


    @Column(name = "au_metodo")
    private String metodo;

    public Auditar(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getInsert() {
        return insert;
    }

    public void setInsert(Integer insert) {
        this.insert = insert;
    }

    public Integer getUpdate() {
        return update;
    }

    public void setUpdate(Integer update) {
        this.update = update;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return "Auditar [id=" + id + ", placa=" + placa + ", insert=" + insert + ", update=" + update + ", select="
                + select + ", delete=" + delete + ", metodo=" + metodo + "]";
    }
   
        
}
