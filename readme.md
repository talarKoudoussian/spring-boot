# Spring Boot application

## Import Database

1. Log in to the database as root or another user with sufficient privileges to create new databases.

    ```bash
    mysql -u root -p
    ```

1. Create a new database called `springbootapp`

    ```bash
    CREATE DATABASE springbootapp;
    ```

1. * Run the script located in `src/db/springbootapp_db_script.sql`

   OR

   * Through cmd, enter the following

    ```bash
       mysql -u username -p springbootapp < /path/to/springbootapp_db_script.sql
    ```
