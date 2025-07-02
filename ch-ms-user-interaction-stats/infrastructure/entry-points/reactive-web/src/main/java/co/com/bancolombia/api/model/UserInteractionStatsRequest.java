package co.com.bancolombia.api.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* UserInteractionStatsRequest
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInteractionStatsRequest {
    private Integer totalContactoClientes = null;
    private Integer motivoReclamo = null;
    private Integer motivoGarantia = null;
    private Integer motivoDuda = null;
    private Integer motivoCompra = null;
    private Integer motivoFelicitaciones = null;
    private Integer motivoCambio = null;
    private String hash = null;
}