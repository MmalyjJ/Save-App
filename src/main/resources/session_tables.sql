CREATE TABLE user_auth (
                            name VARCHAR(64) NOT NULL,
                            email LONGTEXT NOT NULL,
                            password LONGTEXT,
                            enabled TINYINT NOT NULL DEFAULT 1,
                            PRIMARY KEY (name)
);

CREATE TABLE user_authorities (
                             name Varchar(64) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (name) REFERENCES user_auth_(name)
);

CREATE UNIQUE INDEX ix_auth_name on user_authorities (name,authority);



CREATE TABLE user_save(
                           id INT NOT NULL AUTO_INCREMENT,
                           password LONGTEXT,
                           phone LONGTEXT,
                           email LONGTEXT,

                           token LONGTEXT,
                           day_created DATE,
                           CONSTRAINT user_pkey PRIMARY KEY (id)
#                            CONSTRAINT user_fkey_docs FOREIGN KEY (id) REFERENCES users_documents_save (id),
#                            CONSTRAINT user_fkey_profiles FOREIGN KEY (id) REFERENCES users_profiles_save (id),
#                            CONSTRAINT user_fkey_portfolio FOREIGN KEY (id) REFERENCES user_portfolio_save (id),
#                            CONSTRAINT user_fkey_wallets FOREIGN KEY (id) REFERENCES user_wallets_save (id)
);


CREATE TABLE IF NOT EXISTS users_documents_save (
#     id VARCHAR(128) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    passport_data VARCHAR(32),
    passport_issued VARCHAR (64),
    passport_main_page VARCHAR (8000),
    passport_registration_page VARCHAR (8000),
    is_accepted BOOLEAN,
    CONSTRAINT users_documents_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS users_profiles_save (
#     id VARCHAR(128) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(32),
    second_name VARCHAR(32),
    birth_date  VARCHAR(32),
    CONSTRAINT users_profiles_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS user_portfolio_save(
#     id VARCHAR(128) NOT NULL,
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   total_balance_now LONG,
                                                   total_balance_prev_day LONG,
                                                   actives  VARCHAR(8000),
                                                   trade_history  VARCHAR(8000),
                                                   CONSTRAINT user_portfolio_pkey PRIMARY KEY (id),
                                                   CONSTRAINT user_porfolio_assets_fkey FOREIGN KEY (id) REFERENCES assets(id)
);

CREATE TABLE assets(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) ,
    cost LONG,
    bought_date DATE,
    CONSTRAINT asset_pkey PRIMARY KEY (id)
);


CREATE TABLE user_wallets_save (
    id INT NOT NULL AUTO_INCREMENT,
    amount LONG,
    type VARCHAR(10),
    CONSTRAINT user_wallets_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS admins_save (
#     id VARCHAR(128) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    Password LONGTEXT,
    Phone LONGTEXT,
    Email LONGTEXT,
    Token LONGTEXT,
    day_created DATE,
    CONSTRAINT admins_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS news_save (
#     id VARCHAR(128) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    news_source VARCHAR(64),
    category VARCHAR(32),
    body VARCHAR(8000),
    news_date TIMESTAMP,
    CONSTRAINT news_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS payments_save (
#     id VARCHAR(128) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    amount BIGINT,
    day_created TIMESTAMP,

    user_from INT,
    user_to INT,

    CONSTRAINT payments_pkey PRIMARY KEY (id),
    CONSTRAINT user_from_fkey FOREIGN KEY (user_from) REFERENCES user_save (id),
    CONSTRAINT user_to_fkey FOREIGN KEY (user_to) REFERENCES user_save (id)
);



#MAPPING TABLES
CREATE TABLE user_user_wallets (
    user_id INT,
    user_wallet_id INT,
    CONSTRAINT user_user_wallets_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_save (id),
    CONSTRAINT user_user_wallets_user_wallets_id_fkey FOREIGN KEY (user_wallet_id) REFERENCES user_wallets_save (id)
);

CREATE TABLE wallet_payments (
    wallet_id INT,
    payment_id INT,
    CONSTRAINT wallet_payments_wallet_id_fkey FOREIGN KEY (wallet_id) REFERENCES user_wallets_save (id),
    CONSTRAINT wallet_payments_payment_id_fkey FOREIGN KEY (payment_id) REFERENCES payments_save (id)
);

CREATE TABLE portfolio_assets (
    portfolio_id INT,
    asset_id INT,
    CONSTRAINT portfolio_assets_portfolio_id_fkey FOREIGN KEY (portfolio_id) REFERENCES user_portfolio_save (id),
    CONSTRAINT portfolio_assets_asset_id_fkey FOREIGN KEY (asset_id) REFERENCES assets (id)
)