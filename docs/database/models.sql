CREATE TABLE "pedidos" (
  "id" uuid PRIMARY KEY,
  "total_amount" double NOT NULL,
  "customer_id" uuid,
  "isPayed" bool,
  "status" enum,
  "created_at" timestamp,
  "updated_at" timestamp,
  "created_by" uuid,
  "updated_by" uuid
);

CREATE TABLE "status" (
  "status" enum NOT NULL
);

CREATE TABLE "produtos" (
  "id" uuid PRIMARY KEY,
  "category" enum,
  "price" double,
  "name" varchar,
  "description" varchar,
  "isActive" bool,
  "time_to_prepare" integer,
  "created_at" timestamp,
  "updated_at" timestamp,
  "created_by" uuid,
  "updated_by" uuid
);

CREATE TABLE "categories" (
  "category" enum
);

CREATE TABLE "combos" (
  "id" uuid PRIMARY KEY,
  "pedido_id" uuid,
  "produto_id" uuid,
  "created_at" timestamp,
  "updated_at" timestamp,
  "created_by" uuid,
  "updated_by" uuid
);

CREATE TABLE "clientes" (
  "id" uuid PRIMARY KEY,
  "first_name" varchar,
  "last_name" varchar NOT NULL,
  "cpf" varchar UNIQUE,
  "email" varchar NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp,
  "created_by" uuid,
  "updated_by" uuid
);

ALTER TABLE "combos" ADD FOREIGN KEY ("pedido_id") REFERENCES "pedidos" ("id");

ALTER TABLE "combos" ADD FOREIGN KEY ("produto_id") REFERENCES "produtos" ("id");

ALTER TABLE "pedidos" ADD FOREIGN KEY ("customer_id") REFERENCES "clientes" ("id");

ALTER TABLE "pedidos" ADD FOREIGN KEY ("status") REFERENCES "status" ("status");

ALTER TABLE "produtos" ADD FOREIGN KEY ("category") REFERENCES "categories" ("category");
