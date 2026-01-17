# Banking System

A Java-based banking system application with database integration, allowing users to create accounts, perform deposits and withdrawals, and manage their bank account information.

## Features

- **User Authentication**: Login with owner name and password
- **Account Management**: 
  - Create new bank accounts with initial balance
  - Delete existing accounts
  - View account information
- **Financial Operations**:
  - Deposit money into accounts
  - Withdraw money from accounts (with insufficient funds validation)
  - Real-time balance updates
- **Data Persistence**: All account data is stored in a MySQL database

## Technology Stack

- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: MySQL
- **Database Driver**: MySQL Connector/J 5.1.49

## Project Structure

```
demo/
├── pom.xml                           # Maven configuration
├── database/
│   └── queries.sql                   # Database setup scripts
└── src/
    ├── main/
    │   └── java/com/banking_system/
    │       ├── Main.java             # Application entry point
    │       ├── dao/
    │       │   ├── DBConnection.java      # Database connection handler
    │       │   ├── AccountDao.java        # Account DAO interface
    │       │   └── AccountDaoImplementation.java  # DAO implementation
    │       ├── model/
    │       │   └── Account.java       # Account model class
    │       ├── ui/
    │       │   └── Menu.java          # User interface menus
    │       └── Utils/
    │           └── InputHandle.java   # Input validation utilities
    └── test/
        └── java/                     # Test classes
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6+
- MySQL Server
- MySQL Connector/J

### Installation

1. **Clone/Download the project**
   ```bash
   cd demo
   ```

2. **Set up the database**
   - Start your MySQL server
   - Execute the database setup script:
   ```bash
   mysql -u root -p < database/queries.sql
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Configure database connection**
   - Update your database credentials in `DBConnection.java` if needed

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.banking_system.Main"
```

Or run the compiled JAR:
```bash
java -jar target/demo-1.0-SNAPSHOT.jar
```

## Usage

### Main Menu

When the application starts, you'll see the main menu with three options:

1. **Login** - Access an existing account with owner name and password
2. **Create Account** - Register a new bank account with:
   - Owner name
   - Initial balance
   - Password (numeric only)
3. **Exit** - Close the application

### Account Operations

Once logged in, you can perform the following operations:

1. **Deposit** - Add funds to your account (amount must be positive)
2. **Withdraw** - Remove funds from your account (checks for sufficient balance)
3. **Display Info** - View current account owner and balance
4. **Delete Account** - Permanently remove your account from the system
5. **Logout** - Return to the main menu

## Database Schema

The system uses a single `accounts` table:

```sql
CREATE TABLE accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_owner VARCHAR(100) NOT NULL,
    password INT NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Class Descriptions

### Main
Entry point of the application. Handles the main program flow, user login, account creation, and menu navigation.

### Account
Model class representing a bank account with properties:
- `ownerName`: Account holder's name
- `Balance`: Current account balance
- `password`: Numeric password for authentication

Methods include deposit, withdraw, and account information display.

### AccountDao & AccountDaoImplementation
Data Access Object pattern implementation for database operations:
- Create new accounts
- Retrieve account information
- Update account balance
- Delete accounts

### DBConnection
Manages MySQL database connections using sql.Connection.

### Menu
Provides user interface menus for:
- Login/Registration screen
- Account operations menu

### InputHandle
Utility class for validating and handling user input:
- Integer input validation
- Double input validation

## Error Handling

The system includes validation for:
- Invalid login credentials
- Insufficient funds for withdrawals
- Negative deposit/withdrawal amounts
- Invalid menu selections

## Future Enhancements

- Transaction history tracking
- Interest calculation
- Multiple account types
- Transfer between accounts
- Account statement export
- Security improvements (encrypted passwords)

## License

This project is part of the MY STUDIES - Java Standard course.

## Author

Created as a learning project for Java standard libraries and database integration.
