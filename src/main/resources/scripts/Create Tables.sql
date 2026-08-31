CREATE TABLE "transaction" (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	account_id varchar(100) NULL,
	"type" varchar(100) NULL,
	amount float8 NULL,
	currency varchar(50) NULL,
	description varchar(200) NULL,
	status varchar(100) NULL,
	provider_transaction_id varchar(100) NULL,
	balance_after float8 NULL,
	created_at varchar(50) NULL,
	code varchar(50) NULL,
	CONSTRAINT transaction_pkey PRIMARY KEY (id)
);