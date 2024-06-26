# Efficient_Task_Management_System

## Overview

This project is a task management system implemented in Java, featuring two main components:
1. `TaskQueue` using linked lists
2. `TaskHeap` using arrays

Tasks are managed based on priority and insertion order without loops or recursion, ensuring efficient task allocation and retrieval.

## Classes

### TaskQueue
A queue class implemented using a linked list structure. Tasks are added to the back of the queue and removed from the front.

### Task
Represents a task with a priority and a name. Implements a compareTo method to compare tasks based on priority and lexicographical order of names.

### TaskHeap
A heap implementation using an array. Tasks are ordered according to their priority. Includes methods for insertion, extraction of the maximum priority task, and removal of tasks at specified indices.

### TaskAllocation
Manages tasks using both a heap for priority management and a queue for order management. Tasks can be added and allocated based on priority or order of creation.

### TaskElement
A wrapper class for the Task class. Acts as a node in a doubly-linked list and as an element in a heap. Contains references to the next and previous elements in the linked list, a heap index, and a Task object.


## Usage

1. Setup:
   - Ensure Java Development Kit (JDK) is installed on your system.
   - Compile all .java files in the project directory.

2. Execution:
   - Execute the TaskHeap class to verify heap operations.
   - Execute the TaskAllocation class to verify task allocation operations.
