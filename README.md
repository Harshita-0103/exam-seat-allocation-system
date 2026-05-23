# Exam Seat Allocation and Invigilator Allocation System

## Project Overview

This project is developed to automate the examination seating arrangement and invigilator allocation process in colleges/universities.

The system helps admins manage:

- Student details
- Hall details
- Faculty details
- Exam schedules
- Seat allocation
- Invigilator allocation

It reduces manual work, avoids errors, and generates reports automatically.

---

## Features

### Admin Module
- Admin Login
- Add/Edit/Delete Students
- Add Halls
- Add Faculty
- Add Exams
- Automatic Seat Allocation
- Automatic Invigilator Allocation
- Generate Reports

### Student Module
- Student Login
- View Seat Allocation
- View Exam Details

### Faculty Module
- View Invigilation Duties
- Check Hall Details
- Duty Date Information

---

## Technologies Used

### Frontend
- HTML
- CSS
- JavaScript
- Thymeleaf

### Backend
- Java
- Spring Boot

### Database
- MySQL

### Tools
- IntelliJ IDEA
- GitHub

---

## Database Tables

- students
- halls
- faculty
- exams
- seat_allocation
- invigilator_allocation
- admin

---

## Automatic Seat Allocation Logic

- Fetch all students
- Fetch available halls
- Check hall capacity
- Assign seat numbers automatically
- Store allocation in database

---

## Automatic Invigilator Allocation Logic

- Fetch faculty list
- Fetch hall list
- Assign one invigilator per hall
- Maintain equal workload distribution

---

## Project Structure

```text
src/main/java
 ┣ controller
 ┣ service
 ┣ repository
 ┣ entity

src/main/resources
 ┣ templates
 ┣ static
