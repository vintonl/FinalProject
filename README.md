## GearSilo

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
