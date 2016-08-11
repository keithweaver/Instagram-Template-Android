
### Log In Endpoints

#### Sign Up
`POST`
`/endpoints/login/signup`

Parameters:
`apikey` - The API Key you've selected
`email` - The users email
`password` - The users password in plain English

Responses:
Success Response
```
{
	"success":1,
	"message":"Account added."
}
```

View common responses for other potential responses depending on parameters.


#### Common Responses:
```
{
	"success" : 0,
	"message" : "Error: Invalid API Key."
}
```

```
{
	"success" : 0,
	"message" : "Error: Email cannot be less than 3 characters."
}
```

```
{
	"success" : 0,
	"message" : "Error: Password cannot be less than 3 characters."
}
```
