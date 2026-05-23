import { Db, ObjectId } from "mongodb";

export class MongoStorage {
  #bookings;

  constructor(db: Db) {
    this.#bookings = db.collection("bookings");
  }

  async getBookings(username: string) {
    return await this.#bookings.find({ username }).toArray();
  }

  async addBooking(username: string, hotel_id: string, rooms: number) {
    return await this.#bookings.insertOne({
      username,
      hotel_id,
      rooms,
      status: "pending",
    });
  }

  async updateBookingStatus(
    booking_id: string,
    pdfPath: string,
    status: string,
  ) {
    await this.#bookings.updateOne({ _id: new ObjectId(booking_id) }, {
      $set: { pdfPath: pdfPath ? pdfPath : null, status },
    });
  }
}
