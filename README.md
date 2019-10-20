# Instructions and General Information

## Instructions


The project is based on a SpringBoot Rest App Small Plugable Microservices to simulate the discounts which uses the following technologies:

* Java 1.8
* MicroServices Strategy with Layered  Architecture
* Maven For Dependencies and build the project
* UsedPostMapping to Pass DiscountUserDetailDto Object
* Swagger Endpoint Url For Testing
* http://localhost:3000/swagger-ui.html#!/xebia45discount45controller/totalAmmountToBePaidAfterDiscountUsingPOST
* JUnit for Unit Tests Test Coverage 100 Percent 
* STS to run and test the program



NOTE:For More Info please check the below Location attached required Screen to Understand the Project Structure And Unit Test Case Covering.

/xebiaEmpDiscount/src/main/resources/ProjectInfoScreen
 
 
 


 In Controller  we have used PostMapping so please passed the Require JSON Information to Test The App
 
 
 Sample InputRequest :
 {
  "listOfProduct": [
    {
      "name": "ProductNme",
      "type": "GROCERY",
      "unitPrice": 0
    },
    {
      "name": "ProductNme",
      "type": "OTHER",
      "unitPrice": 0
    }
  ],
  "numberOfGroceryItem": 0,
  "numberOfOtherItem": 0,
  "user": {
    "joiningDate": "2019-10-20T08:35:31.709Z",
    "type": "EMPLOYEE",
    "userName": "string"
  }
}


Since the program is created in STS it is recommended to also run and test it by importing it into STS though other IDE's can also be used. You can run the program by executing xebia.Main.java file in Eclipse

Furthermore, unit tess are provided that can run by executing com.xebia.test.TestDiscounts and com.xebia.test.TestWithoutDiscounts. To get the complete test coverage just run JUnit Tests in IDE.

Comments are provided in the code and test cases for briefly describe the functionality and approach.

In case of any errors make sure that the jars under the lib folder is included in the buld path.


## General Information regarding the solution

- **ItemService** represent goods/products that can be placed in the cart.

- A **CartService** contains a number of items, and can compute the total price of its contents.

- Cart items can either be actual **products** with a fixed unit price, or products with a **pricing policy** attached for any dicounts that are calculated based on items and not the overall discount policy of current cart.

- **DiscountPolicyService** is used to calculate or apply different discounts based on overall cart value of contents.

