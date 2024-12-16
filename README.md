**TakeMyPet** is a web application designed to connect pet owners who need temporary care for their pets with hosts willing to look after them. Both owners and hosts can register, create personal pages, and post relevant information (type of pet, duration of care, etc.). This information will be stored in a database and users can search for each other based on specific criteria. Pet owners can book care services and rate the quality of the service, contributing to the host's rating. Future users can then choose hosts based on these ratings.

_**Features**_

**Personal Pages**

•	Users (owners and hosts) can create personal pages with details such as:
  o	Name
  o	Date of Birth
  o	Address
  o	Contact Information
  o	Unique User ID

•	Hosts can also specify:
  o	Maximum number of pets they can care for simultaneously
  o	Their rating

•	Registration and login system, with personal information stored in the database.

**Host Posts**

•	Hosts can create posts about their availability to care for pets, including:
  o	Type of pet
  o	Maximum duration of care
  o	Price

•	Hosts can create up to one post per category of pet and change the status of the post (active/inactive).

**Host Search**

•	Pet owners can search for hosts based on specific criteria (type of pet, rating, etc.).

**Contract Management**

•	Contracts between owners and hosts are created when a booking is made, including:
  o	Host ID
  o	Owner ID
  o	Contract ID
  o	Type of pet
  o	Dates of care
  o	Contract status (active/completed/error)
  o	Quality rating (added after completion)
•	Contracts are only confirmed if the number of active contracts does not exceed the host's capacity.
•	Contracts automatically update to "completed" after the care period ends.
•	Option to report errors, which will notify the app administrators and change the contract status to "error".

**Rating and Review System**

•	Owners can rate the service after the contract ends.
•	Ratings are stored in the contract information and contribute to the host's overall rating.
•	The rating system weights reviews based on the owner's usage time, giving more experienced users' reviews greater impact.

_**Technology Stack**_

**Backend**

•	Spring Framework for building the Java application
•	Spring Web for the web application component
•	Spring Data for database interaction (MySql)

**Frontend**

•	HTML and Bootstrap for the user interface
