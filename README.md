# Realtime-Ticketing-System
# Realtime Ticketing System

## Overview
The Realtime Ticketing System is a web application developed using **Spring Boot** and **React**. This platform enables vendors to release tickets for events or services, while customers can purchase tickets in real-time. The backend operations utilize multithreading and synchronization to handle concurrent transactions efficiently, ensuring data consistency and reliability.

## Features
- **Vendor Dashboard**: Vendors can create, manage, and release tickets for their events.
- **Customer Portal**: Customers can browse available tickets and make purchases.
- **Real-time Updates**: Ensures ticket availability is updated instantly.
- **Multithreading Support**: Handles multiple ticket purchases simultaneously.
- **Data Synchronization**: Prevents overselling of tickets through robust backend synchronization mechanisms.

## Technologies Used
### Backend
- Spring Boot (Java)
- Multithreading and synchronization
- RESTful APIs

### Frontend
- React.js
- CSS

### Database
- MySQL 

### Tools
- IntelliJ IDEA (Backend Development)
- Visual Studio Code (Frontend Development)
- Postman (API testing)

## Installation
### Prerequisites
- Java 17+
- Node.js 16+
- MySQL or PostgreSQL database
- Maven (for managing dependencies)

### Steps
1. **Clone the repository:**
   ```bash
   git clone ttps://github.com/gesith/Realtime-Ticketing-System.git
   cd realtime-ticketing-system
   ```
2. **Setup the backend:**
   - Navigate to the `backend` folder.
   - Update the `DatabaseConnection` file with your database credentials.
   - Create a local MySQL database named `TicketingSystem`.
   - Add the following tables:
     - **Customer**: Columns - `name`, `email`, `password`, `is_vip`
     - **Vendor**: Columns - `name`, `email`, `password`
   - Build and run the Spring Boot application:
     ```bash
     mvn clean install
     mvn spring-boot:run
     ```
3. **Setup the frontend:**
   - Navigate to the `frontend` folder.
   - Install dependencies:
     ```bash
     npm install
     ```
   - Start the development server:
     ```bash
     npm start
     ```
4. Access the application at `http://localhost:3000`.

## Usage
1. **For Vendors:**
   - Sign up or log in as a vendor.
   - Use the dashboard to create and manage ticket releases.
2. **For Customers:**
   - Sign up or log in as a customer.
   - Browse the available tickets and purchase them in real-time.
3. **Admin Panel:** (if applicable)
   - Manage vendors, customers, and system configurations.

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes and push them:
   ```bash
   git add .
   git commit -m "Add feature"
   git push origin feature-name
   ```
4. Submit a pull request for review.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For further inquiries or support, please contact:
- **Name:** Gesith Bimsara
- **Email:** your-email@example.com
- **GitHub:** [github.com/your-username](https://github.com/your-username)

---
Thank you for using the Realtime Ticketing System!

