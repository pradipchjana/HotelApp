import { Db, MongoClient } from "mongodb";

export const createDB = async (): Promise<Db> => {
  const MONGO_URI = Deno.env.get("MONGODB_URI") || "mongodb://localhost:27017";
  const client = new MongoClient(MONGO_URI);
  console.log(MONGO_URI,client)

  await client.connect();

  console.log("Database connected");

  return client.db("test");
};
