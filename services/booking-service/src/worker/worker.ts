import { initMongoDb } from "../db/initialize.ts";
import { MongoStorage } from "../db/mongo.ts";
import { generatePDFFile } from "../handlers/try.js";
import { createRedis } from "../redis.ts";

const startWorker = async () => {
  const redis = await createRedis();
  const db = await initMongoDb();
  const storage = new MongoStorage(db);
  const serviceServer = Deno.env.get("SEARCH_SERVICE") || "localhost";

  while (true) {
    const result = await redis.brpop(0, "UPDATE_ROOM");
    const job = JSON.parse(result[1]);
    const { booking_id, hotel_id, rooms } = job;

    const response = await fetch(
      `http://${serviceServer}:8001/api/update/${hotel_id}?rooms=${rooms}`,
      { method: "PATCH" },
    )
      .then((x) => x.json());

    if (response.success) {
      const pdfPath = await generatePDFFile(booking_id);
      await storage.updateBookingStatus(booking_id, pdfPath, "completed");
      continue;
    }

    await storage.updateBookingStatus(booking_id, "", "cancelled");
  }
};

startWorker();
