import { createApp } from "./src/app.ts";
import { createDB } from "./src/db/initialise.ts";
import { MongoStorage } from "./src/db/mongo.ts";

const main = async () => {
  const db = await createDB();
  const storage = new MongoStorage(db);

  const app = createApp(storage);

  Deno.serve({ port: 8002 }, app.fetch);
};

main();
