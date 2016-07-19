users
```
id
username
email
verified
encryptedPassword
hash
first
last
profileImg
bio
website
isPrivate
createdDateTime
```

loginTokens
```
id
userId
token
device
datetime_tab
```

posts
```
id
userId
caption
locationId
imgPath
isVideo
datetime_tab
```

locations
```
id
latitude
longitude
name
```

postLikes:
```
id
postId
userIdWhoLiked
datetime_tab
```

videoViews:
```
id
postId
userIdWhoViewed
datetime_tab
```

postComments:
```
id
postId
comment
byId
datetime_tab
```

following
```
id
userId
isFollowing
datetime_tab
```