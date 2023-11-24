package ModeloClases;

import java.util.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hib_departamento")
@NamedQueries({
	@NamedQuery(name="Departamento.findByNombre", 
			query="SELECT d FROM Departamento d WHERE d.nombre LIKE :nombre"),
	@NamedQuery(name="Departamento.findByEmpleado", 
			query="SELECT DISTINCT d FROM Empleado e JOIN e.departamento d WHERE e.id = :id")
})
public class Departamento {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    
    @OneToMany(mappedBy = "departamento")
    private Set<Empleado> empleados = new HashSet<>();

    public Departamento(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Departamento(Integer id) {
        this.id = id;
    }

    public void addEmpleado(Empleado e) {
        if (e.getDepartamento() != null) {
            e.getDepartamento().getEmpleados().remove(e);
        }
        e.setDepartamento(this);
        empleados.add(e);
    }

    public void removeEmpleado(Empleado e) {
        if (e.isEsJefe()) {
            e.setEsJefe(false); // Si el empleado es jefe, se actualiza a no serlo
        }
        e.setDepartamento(null);
        empleados.remove(e);
    }

    public Empleado getJefe() {
        return empleados.stream()
                .filter(Empleado::isEsJefe) // Suponiendo que isEsJefe es el getter de esJefe
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public String toString() {
        List<String> emps = empleados.stream()
                .map(Empleado::getNombre)
                .sorted()
                .toList();
        return String.format("Departamento [%-2d %-25s %s]", id, nombre, emps);
    }
    
    public void setJefe(Empleado nuevoJefe) {
        if (nuevoJefe != null && empleados.contains(nuevoJefe)) {
            empleados.forEach(empleado -> empleado.setEsJefe(empleado.equals(nuevoJefe)));
        }
    }
}
