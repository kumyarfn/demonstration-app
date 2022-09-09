# Demonstration App

This is an unfinished demonstration app by Kamyar Farzanenia. The Purpose of this is to show the code, the cleanness, the design and the queries that's used. This app uses mongodb as its database and wherever there was a need for indexing it was done.

### Users And Roles
This app has SUPER ADMIN, ADMIN, PRODUCT MANAGER, PRODUCT PROVIDER and USER as its defined roles. As guessed SUPER ADMIN can do anything in this app and ADMINs have to be registered by SUPER ADMIN and  PRODUCT MANAGER & PRODUCT PROVIDER have to be registered by an ADMIN.

### Products

Products can only be added and updated by PPs, PMs and ADMINs. For products there's an advanced search (dynamic query) so page rendering for frontend would be easy. Each product has a providerId and availability of the product and whether users can vote and comment or not and whether only those who has bought the product can vote and comment.

There is also averageVote (of those that have been approved), datails among other fields.

For the advanced search parameters are: providerId, minPrice, maxPrice, isAvailable (these four can be null), sort (the field that you wanna sort by), sortDirection (whether ASC or DESC), pageNumber and PageSize (for pagination).

### Review
As you can imagine when the products are rendered in the frontend, now when each product is clicked on the product's page will show the votes and comments by the Review services.

The review rest APIs enables user to vote (first checks if the user can vote) and see the product's vote count and whether that user can vote for that product or not. Each time a vote is casted and approved (it happens later on, because votes and comments are not approved at first) the product's average vote get calculated again.

Same goes for commenting, users can comment (first checks if the user can comment) and see the product's comment count and whether that user can comment on that product or not.

Votes and Comments can only be approved by PM, ADMIN.

#### SwaggerUI
This project also has swagger ui at:

http://127.0.0.1/api/swagger-ui.html

## More Details

On each method in the service package there's more description and details.

## Project status
As of 9/10/2002 this project is going to stop.


## License
[Kamyar Farzanenia](mailto:komyar.fn@gmail.com)