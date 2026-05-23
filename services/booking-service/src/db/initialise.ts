import { Db, MongoClient } from "mongodb";

export const createDB = async (): Promise<Db> => {
  const MONGO_URI = Deno.env.get("MONGO_URI") || "mongodb://localhost:27017";

  const client = new MongoClient(MONGO_URI);

  await client.connect();

  console.log("Database connected");

  return client.db("test");
};
