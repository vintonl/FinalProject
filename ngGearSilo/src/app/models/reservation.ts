// private int id;
// private Date openDate;
// private Date closeDate;

export class Reservation {
  id: number;
  openDate: Date;
  closeDate: Date;

  constructor(
    id?: number,
    openDate?: Date,
    closeDate?: Date,
  ) {
    this.id = id;
    this.openDate = openDate;
    this.closeDate = closeDate;
  }
}
