CREATE TABLE "orders" (
  "id" uuid PRIMARY KEY,
  "total_amount" double NOT NULL,
  "customer_id" uuid,
  "isPayed" bool,
  "status" enum,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "products" (
  "id" uuid PRIMARY KEY,
  "category" enum,
  "price" double,
  "name" varchar,
  "description" varchar,
  "isActive" bool,
  "time_to_prepare" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "combos" (
  "id" uuid PRIMARY KEY,
  "order_id" uuid,
  "product_id" uuid,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "customers" (
  "id" uuid PRIMARY KEY,
  "first_name" varchar,
  "last_name" varchar NOT NULL,
  "cpf" varchar UNIQUE,
  "email" varchar NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

ALTER TABLE "combos" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "combos" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("customer_id") REFERENCES "customers" ("id");
