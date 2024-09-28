# CV Generator

**CV Generator** is a Java-based web application built using Maven and JSP, designed to help users create and manage their CVs online. The application allows users to input their personal information, education, work experience, skills, and other relevant details, and then generates a professional-looking CV that can be previewed or downloaded as a PDF. The application is hosted using Apache Tomcat.

## Features

- **User Authentication:** Users can sign up, log in, and manage their accounts.
- **CV Creation:** Users can input and manage various sections of their CVs, including personal information, education, work experience, skills, and more.
- **PDF Generation:** The application generates a well-formatted PDF version of the user's CV, which can be downloaded or previewed online.
- **MVC Architecture:** The project is organized using the Model-View-Controller (MVC) design pattern for clean and maintainable code.

## Technologies Used

- **Java**: Core application logic.
- **Maven**: Build automation and dependency management.
- **JSP (Java Server Pages)**: For creating dynamic web pages.
- **Apache Tomcat**: Web server for hosting the application.
- **MySQL**: Database for storing user data and CV information.

## Installation

To run the CV Generator locally, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/devs-talha/cv-generator.git
   cd cv-generator
   ```

2. **Set up the database:**

    - Create a MySQL database named `web_project`.
    - Run the provided [SQL script](Database.sql) to create the necessary tables and insert initial data.

3. **Configure the application:**

    - Update the `db-host`, `db-port`, `db-name`, `db-user`, and `db-password` parameters in your `web.xml` file with your database connection details.

4. **Build the project using Maven:**

   ```bash
   mvn clean install
   ```

5. **Deploy to Tomcat:**

    - Deploy the generated WAR file (`target/cv-generator.war`) to your Tomcat server.

6. **Run the application:**

    - Start your Tomcat server and access the application via `http://localhost:8080/cv-generator`.

## Usage

- **Sign Up**: Create a new account to start building your CV.
- **Login**: Access your account to view, edit, or delete your CVs.
- **Create CV**: Fill in the details across various sections to generate your CV.
- **Preview/Download**: Preview your CV online or download it as a PDF file.


## Video Walkthrough

Here's a video walkthrough of the CV Generator project:

[![Watch the video](https://img.youtube.com/vi/UoJbfMjCcT4/default.jpg)](https://youtu.be/UoJbfMjCcT4)

Click the image above or [click here](https://youtu.be/UoJbfMjCcT4) to watch the video on YouTube.


This approach makes it easy for viewers to see the video directly from your repository's README.

## Sample CV

A [sample generated CV](sample-john-doe-cv.pdf) has been included in the repository. This file demonstrates the formatting, structure, and style used by the application.


## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
