import { createApp } from "./src/app.ts";
import { initMongoDb } from "./src/db/initialize.ts";
import { MongoStorage } from "./src/db/mongo.ts";
import { createRedis } from "./src/redis.ts";

const main = async () => {
  const db = await initMongoDb();
  const storage = new MongoStorage(db);
  const redis = await createRedis();

  const app = createApp(storage, redis);

  const PORT = Deno.env.get("PORT") || 8002;
  Deno.serve({ port: PORT as number }, app.fetch);
};

main();
