# TripPlanner

A journey-planning console application written in Java for the Advanced OOP Course.

### API Documentation:

This application is used to manage and organize information needed when traveling.
It has the next operations implemented:

**Objectives** are places you plan on seeing on this trip, they can be anything from landmarks and restaurants to bars or abandoned buildings.

- add objective
- list objectives
- mark objective as seen 
- show details for objective

**Events** are things that will happen while you are in your trip, for example art galleries or concerts. They can be either indoor events or outdoor events, with different fields for each.
- add event
- show events

**Travel Methods** are another thing you have to keep in check when travelling.
There are two traveling methods which inherit a base class, those are short-distance means of travel, for intra-city moving, and long-distance ones,
for example taking an airplane to reach a different country.
- add travel method
- show travel methods
- remove travel method

**Reminders** are there so you don't forget anything you were supposed to do when traveling.
These can be associated with an objective (for example getting tickets online before visiting an art gallery),
or they can exist by themselves.
- add reminder
- show reminders
- mark reminder as done
- remove reminder

**Accommodations** are the places you will sleep into. They can be whatever you want from hotels, motels or even your trusty tent if that's what you like.
- add accommodation
- list accommodations
- remove accommodation

### Technical details:
**Data permanence** is achieved using JDBC, which connects to a local running MySql instance. There are services which use CSV files instead (for some of the classes), but they are commented out as legacy (from stage 2 of the project).

Inheritance is used for the Event and TravelMethod classes, which serve as bases for two classes each.
Inheritance is also used to define a base functionality, which is common for every data access service. The base classes for those 
are the **CSVgeneric** and **JDBC generic** classes.

There is a MainService class, which exposes services to the Main class.