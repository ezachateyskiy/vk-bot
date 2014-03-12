<html>
<head>
    <title>Hello Facebook</title>
</head>
<body>
<h3>Connect to Facebook</h3>

<form action="/connect/facebook" method="POST">
    <input type="hidden" name="scope" value="publish_stream,offline_access,user_status">
<p>You haven't created any connections with Twitter yet. Click the button to create
    a connection between your account and your Twitter profile.
    (You'll be redirected to Twitter where you'll be asked to authorize the connection.)</p>
<p><button type="submit">Submit</button></p>
</form>
</body>
</html>