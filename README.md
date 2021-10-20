# vf-assignment

JAVA VERSİON : 11

POSTMAN CONTROLS
****************
To see the garage contents
	{GET METHOD} endpoint -> localhost:8082/api/garage/status 
******************************************************************************************************
To see issued tickets
	{GET METHOD} endpoint -> localhost:8082/api/tickets/status 
******************************************************************************************************
To add a vehicle to the parking lot
	{POST METHOD} endpoint -> localhost:8082/api/tickets/create 

		Submitted data must be in this format
		                              -> {
                                                   "reasonForApplication" : "park",
   						   "carPlaque" : "34-LO-2000",
   						   "carColor" : "White",
    						   "carType" : "Car"
						 }
******************************************************************************************************	
To exit from the parking lot, i.e. cancel the issued ticket
	{POST METHOD} endpoint -> localhost:8082/api/tickets/delete

		!Enter parameters with postman key and value
		!Endpoint should be like this (localhost:8082/api/tickets/delete?carPlaque=34-LO-2000)
******************************************************************************************************

NOTES

#Since a database was not desired, I used a hashMap<Integer, Vehicle> for the garage on inMemory and a List<Ticket> on inMemory to see the tickets that were cut.

#Why don't service classes have an abstract layer?
-The @Autowired annotation is no longer used in current spring boot projects. The reason is that when starting your Spring boot project, Singleton creates an instance for each structure, and for this reason, calls are processed directly over that singleton structure. Therefore, since each structure has a singleton instance at the back, the project can work as before with private final access specifiers.

#In order not to suffer from future objectivity weakness, I wrapped each of my concrete classes with the Entity interface that I created as abstract.

#I have overridden the equals, hashCode methods in all my concrete models so that the reference equality checks I have used will give a correct result.

#I used the dtos I had created in order not to open an entity directly to the outside. I did not create a dto with the contents of the garage just to show the tickets and garage status in inMemory, because it is requested in this way as per the scenario.

#Since there is no DAO layer in the scenario, I created and used the List<Ticket> and HashMap<Integer, Vehicle> data structures in inMemory within their own services.

#I used my DtoConverters over a single dto. The reason for this is that the incoming ticket information contains both Vehicle and Ticket information. That is, to be processed through a single input.

#I wrote all the logic of adding cars on GarageService. I did not add a comment line between them, but I specified the method names in a descriptive way.
