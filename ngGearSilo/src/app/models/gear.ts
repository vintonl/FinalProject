import { User } from './user';

//  Java Entity
// private int id;
// private User user;
// private String name;
// private String condition;
// private Double price;
// private String description;
// private String imageUrl;
// private String imageUrl2;
// private String imageUrl3;
// private Boolean available;
// private Boolean active;

export class Gear {
  id: number;
  user: User;
  name: string;
  gearCondition: string;
  price: number;
  description: number;
  imageUrl: string;
  imageUrl2: string;
  imageUrl3: string;
  available: boolean;
  active: boolean;

  constructor(
    id?: number,
    user?: User,
    name?: string,
    gearCondition?: string,
    price?: number,
    description?: number,
    imageUrl?: string,
    imageUrl2?: string,
    imageUrl3?: string,
    available?: boolean,
    active?: boolean

  ) {
    this.id = id;
    this.user = user;
    this.name = name;
    this.gearCondition = gearCondition;
    this.price = price;
    this.description = description;
    this.imageUrl = imageUrl;
    this.imageUrl2 = imageUrl2;
    this.imageUrl3 = imageUrl3;
    this.available = available;
    this.active = active;

  }
}
