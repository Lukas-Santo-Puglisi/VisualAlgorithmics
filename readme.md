# README: Convex Hull Visualization Project

## Introduction
This project is the result of a programming practicum at the Faculty of Mathematics and Computer Science, aimed at gaining experience in object oriented programming, developing a GUI, and solving complex geometric problems with Java and.  These tasks were addressed by implementing algorithms from an informal description provided by my university professor who invented the algorithms.

### Implemented Algorithms and Their Complexities
I tackled challenges related to convex hulls, including calculating the hull itself, identifying its diameter, and finding the largest square and triangle that can fit within it.:

- **Convex Hull via the Contour Polygon Method:** This method starts with sorting points lexicographically and then creates a contour polygon that encompasses all points. By simplifying this contour, the convex hull is formed. This method, operates with a time complexity of \(O(n \log n)\) due to the sorting phase, with subsequent steps running in linear time, \(O(n)\).
  
- **Diameter Calculation with Rotating Calipers:** To find the polygon's diameter, I employed rotating calipers to efficiently identify the furthest pair of points along the hull, achieving a linear runtime complexity of \(O(n)\), a significant improvement over brute-force approaches.
  
- **Largest Inscribed Square and Triangle Determination:** These calculations were more intricate, requiring a nuanced understanding of geometric principles to iterate over convex hull points and deduce optimal shapes. Although the worst time complexity can be in \(O(n^3)\), the implementations aim for efficiency, leveraging the convex hull and diameter calculations as foundational steps.

### User Interface and Visualization
The document details the requirements and expectations for a software development project involving geometric calculations and visualizations, particularly focusing on convex hulls. This summary highlights the key aspects of the user interface and animation features that students are expected to implement as part of their project:

### User Interface 
- The software allows users to **interactively place, delete, and move points** on a graphical canvas by mouse clicks. Nearby clicks should suffice for deletion and moving operations.
- Input coordinates are of `int` type, meaning **screen coordinates from mouse clicks can be directly used** without conversion. However, the vertical orientation usually goes from top to bottom, which might require adjustments for left and right roles.
- The application **immediately computes and display the convex hull** for any set of points. This also applies to the moving points: the hull should update dynamically if changes affect it.
- The software includes functionality to **generate random inputs** of 10, 50, 100, 500, and 1000 points within the canvas area and add them to the current point set.
- **Reading and saving point sets** from files is required, with support for basic file operations like New, Open, Save, and Save As... The data format involves plain text with one point per line, separated by spaces (e.g., `123 456` for a point at coordinates (123, 456)).
- The **convex hull calculation algorithm** (the Contour Polygon Method) is implemented, though sorting can use Java's standard library functions. External libraries are not allowed except for the provided testing library, ProPraTester.

- A **user manual accessible via a Help menu** is included, detailing how to use the application.

### Animation Features
- I implemented a live animation that displays how the algorithm operates in finding the convex hull, the triangle and the square.

- **Zoom In and Zoom Out** functionalities are implemented to allow users to adjust their view of the canvas, especially useful for large point sets. This requires managing two coordinate systems: one for the screen and another for the data.
- The application might need to **render large datasets** effectively on the screen, which could involve adjusting the visualization scale.

In summary, the project focuses on creating an interactive and visually engaging application that allows users to manipulate point sets and visualize geometric calculations, including convex hulls, dynamically. I was expected to implement these features using Java, adhering to specified requirements for interactivity, data handling, and algorithm implementation, enhancing the learning experience through practical application of geometric algorithms and GUI programming.

### Project Technicalities
Developed exclusively in Java, this project adheres strictly to the use of the Java Standard Library and a provided testing library, avoiding external dependencies to ensure compatibility and maintain focus on algorithm efficiency. The project structure, documentation using Javadoc, and adherence to Java programming best practices reflect a comprehensive approach to software development.
