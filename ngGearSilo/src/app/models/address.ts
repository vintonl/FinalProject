import { User } from './user';

// Java Entity:
// private int id;
// private User user;
// private String address;
// private String address2;
// private String city;
// private String state;
// private Integer postalCode;
// private String country;

export class Address {
  id: number;
  user: User;
  address: string;
  address2: string;
  city: string;
  state: string;
  postalCode: number;
  country: string;

  constructor(
    id?: number,
    user?: User,
    address?: string,
    address2?: string,
    city?: string,
    state?: string,
    postalCode?: number,
    country?: string
  ) {
    this.id = id;
    this.user = user;
    this.address = address;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
  }
}
