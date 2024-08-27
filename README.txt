1. Please create an endpoint which will responsible to insert the transaction details 
like transaction_id, amount, currency, create_time, customer_name, device_id, status, cardnumber(how to encrypt that you need to know), expiry date
Before inserting the transaction, need to check if that transaction triggered from authenticate device or not.
Need to do a db call to get the details of customer. You should have all the entity class. 
Customer entity class should have CustomerName, Address, DeviceID, emailId fields
(You can use in-memory database)
2. Device detection (another microservice)– need to invoke another endpoint which will take customer details and check the device is present or not(assuming present) and return "Yes".
Need to invoke detection api from the current api and
if we are getting yes then insert the data or else redirect to error page(no need to implement error page. We can return only a message "error")
3. Fraud detection
We should have one endpoint in same api which will check if the same amount of transaction happening multiple time say like 50 times within 2hrs for a 
customer from other device then will block the card.
The application should be in running state. We can test it from rest api and you need to create some jUnin test method for testing the endpoint, and test some service method. (Application should have proper layers like controller service dao etc.)