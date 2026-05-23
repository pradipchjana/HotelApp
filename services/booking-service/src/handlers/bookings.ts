import { Context } from "hono";
import { MongoStorage } from "../db/mongo.ts";

export const listBookings = async (c: Context) => {
  const db: MongoStorage = c.get("db");
  const username: string = c.get("username");
  const bookings = await db.getBookings(username);

  return c.json(bookings);
};

export const addBooking = async (c: Context) => {
  const serviceServer = Deno.env.get("SEARCHSERVICE") || "localhost";
  
  const db: MongoStorage = c.get("db");
  const username: string = c.get("username");
  const details = await c.req.json();
  const { hotel_id, rooms } = details;

  return fetch(`http://${serviceServer}:8001/api/hotels/${hotel_id}`)
    .then((x) => x.json())
    .then(async (res) => {
      if (!res) return c.json({ status: false, message: "Hotel not found" });

      if (res.availableRooms >= rooms) {
        db.addBooking(username, hotel_id, rooms);

        return await fetch(
          `http://${serviceServer}:8001/api/update/${hotel_id}?rooms=${rooms}`,
          { method: "PATCH" },
        )
          .then((res) => res.json())
          .then((response) => {
            if (response) {
              return c.json({ status: true, message: "Booked" });
            }
            return c.json({ status: false, message: "Rooms not available" });
          });
      }

      return c.json({ status: false, message: "Rooms not available" });
    });
};
