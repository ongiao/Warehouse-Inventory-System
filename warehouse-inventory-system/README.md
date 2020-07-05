# Warehouse Inventory System

### Introduction

This is a warehouse inventory management system which is based on ***SpringBoot, MyBatis, Thymeleaf, MySql, etc***.

The program has the follow structures in the src/main/java package:

![image-20200705202145758](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705202145758.png)

##### java/com/ongiao/wis:

 * ***constants***: Store some constant variables 
 * ***controller***: Java controllers which can receive and handle HTTP request from the clients
 * ***dao***: Work with MySql database, execute sqls.
 * ***pojo***: Entities (model class) such as Product (商品), Warehouse (庫房) and WarehouseInventory (庫存).  
 * ***service*** : Most of the business logics are here.  
 * ***utils*** : It includes some public utilities such as csv file readers, MybatisUtil and UploadHelper.  
 * ***resources*** : It includes properties file (application.properties, database.properties, log4j.properties), XML file (mybatis-config.xml) and some static file for front end side.

This project has unit tests in the ***src/test*** package.

### Run Guide

Before running anything in this project, don't forget to download ***MySql*** on you local computer. Besides, you need to modify ***database.properties*** to change the ***url, username, password*** to you own one.

![image-20200705202658067](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705202658067.png)

After that, you can use any Java IDE (recommand IntelliJ IDEA) to open the whole project. It will need several time to download the dependencies. When everything is ready, you can simply right click the ***WisApplication*** file to run it.

![image-20200705202242752](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705202242752.png)

When you see this as the below picture shown, it means the project is ok for receiving ang request from clients.

![image-20200705202050837](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705202050837.png)

The default port for this project is 8080, so you can test the apis through localhost:8080/



After running up the application, you can use browser or postman to test apis.

The project has these following apis:

##### IndexController:

```json
"/": Home page
```

##### WarehouseController:

```json
"/warehouses": Get all the warehouses in Hong Kong
```

##### ProductController:

```json
GET "/products": Get all the products
POST "/product": Add a specific product into the database given name, code and weight
POST "/products/csv": Upload csv file for the products
```

##### WarehouseInventoryController:

```json
GET "/warehouse/inventory/all": Get the inventory from all the warehouses based in Hong Kong
GET "/inventory/product": Get the inventory of the specific product given the product code
POST "/warehouse/inventory": Upload csv file for warehouse inventory
POST "/transfermation": Transfer inventory from one location to another location
```



This is the page for getting all the products:

![image-20200705203916539](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705203916539.png)

This is the page for getting all the inventories:

![image-20200705203955363](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705203955363.png)

This is the page for performing transfermation:

![image-20200705204036210](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705204036210.png)

You can upload csv files by choosing the csv file and clicking the upload button:

![image-20200705204357525](/Users/chenjunxing/Library/Application Support/typora-user-images/image-20200705204357525.png)

