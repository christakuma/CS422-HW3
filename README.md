# CS422-HW3

This is homework 3 for CptS422. The instructions were as follows:

'UserProject' is a straightforward CRUD application.

Create a call graph using mermaidjs
You may use mermaid-live editor. Or you can install mermaid intelliJ plugin.
Skip Java library/standard methods (example: list.add(), System.out.println, Map.values, etc)
Skip getter/setters and toString method (example: user.getFirstName)
Export the markdown call graph as a PDF or PNG
Write two pairwise integration test
Select the pairs from the call graph for 'createUser' or 'updateUser.'
Remember - Integration testing is about verifying calls and messages more than verifying the results on computations (but it doesn't hurt to do both)

For this assignment, I have chosen to do pairwise integration tests for (UserServiceImpl, UserValidator) for createUser, and (UserServiceImpl, UserDaoImpl) for updateUser.
