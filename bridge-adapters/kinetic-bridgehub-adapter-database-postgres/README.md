# kinetic-bridgehub-adapter-database-postgres
An extension of the Generic JDBC Bridge that has been customized to simplify the deployment and implement Postgres specific features

## Configuration Values
| Name                    | Description |
| :---------------------- | :------------------------- |
| Username                | The username that will be used to access the Kinetic Core information |
| Password                | The password that is associated with the username |
| Server                  | The domain or IP address |
| Port                    | The port number |
| Database Name           | Name of the database to connect to |

## Example Configuration
| Name | Value |
| :---- | :--- |
| Username | SA |
| Password | Password1 |
| Server | 127.0.0.1 |
| Port | 1433 |
| Database Name | TestDB |

## Supported Structure
The structure will match a table in the configured database. ex: Inventory

## Fields
Fields will be converted to column names in the query to the sql database.  If no fields are provided the "*" will be inserted into the query.

## Query
The query will be converted to the condition for the where clause.  A query is required for the search and retrieve methods.
