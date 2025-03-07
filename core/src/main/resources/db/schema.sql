drop table if exists phone2color;
drop table if exists colors;
drop table if exists stocks;
drop table if exists phones;
drop table if exists order_items;
drop table if exists orders;

create table colors (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50),
  UNIQUE (code)
);

create table phones (
  id BIGINT AUTO_INCREMENT primary key,
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(254) NOT NULL,
  price FLOAT,
  displaySizeInches FLOAT,
  weightGr SMALLINT,
  lengthMm FLOAT,
  widthMm FLOAT,
  heightMm FLOAT,
  announced DATETIME,
  deviceType VARCHAR(50),
  os VARCHAR(100),
  displayResolution VARCHAR(50),
  pixelDensity SMALLINT,
  displayTechnology VARCHAR(50),
  backCameraMegapixels FLOAT,
  frontCameraMegapixels FLOAT,
  ramGb FLOAT,
  internalStorageGb FLOAT,
  batteryCapacityMah SMALLINT,
  talkTimeHours FLOAT,
  standByTimeHours FLOAT,
  bluetooth VARCHAR(50),
  positioning VARCHAR(100),
  imageUrl VARCHAR(254),
  description VARCHAR(4096),
  CONSTRAINT UC_phone UNIQUE (brand, model)
);

create table phone2color (
  phoneId BIGINT,
  colorId BIGINT,
  CONSTRAINT FK_phone2color_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_phone2color_colorId FOREIGN KEY (colorId) REFERENCES colors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table stocks (
  phoneId BIGINT NOT NULL,
  stock SMALLINT NOT NULL,
  reserved SMALLINT NOT NULL,
  UNIQUE (phoneId),
  CONSTRAINT FK_stocks_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table orders (
    orderId BIGINT AUTO_INCREMENT primary key,
    secureId UUID NOT NULL,
    subTotal FLOAT
        CONSTRAINT CHK_orders_subTotal
            CHECK (subTotal > 0),
    deliveryPrice FLOAT
        CONSTRAINT CHK_orders_deliveryPrice
            CHECK (deliveryPrice > 0),
    totalPrice FLOAT
        CONSTRAINT CHK_orders_totalPrice
            CHECK (totalPrice > 0),
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    deliveryAddress VARCHAR(200),
    contactPhoneNo VARCHAR(20),
    additionalInfo VARCHAR(4096),
    status VARCHAR(20),
    dateTime TIMESTAMP NOT NULL
);

create table order_items (
    orderItemId BIGINT AUTO_INCREMENT PRIMARY KEY,
    phoneId BIGINT
        CONSTRAINT FK_order_items_phoneId
            REFERENCES phones(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    orderId BIGINT
        CONSTRAINT FK_order_items_orderId
            REFERENCES orders(orderId)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    quantity BIGINT
        CONSTRAINT CHK_order_items_quantity
            CHECK (quantity > 0)
);
