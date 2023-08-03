# Sunbasedata
Developed Sunbase Customer Management web application that allows you to manage customer data using the provided APIs.
Sunbase Customer Management Application
The Customer Management Application is a web application that allows users to manage customer information. Users can log in, view the list of customers, 
add new customers, update existing customer details, and delete customers.

Technologies Usedm:
Java
Spring Boot
Spring Data JPA
MySQL
HTML
CSS
JavaScript
RESTful API

Prerequisites :
Java Development Kit (JDK) 8 or higher
MySQL Database
Node.js and npm (Node Package Manager)

Setup : 
Clone the repository to your local machine:

git clone https://github.com/your-username/customer-management-app.git
cd customer-management-app

Set up the MySQL database:
Create a new database named customers1 in MySQL.
Update the application.properties file located in src/main/resources with your MySQL database connection details.

Build and run the Spring Boot application:
Install the frontend dependencies:

cd src/main/resources/static
npm install

Start the frontend development server:
npm start

Access the application in your web browser at http://localhost:8080.

Usage :
On the login screen, enter your login credentials and click the "Login" button.

After successful login, you will be redirected to the customer list screen, where you can view a list of customers.

To add a new customer, click the "Add New Customer" button, enter the customer details in the form, and click the "Add Customer" button.

To update a customer's details, click the "Edit" button next to the customer's name, make the necessary changes in the form, and click the "Update" button.

To delete a customer, click the "Delete" button next to the customer's name.

API Endpoints :
The application communicates with the backend API to perform various operations. The API endpoints are as follows:

POST /auth: Authenticates the user and returns a bearer token for further API calls.

POST /customers: Creates a new customer.

GET /customers: Retrieves the list of customers.

POST /customers/{customerId}/delete: Deletes a specific customer.

POST /customers/{customerId}/update: Updates a specific customer's details.

Contributing
Contributions to the Customer Management Application are welcome! If you find any issues or want to add new features, feel free to submit a pull request.

Acknowledgments
This application is built using the Spring Boot framework and the Thymeleaf template engine.
Special thanks to SunBase Data for providing the API endpoints used in this application.
