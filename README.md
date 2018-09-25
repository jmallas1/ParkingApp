# ParkingApp
## Unit 2 challenge: ParkingApp

* You are going to design and program a software application for automating the parking and exit functions at a commercial parking garage. Name this project: ParkingApp.

* Most people are familiar with commercial parking facilities, but for those who aren’t, here’s how it works:

* Many, but not all commercial parking garages, use two automated teller machines – one for checkin and one for checkout.  The process begins when you drive your vehicle up to the automated checkin machine -- it issues a ticket which indicates your checkin time and date. This ticket is kept in the vehicle until you checkout.

* When you are ready to leave the garage you drive your vehicle to the automated checkout machine and insert your ticket into the machine. The machine then reads the checkin time and date and based on this calculates (using a special formula) how long you parked your vehicle in the garage and how much you owe. These values are displayed on an electronic display on the automated checkout machine. You then must pay this fee by cash or credit card, which you insert in the machine. At that point a bill is provided and the system will assume you paid before you are allowed to exit the garage.

* Those are the basics of how it works. Remember, this is an automated system, so there are no clerks or other people at the machines.

* The fee charged for parked vehicles is based on a $5.00 minimum fee to park for up to three hours. After that there is an additional $1.00 per hour charge for each hour or part of an hour parked. The maximum charge for any given 24-hour period is $15.00. Assume that no vehicle parks for longer than 24 hours.  Lost tickets have a $25.00 fee.

* The application needs to store all of the tickets in a file.  When the application is opened, that file needs to load all of the tickets in a collection.  

#### Input

* For input, create a class that emulates a machine that has one button, Check/In.
```
 Best Value Parking Garage

 =========================

 1 – Check/In

 3 – Close Garage

 =>_
```
 
* And a second machine that you submit your ticket to.
```
 Best Value Parking Garage

 =========================

 1 – Check/Out

 2 – Lost Ticket

 =>_
```

* For the Check/In, the start time should be created by a class that randomly creates a time between 7am and noon.  This class should easily be replaced with a class that would access the real time.  (But that would be harder to test)

* The second Machine should produce the bill a check-out time between 1pm and 11pm should randomly be created.

* Once the ticket is submitted, a bill is produced.
```
Best Value Parking Garage

 =========================

 Receipt for a vehicle id 105

 

 4 hours parked  11am – 3pm

 $6.00
```
 
```
 Best Value Parking Garage

 =========================

 Receipt for a vehicle id 107

 

 Lost Ticket

 $25.00
```
* Once “Close Garage” is selected, a summary of activity to date is produced.

```
Best Value Parking Garage

 =========================

 Activity to Date

 

 $120 was collected from 13 Check Ins

 $50 was collected from 2 Lost Tickets

 

 $230 was collected overall
```
 

