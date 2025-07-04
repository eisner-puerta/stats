Requisitos:
1. Docker Desktop (Iniciado)
2. aws cli
3. gradle
4. java

Instrucciones:
1. Clonar el repositorio git clone https://github.com/eisner-puerta/stats.git
2. Ubicarse en la carpeta stats
3. Iniciar docker desktop
4. Ejecutar docker-compose up -d
5. Para crear la tabla UserInteractionStats Ejecutar aws dynamodb create-table --table-name UserInteractionStats --attribute-definitions AttributeName=timestamp,AttributeType=S --key-schema AttributeName=timestamp,KeyType=HASH --billing-mode PAY_PER_REQUEST --table-class STANDARD --region us-east-1 --endpoint-url http://localhost:4566
6. Ubicarse en la carpeta ch-ms-user-interaction-stats
7. Ejecutar gradle bootRun
8. Importar en postman el collection reto.postman_collection.json que está en la carpeta stats
9. Para ejecutar las pruebas. gradle test
