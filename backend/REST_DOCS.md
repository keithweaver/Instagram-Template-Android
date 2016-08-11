
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


#### Log in
`POST`
`/endpoints/login/login`

Parameters:

`apikey` - The API Key you've selected

`email` - The users email

`password` - The users password in plain English

Responses:

Success Response
```
{
	"success":1,
	"message":"bbsmbdsfbgkmsiobfqjpplfeklaionmpomip"
}
```

Message is the users log in token. Stored this locally on the device, so when the users reopens the application, you can verify their identity without having them reenter their login information.


#### Update User name
`POST`
`/endpoints/login/update-username`

Parameters:

`apikey` - The API Key you've selected

`email` - The users email

`password` - The users password in plain English

`username` - The users new username

Responses:

Success Response
```
{
	"success":1,
	"message":"Success! User name updated."
}
```

Taken Response
```
{
	"success":0,
	"message":"Error: User name taken."
}
```


#### Register Device
`POST`
`/endpoints/login/register-device`

Parameters:

`apikey` - The API Key you've selected

`email` - The users email

`token` - The users locally stored log in token. (From the log in response)

`device` - Device id from GCM

Responses:

Success Response
```
{
	"success":1,
	"message":"Stored"
}
```

Error Responses
```
{
	"success":0,
	"message":"Error: Cannot find user info"
}
```
```
{
	"success":0,
	"message":"Error: Cannot find log in information"
}
```


#### Check Log In Token
`POST`
`/endpoints/login/check-login-token`

Parameters:

`apikey` - The API Key you've selected

`email` - The users email

`token` - The users locally stored log in token. (From the log in response)

Responses:

Success Response
```
{
	"success":1,
	"message":"Valid token"
}
```

Error Responses
```
{
	"success":0,
	"message":"Error: Invalid log in. Please log in again."
}
```
```
{
	"success":0,
	"message":"Error: Cannot find users."
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
	"message" : "Error: Email too short."
}
```

```
{
	"success" : 0,
	"message" : "Error: Password cannot be less than 3 characters."
}
```

```
{
	"success" : 0,
	"message" : "Error: User name cannot be blank."
}
```

```
{
	"success" : 0,
	"message" : "Error: User name cannot be less than 3 characters."
}
```

```
{
	"success" : 0,
	"message" : "Error: Token cannot be blank."
}
```

```
{
	"success" : 0,
	"message" : "Error: Device cannot be blank."
}
```