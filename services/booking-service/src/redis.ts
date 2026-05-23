import { connect } from "@db/redis";

export const createRedis = async () => {
  const hostname = Deno.env.get("REDIS_HOST") || "127.0.0.1";
  const redis = await connect({
    hostname,
    port: 6379,
  });

  console.log("connected to redis");
  return redis;
};
