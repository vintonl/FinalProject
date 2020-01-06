## Gear Silo - Peer-To-Peer Outdoor Gear Rentals

## Angular and RESTful API Software Application

### Week 16 Final Project at Skill Distillery - January 7, 2020

Built by:

-   [Vinton Lee](https://github.com/vintonl)
-   [Colt Looper](https://github.com/calooper)
-   [Adam Onwan](https://github.com/AdamOnwan)
-   [Jerry Rogers, Jr](https://github.com/jerryrogersjr)
-   [Brian Streetman](https://github.com/Briman-Jag)

This was a two-week-long sprint use Agile Methodologies to exceed the minimum viable product for our final [assignment](https://github.com/calooper/FinalProject/blob/master/Assignment.md).

### Overview:

This program is designed to be a full-stack RESTful software application that creates, reads, updates and deletes user, gear, reservation data from a database we built using MySQL Workbench and connecting with using Java Database Connectivity.

### How to run:

First, the user enters the home page. The user can choose to:

-   See the available gear to rent
-   Login
-   Join our community

Demo Account:

-   Email: martymcfly@gmail.com
-   Password: gear

A logged-in user can:

-   Update their profile
-   Make reservations
-   Rate and make a review comment after the reservation
-   Add gear to rent
-   Approve reservations
-   Mark reservations complete

The admin user can:

-   Remove a review
-   Deactivate or reactivate a user

Admin Account:

-   Email: gearsilo@gmail.com
-   Password: gear

### Technologies used:

Java 1.8 / EE, Java Persistence API (JPA), REST API, Spring Boot, Spring Security, JUnit, Gradle, Hibernate, JSON, TypeScript, Angular, Visual Studio Code, HTML5, CSS, Bootstrap, XML, MySQL Workbench, Postman, Apache Tomcat, Amazon Web Services (AWS), Atom, Git, GitHub, Trello, and Slack.

### Topics implemented:

-   Build a front-end with TypeScript, Angular, and VS Code.
-   Send asynchronous requests to Java controllers with HTTP to perform CRUD RESTfully.
-   Consume and parse JSON responses with Angular.
-   Dynamic Web Applications using Spring Boot.
-   Tomcat 8 on AWS EC2 Instance.
-   CCS, Material, and Bootstrap to make it web and mobile-friendly.
-   Object-Relational Mapping (ORM).
-   Relational Database: large multi-table SQL Database creation using MySQL Workbench.
-   Java Libraries: SQL, List, and handling exceptions.
-   Object-Oriented Programming in Java: Abstraction, Polymorphism, Inheritance, and Encapsulation.
-   Test-Driven Development using JUNIT Juniper.

### Lessons learned:

This project helped us better grasp the capabilities of Dynamic Web Applications by allowing us to better learn how to:

-   Build a multi-table database using MySQL Workbench.
-   Be an Agile and Scrum development team
-   Use branching on Git/GitHub
-   Understand Spring Security.
-   Use the Angular to generate HTML.
-   Build a Dynamic Web Applications using Spring Boot RESTful API endpoints.
-   CCS, Material, and Bootstrap to make it web and mobile-friendly.

### Public REST API Routes

| Return Type  | Route           | Functionality      |
| ------------ | --------------- | ------------------ |
| `List<Gear>` | `GET api/gears` | Get all gear items |

### Secure REST API Routes

| Return Type                | Route                                                                               | Functionality                        |
| -------------------------- | ----------------------------------------------------------------------------------- | ------------------------------------ |
| `User`                     | `POST register`                                                                     | Create a register user               |
| `User`                     | `GET authenticate`                                                                  | Authenticate a user                  |
| `User`                     | `PUT api/users/{id}`                                                                | Update a user by id                  |
| `User`                     | `PUT api/users/admin`                                                               | Update a user by enabled or disabled |
| `User`                     | `GET api/users/{id}`                                                                | Get a user by id                     |
| `User`                     | `GET api/users/{username}`                                                          | Get a user by username               |
| `List<User>`               | `GET api/users`                                                                     | Get all users                        |
| `List<Gear>`               | `GET api/gears/users`                                                               | Get list of a user's gear            |
| `Gear`                     | `DELETE api/gears/{gearId}`                                                         | Deactivate gear by id                |
| `Gear`                     | `POST api/gears/users`                                                              | Create gear item                     |
| `Gear`                     | `PUT api/gears/users/{gearId}`                                                      | Update gear item by id               |
| `List<ReviewOfGear>`       | `GET api/users/{gearId}/reviews/gearreviews`                                        | Get all gear reviews by user id      |
| `List<Category>`           | `GET api/categories`                                                                | Get all categories                   |
| `List<ReservationMessage>` | `GET api/users{userId}/reservation/reservationmessages`                             | Get messages by username             |
| `ReservationMessage`       | `POST api/users{userId}/reservation/{reservationId}/reservationmessages`            | Create message                       |
| `ReservationMessage`       | `PUT api/users{userId}/reservation/{reservationId}/reservationmessages/{messageId}` | Update message                       |
| `Reservation`              | `POST api/reservation`                                                              | Create a reservation                 |
| `List<Reservation>`        | `GET api/reservation/users`                                                         | Get all reservations                 |
| `List<Reservation>`        | `GET api/reservation/users/shoppers`                                                | Get all reservations by shopper      |
| `List<ReviewOfGear>`       | `GET api/users/{userId}/reviews/lenderreviews`                                      | Get all lender reviews by user id    |
| `List<ReviewOfGear>`       | `GET api/users/{gearId}/reviews/gearreviews`                                        | Get all gear reviews by user id      |
| `ReviewOfGear`             | `POST api/users/{userId}/reservation/{reservationId}//reviews/gearreviews`          | Create gear review                   |
| `ReviewOfLender`           | `POST api/users/{userId}/reservation/{reservationId}//reviews/lenderreviews`        | Create review of a lender            |
