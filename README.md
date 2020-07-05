# Warehouse Inventory System

### Introduction

This is a warehouse inventory management system which is based on ***SpringBoot, MyBatis, Thymeleaf, MySql, etc***.

The program has the follow structures in the src/main/java package:

![aaa](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705202145758.png)

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

![image-20200705202658067](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705202658067.png)

After that, you also need to execute the [wis.sql](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/wis.sql) file to create needed tables and data. If you request the application without executing the sql file, it will go wrong.

After that, you can use any Java IDE (recommand IntelliJ IDEA) to open the whole project. It will need several time to download the dependencies. When everything is ready, you can simply right click the ***WisApplication*** file to run it.

![image-20200705202242752](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705202242752.png)

When you see this as the below picture shown, it means the project is ok for receiving ang request from clients.

![image-20200705202050837](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705202050837.png)

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

![image-20200705203916539](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705203916539.png)

This is the page for getting all the inventories:

![image-20200705203955363](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705203955363.png)

This is the page for performing transfermation:

![image-20200705204036210](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705204036210.png)

You can upload csv files by choosing the csv file and clicking the upload button:

![image-20200705204357525](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705204357525.png)

This is the example for product csv file:

![image-20200705234239630](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705234239630.png)

And this is the example for quanties csv file:

![image-20200705234328537](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705234328537.png)

You should follow the structure of these csv file when you uploading csv file to the application.



Or you can just simply use these two csv files for testing the functionality of uploading.

![wxupload](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/WX20200706-024341.png)



### Test Guide

In the idea IDE, you can just right click the ***src/test/java*** and choose "Run All Tests".



### Short Daily

#### Day One:

1. System design
   - Because it is a lightweight system, the business logics are not complex. Considering for that, I used SpringBoot for the platform, MySql for the database, MyBatis for persistence framework
2. Database design
   - I designed 3 tables in the Warehouse Inventory System's database. The first one is "tb_product" for Product model, the second one is "tb_warehouse" for Warehouse model, and the last one is "tb_warehouse_inventory" for WarehouseInventory model.
3. Wrote short daily

#### Day Two:

1. Designed Restful APIs
2. Wrote business logis for controller, service, dao, model, utils, etc
3. Wrote unit tests
4. Fixed bugs found in testing



#### Day Three:

1. Continued on developing front end page
2. Wrote documentations for README.md and short daily



#### What I learned:

1. The usage of Thymeleaf for front end part
2. Frontend development. (HTML, CSS, JQuery)
3. How to ensure the transactional correctness in xml
4. Interface-based programming



#### What I tried:

Because my focus was on backend side, doing frontend development was not so familiar to me. I spent some time on learning Thymeleaf and developing front end page based on Thymeleaf engine. 



#### Problems I encountered:

1. The default level for idea language is 5 (JDK1.5), so I just modify the level to 8 in Project Structure.

![image-20200705232508139](https://github.com/ongiao/Warehouse-Inventory-System/blob/master/warehouse-inventory-system/src/main/resources/images/image-20200705232508139.png)

But every time I import a new maven dependency, it will automatically change back to 5. My solution was specifying the value of language level to 8 by adding this plugin in pom.xml to prevent reseting the automatic language level each time.

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```





