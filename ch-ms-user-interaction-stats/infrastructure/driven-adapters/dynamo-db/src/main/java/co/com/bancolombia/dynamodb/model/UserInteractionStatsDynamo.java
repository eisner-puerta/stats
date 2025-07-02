package co.com.bancolombia.dynamodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class UserInteractionStatsDynamo {
    private String timestamp;
    private Integer totalContactoClientes;
    private Integer motivoReclamo;
    private Integer motivoGarantia;
    private Integer motivoDuda;
    private Integer motivoCompra;
    private Integer motivoFelicitaciones;
    private Integer motivoCambio;
    private String hash;

    @DynamoDbPartitionKey
    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
