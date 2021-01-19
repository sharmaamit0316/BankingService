# BankingService

Description
This Project provides APIs for user to Update Account Details, Add new transactions and retrieve them from the In-Memory H2 Database.
Using the following endpoints, different operations can be achieved:

/updateAccount - This API updates the account details and returns the updated account detail. Eg: {"accountNumber": 111111111112,"balance": 2000,"lastUpdatedTimeStamp": "2021-01-17 21:02:43.532Z"}

/insertTransaction - This API allows to insert Transactions and update the account balance. Eg:  { "accountNumber": 111111111112,"transactionTimeStamp": "2021-01-17 22:11:37.267Z","amount": 600.00, "type": "DEPOSIT" }

/getTransactions - API to get transactions based on AccountNumber, Type and Date range. Eg. {"accountNumber": "111111111112","startDate":"15-01-2021","endDate":"2021-01-17"}

/getAccount- API to get account and transaction details. Eg: {"accountNumber": "111111111112","startDate":"15-01-2021","endDate":"2021-01-17"}

Libraries used:

Spring Boot
Spring Configuration
Spring REST Controller
Spring JPA
Spring AOP
Lombok
MapStruct
H2

Development Tools:

Git
IntelliJ IDEA
Postman

Compilation Command:

mvn clean install - Plain maven clean and install
