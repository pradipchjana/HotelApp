import { createApp } from "./src/app.ts";
import { createDB } from "./src/db/initialise.ts";
import { MongoStorage } from "./src/db/mongo.ts";

const main = async () => {
  const db = await createDB();
  const storage = new MongoStorage(db);

  const app = createApp(storage);

  const PORT = Deno.env.get("PORT") || 8002;
  Deno.serve({ port: PORT as number }, app.fetch);
};

main();
