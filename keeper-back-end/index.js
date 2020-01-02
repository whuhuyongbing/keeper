
const express = require("express");
const app = express();
const bodyParser = require("body-parser");
const request = require("request");
const qs = require("querystring");
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));

app.post("/register", function(req, res) {
  request.post({url: "http://localhost:8080/register", form:{
    username: req.body.username,
    email: req.body.email,
    password: req.body.password
  }}, function(err, httpResponse, body){
    const data = JSON.parse(body);
    res.send(data);
  });
});

app.post("/login", function(req, res) {
    request.post({url:"http://localhost:8080/login", form:{
      grant_type: "password",
      client_id: "client",
      client_secret: "secret",
      email: req.body.userEmail,
      password: req.body.password,

    }}, function(err,httpResponse,body) {
      const data = JSON.parse(body);
      if (data.errorCode === 0) {
        request.post({url:"http://localhost:8080/oauth/token", form:{
          grant_type: "password",
          client_id: "client",
          client_secret: "secret",
          username: req.body.userEmail,
          password: req.body.password,
        }}, function(err2, httpResponse2, body2){
          const data2 = JSON.parse(body2);
          res.send({error: 0, access_token: data2.access_token, fresh_token: data2.refresh_token, message: "success"});
        });
      } else if(data.errorCode ===1) {
        res.send({error:1, message: "password incorrect"});
      }// password incorrect;
      else{
        res.send({error:2, message: "user doesn't exist"});
      } //user doesn't exits;
    });
});

app.post("/content/list", function(req, res){
    request.get('http://localhost:8000/content/list', {
  'auth': {
    'bearer': req.body.access_token,
  }
}, function(err, httpResponse, body){
    const data = JSON.parse(body);
    res.send(data);
});
});

app.post("/content/save", function(req, res) {
    request.post('http://localhost:8000/content/save',
    {form: {
      title: req.body.title,
      content: req.body.content
    },
    'auth': {'bearer': req.body.access_token}},
    function(err, httpResponse, body){
      const data = JSON.parse(body)
      res.send(data);
    });
});
app.post("/content/delete", function(req, res) {
    request.post('http://localhost:8000/content/delete',
    {form: {
      title: req.body.title,
      content: req.body.content
    },
    'auth': {'bearer': req.body.access_token}},
    function(err, httpResponse, body){
      const data = JSON.parse(body)
      res.send(data);
    });
});

app.listen(3000, function() {
  console.log("server started at port 3000");
});
