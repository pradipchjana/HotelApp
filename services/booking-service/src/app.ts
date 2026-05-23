import { Hono } from "hono";
import { logger } from "hono/logger";
import { cors } from "hono/cors";
import { addBooking, getReceipt, listBookings } from "./handlers/bookings.ts";
import { MongoStorage } from "./db/mongo.ts";
import { decode } from "@gz/jwt";
import { Redis } from "@db/redis";

type Variables = {
  db: MongoStorage;
  username: string;
  redis: Redis;
};

const JWTSECRET = Deno.env.get("JWT_SECRET") as string ||
  "ourhotelappmadebykhasimjanadinesh";

export const createApp = (db: MongoStorage, redis: Redis) => {
  const app = new Hono<{ Variables: Variables }>();

  app.use(logger());
  app.use(
    "*",
    cors({
      origin: ["http://localhost:3000"],
      credentials: true,
    }),
  );

  app.use("*", async (c, next) => {
    try {
      const authHeader = c.req.header("Authorization");
      if (!authHeader) {
        return c.json({ success: false, message: "Not logged in" }, 401);
      }

      const token = authHeader.replace("Bearer ", "");

      const userInfo = await decode(token, JWTSECRET, { algorithm: "HS256" });
      c.set("username", userInfo.sub as string);
      await next();
    } catch{
      return c.json({ success: false, message: "Invalid token" }, 401);
    }
  });

  app.use(async (c, next) => {
    c.set("db", db);
    c.set("redis", redis);
    await next();
  });

  app.get("/api/bookings", listBookings);
  app.post("/api/bookings", addBooking);
  app.get("/api/bookings/:id/receipt.pdf", getReceipt)

  return app;
};
