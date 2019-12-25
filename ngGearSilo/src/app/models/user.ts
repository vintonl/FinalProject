import { Address } from './address';

// Java Entity
//  private int id;
// 	private String firstName;
// 	private String lastName;
// 	private String email;
// 	private String username;
// 	private String password;
// 	private Date createdAt;
// 	private Date updatedAt;
// 	private String role;
// 	private String imageUrl;
// 	private String about;
// 	private Address address;
// 	private String phone;
// 	private Boolean enabled;

export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  createdAt: Date;
  updatedAt: Date;
  role: string;
  imageUrl: string;
  about: string;
  address: Address;
  phone: string;
  enabled: string;

  constructor(
    id?: number,
    firstName?: string,
    lastName?: string,
    email?: string,
    username?: string,
    password?: string,
    createdAt?: Date,
    updatedAt?: Date,
    role?: string,
    imageUrl?: string,
    about?: string,
    phone?: string,
    enabled?: string,
    address?: Address
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.role = role;
    this.imageUrl = imageUrl;
    this.about = about;
    this.phone = phone;
    this.enabled = enabled;
    this.password = password;
    this.address = address;
  }


}
