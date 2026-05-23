import { Db } from "mongodb";

export class MongoStorage {
  #bookings;

  constructor(db: Db) {
    this.#bookings = db.collection("bookings");
  }

  async getBookings(username: string) {
    return await this.#bookings.find({ username }).toArray();
  }

  async addBooking(username: string, hotel_id: string, rooms: number) {
    await this.#bookings.insertOne({ username, hotel_id, rooms });
  }
}
