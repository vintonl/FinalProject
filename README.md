## GearSilo

## Angular and RESTful API Software Application

### Week 16 Midterm Project at Skill Distillery - January 7, 2020

Built by:
* [Vinton Lee](https://github.com/vintonl)
* [Colt Looper](https://github.com/calooper)
* [Adam Onwan](https://github.com/AdamOnwan)
* [Jerry Rogers, Jr](https://github.com/jerryrogersjr)
* [Brian Streetmen](https://github.com/Briman-Jag)


This was a two-week-long sprint use Agile Methodologies to exceed the minimum viable product for our midterm  [assignment](https://github.com/calooper/FinalProject/blob/master/Assignment.md).

### Overview:

This program is designed to be a full stack RESTful software application that creates, reads, updates and deletes user and recipe data from a database we built using MySQL Workbench and connecting with using Java Database Connectivity.

### How to run:

First, the user enters the home page. The user can choose to:
* See the available gear to rent
* Login
* Join our community

Demo Account:
* Email: martymcfly@gmail.com
* Password: gear

A logged in user can:
* Update their profile
* Make reservations
* Rate and make a review comment after the reservation
* Add gear to rent
* Approve reservations
* Mark reservations complete

The admin user can:
* Remove a review even if they did not create the review
* Remove any recipe even if they did not create the recipe

Admin Account:
* Email: gearsilo@gmail.com
* Password: gear

### Technologies used:

Java, Java Persistence API, Spring Boot, Spring Security Spring STS, Gradle, SQL, MySQL Workbench, HTML, CSS, Bootstrap, Material,  Apache, Tomcat, AWS, Atom, Git, GitHub, Slack, and Trello.

### Topics implemented:

Dynamic Web Applications using Spring Boot.

Tomcat 8 on AWS EC2 Instance.

JSP Standard Tag Library to generate HTML.

CCS and Bootstrap to make it web and mobile-friendly.

Object-Relational Mapping (ORM).

Relational Database: SQL Database creation using MySQL Workbench.

Java Libraries: SQL, List, and handling exceptions.

Object-Oriented Programming in Java: Abstraction, Polymorphism, Inheritance, and Encapsulation.

Test Driven Development using JUNIT Juniper.

### Lessons learned:

This project helped us better grasp the capabilities of Dynamic Web Applications by allowing us to better learn how to:
* Build a multi-table database using MySQL Workbench.
* Be an Agile development team
* Use branching on Git/GitHub
* Understand Spring Application Contexts.
* Use the Java Persistence API to generate HTML.
* Build a Dynamic Web Applications using Spring MVC.
* CCS and Bootstrap to make it web and mobile-friendly.

### Routes

| Return Type         | Route                                                       | Functionality                                                    |
| ------------------- | ----------------------------------------------------------- | ---------------------------------------------------------------- |
| `List<Gear>`        | `GET api/gear`                                              | Get all gear items                                               |
| `Gear`              | `GET api/gear/{id}`                                         | Get one gear by id                                               |
| `Gear`              | `POST api/gears`                                            | Create gear item                                                 |
| `Gear`              | `PUT api/gears/{id}`                                        | Update gear item by id                                           |
| `Gear`              | `PUT api/gears/{id}`                                        | Deactivate gear item by id                                       |
| `List<Gear>`        | `GET api/gears/categories`                                  | Get all category of gear                                         |
| `List<User>`        | `GET api/users`                                             | Get all users                                                    |
| `User`              | `POST api/users`                                            | Create user                                                      |
| `User`              | `PUT api/users/{id}`                                        | Update a user by id                                              |
| `Gear`              | `POST api/users/{id}/gears`                                 | Create beverage for user by id                                   |
| `Gear`              | `PUT api/users/{userId}/gears/{gearId}`                     | Deactivate gear item by user id and gear item id                 |
| `List<Reservation>` | `GET api/user/{id}/reservations`                            | Get all reservations by user id                                  |
| `Reservation`       | `GET api/users/{userId}/reservation/{resId}`                | Get a reservation by user id                                     |
| `Reservation`       | `POST api/users/{userId}/reservation/{resId}`               | Create a reservation by user id and res id                       |
| `Reservation`       | `PUT api/users/{userId}/reservation/{resId}`                | Update a reservation by user id                                  |
| `Reservation`       | `PUT api/users/{userId}/reservation/{resId}`                | Deactivate a reservation by user id and res id                   |
| `List<Review>`      | `GET api/user/{id}/reviews`                                 | Get all reviews by user id                                       |
| `Review`            | `GET api/users/{userId}/review/{reviewId}`                  | Get a review by user id and review id                            |
| `Review`            | `POST api/user/{userId}/review{resId}/review`               | Create a review for user using id from user and review           |
| `Review`            | `POST api/user/{userId}/review{resId}/gear/{gearId}/review` | Create a review for gear using id from user, review, and gear    |
| `Review`            | `POST api/user/{userId}/review{resId}/review`               | Create a review using user and review id                         |
| `Review`            | `PUT api/user/{userId}/review{resId}/review`                | Deactivate a review of user using id from user and review        |
| `Review`            | `PUT api/user/{userId}/review{resId}/gear/{gearId}/review`  | Deactivate a review of gear using id from user, review, and gear |
