import { Context } from "hono";
import { MongoStorage } from "../db/mongo.ts";
import { Redis } from "@db/redis";

export const listBookings = async (c: Context) => {
  const db: MongoStorage = c.get("db");
  const username: string = c.get("username");
  const bookings = await db.getBookings(username);

  return c.json(bookings);
};

export const addBooking = async (c: Context) => {
  const serviceServer = Deno.env.get("SEARCH_SERVICE") || "localhost";
  
  const db: MongoStorage = c.get("db");
  const redis: Redis = c.get("redis");
  const username: string = c.get("username");
  const details = await c.req.json();
  const { hotel_id, rooms } = details;

  const res = await fetch(`http://${serviceServer}:8001/api/hotels/${hotel_id}`)
    .then((x) => x.json());

  if (!res) return c.json({ status: false, message: "Hotel not found" });

  if (res.availableRooms >= rooms) {
    const booking = await db.addBooking(username, hotel_id, rooms);

    await redis.lpush(
      "UPDATE_ROOM",
      JSON.stringify({
        booking_id: booking.insertedId,
        hotel_id,
        rooms,
        username,
      }),
    );

    return c.json({
      status: true,
      message: "Hotel Booked. Receipt is pending",
    });
  }

  return c.json({ status: false, message: "Rooms not available" });
};
