FROM denoland/deno:2.3.1 AS builder
WORKDIR /app
COPY deno.json deno.lock ./
COPY . .
RUN deno cache main.ts

FROM denoland/deno:2.3.1
WORKDIR /app
COPY --from=builder /app /app
EXPOSE 8002
CMD ["deno", "run", "--allow-net", "--allow-env", "--allow-read", "--allow-sys","main.ts"]