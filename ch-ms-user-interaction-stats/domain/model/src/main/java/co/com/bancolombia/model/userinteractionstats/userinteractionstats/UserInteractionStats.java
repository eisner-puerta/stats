package co.com.bancolombia.model.userinteractionstats.userinteractionstats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserInteractionStats {
    private Integer totalContactoClientes;
    private Integer motivoReclamo;
    private Integer motivoGarantia;
    private Integer motivoDuda;
    private Integer motivoCompra;
    private Integer motivoFelicitaciones;
    private Integer motivoCambio;
    private String hash;
}
