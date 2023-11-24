package ModeloClases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hib_empleado")
@NamedQuery(name="Empleado.noDepartamento", 
query="SELECT e FROM Empleado e WHERE e.departamento IS NULL")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private Double salario;
	
	private boolean esJefe;
	
	@ManyToOne()
	@JoinColumn(name="departamento")
	private Departamento departamento;
	
	@ManyToMany(mappedBy = "empleados")
	private List<Proyecto> proyectos = new ArrayList<>();
		
	public Empleado(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
			
	@Override
	public String toString() {
		String dep = (departamento == null) ? "¿?" : departamento.getNombre();
		return String.format("Empleado     [%-2d %-25s %s]", id, nombre, dep);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Empleado empleado = (Empleado) obj;
	    return Objects.equals(id, empleado.id);
	}
	
	public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        this.esJefe = (departamento != null && departamento.getJefe() != null && departamento.getJefe().equals(this));
    }
	
	public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }
}