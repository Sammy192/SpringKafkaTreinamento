![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka)

# Microserviços com Mensageria Kafka

# Projeto Validador de Boleto

Projeto desenvolvido durante treinamento da JDevTreinamentos com objetivo de aprofundar conhecimentos sobre Mensageria com Kafka.

Instrutor: Luan Rocha.

### Tecnologias utilizadas
- Java
- Spring Boot
- Docker
- Apache Kafka
- Apache Avro
- Control Center
- Schema Registry
- Banco de dados H2

### Arquitetura do projeto
O projeto consiste em aplicações responsáveis por recepcionar, validar e pagar um boleto, 
onde a comunicação entre sistema acontece por mensageria.

Recebido um código de barras a aplicação salva em banco de dados h2 em memória o boleto e status INICIALIZADO.  
Em seguida a aplicação como Producer envia uma mensagem com o boleto para a aplicação 'validar-boleto' que inicia processo de validar o código de barras do boleto.

A aplicação 'validar-boleto' como Consumer recebe a mensagem.  
Inicia-se o processo de validar boleto e validar pagamento.  
Tendo um boleto válido o status é VALIDADO. Inválido é ERRO_VALIDACAO.  
Inicia-se o processo de pagamento.  
Tendo pagamento aprovado o status é PAGO. Tendo pagamento negado status é ERRO_PAGAMENTO.  
A aplicação validar-boleto notifica como um Producer enviando uma mensagem para a aplicação 'apiBoleto' o boleto atualizado com o status.  
A aplicacao 'apiBoleto' como um consumer recebe o boleto atualizado e atualiza o mesmo em banco.  


![Alt Text](./images/arquitetura.gif)

# Para acessar:
Inicie os containers docker com 'docker compose up -d'
acesse o painel de gerenciamento do Kafka em 'http://localhost:9021'
Crie o tópico: 'solicitacao-boleto'
Coloque como shema o seguinte json: Valide e Create. Vide imagem abaixo
````
{
    "type": "record",
    "namespace": "br.com.rocha.avro",
    "name": "Boleto",
    "fields": [
        {
            "name": "codigoBarras",
            "type": "string"
        },
        {
            "name": "situacaoBoleto",
            "type": "int"
        }
    ]
}
````

![image](https://github.com/user-attachments/assets/53988335-6ebb-4dbb-abbe-51a659de9ab8)

Inicie a aplicação 'ApiBoleto'
Inicie a aplicação 'ValidadorBoleto'

No painel de gerenciamento do Kafka
adicione o schema registry para o topico já criado pela aplicação 'notificacao-boleto'. Valide e Create.

````
{
    "type": "record",
    "namespace": "br.com.rocha.avro",
    "name": "Boleto",
    "fields": [
        {
            "name": "codigoBarras",
            "type": "string"
        },
        {
            "name": "situacaoBoleto",
            "type": "int"
        }
    ]
}
````
![image](https://github.com/user-attachments/assets/4a2a07a2-8e51-400f-b16e-9b1cab4c83b4)


Para acessar documentação no Swagger utilize: 'http://localhost:8282/swagger-ui.html'

Abaixo alguns codigosBarras de exemplo:

- Para ocorrer status 'PAGO' caso de sucesso    
 use codigoBarras = '123'

- Para ocorrer status 'ERRO_VALIDACAO'  
Essa validacao ocorre no momento de validar o codigoBarras do boleto  
Se este codigoBarras é iniciado por 2  
use codigoBarras = '256'

- Para ocorrer status 'ERRO_PAGAMENTO':  
Este status ocorre quando na etapa de validacao de pagamento a quantidade de numeros é menor que 47 caracteres numéricos.  
Essa validação é executada depois de validar o boleto que deve ser um codigoBarras iniciado em 1 para ser um boletoValido.  
Use codigoBarras = '12345678998781454654651213210123124asdfadsfadfadfafda4252452452524'

![Screenshot_1](https://github.com/user-attachments/assets/19ff055b-532e-49cb-a162-b91572b5df8a)

![image](https://github.com/user-attachments/assets/a08c8271-a441-4d8c-aab9-61d8ea365e6f)
