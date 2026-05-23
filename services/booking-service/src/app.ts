import { Hono } from "hono";
import { logger } from "hono/logger";
import { cors } from "hono/cors";
import { addBooking, listBookings } from "./handlers/bookings.ts";
import { MongoStorage } from "./db/mongo.ts";
import { decode } from "@gz/jwt";

type Variables = {
  db: unknown;
  username: string;
};

const JWTSECRET = Deno.env.get("JWTSECRET") as string;

export const createApp = (db: MongoStorage) => {
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
    } catch {
      return c.json({ success: false, message: "Invalid token" }, 401);
    }
  });

  app.use(async (c, next) => {
    c.set("db", db);
    await next();
  });

  app.get("/api/bookings", listBookings);
  app.post("/api/bookings", addBooking);

  return app;
};
