import React, { Fragment } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import container from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import { Divider, Avatar, Grid, Paper } from "@material-ui/core";
import Appbar from "./Appbar";
import ReactDOM from "react-dom";
import User from "./User";
function Render() {
  return ReactDOM.render(<User />, document.getElementById("root"));
}
const useStyles = makeStyles((theme) => ({
  root: {
    minHeight: 300,
  },

  title: {
    fontSize: 30,
  },
  newPaper: {
    width: theme.spacing(87),
    padding: "40px 20px",
  },
  submit: {
    marginTop: theme.spacing(5),
    marginLeft: theme.spacing(80),
  },
  root1: {
    "& .MuiTextField-root": {
      margin: theme.spacing(1),
      width: "90ch",
    },
  },
  root2: {
    marginLeft: theme.spacing(70),
    margin: theme.spacing(1),
  },
}));

export default function OutlinedCard() {
  const classes = useStyles();

  return (
    <Fragment>
      <Card className={classes.root} variant="outlined">
        <Appbar />
        <CardContent>
          <Typography className={classes.title} color="inherit" gutterBottom>
            Ticket Description
          </Typography>
          <Typography variant="h6" color="textSecondary" gutterBottom>
            A help desk support system enables the companies to resolve customer
            issues efficiently by simply automating complaint resolution process
            with ticket management. The lines can sometimes be blurred between a
            help desk management system or IT service management
          </Typography>
        </CardContent>
      </Card>
      <div style={{ padding: 14 }}>
        <h1>Comments</h1>
        <Paper className={classes.newPaper}>
          <Grid container wrap="nowrap" spacing={2}>
            <Grid item>
              <Avatar alt="USER" />
            </Grid>
            <Grid justifyContent="left" item xs zeroMinWidth>
              <h3 style={{ margin: 0, textAlign: "left" }}>USER</h3>
              <p style={{ textAlign: "left" }}>
                This is a user generated comment!
              </p>
              <p style={{ textAlign: "left", color: "gray" }}>
                posted 1 minute ago
              </p>
            </Grid>
          </Grid>
          <Divider variant="fullWidth" style={{ margin: "30px 0" }} />
          <Grid container wrap="nowrap" spacing={2}>
            <Grid item>
              <Avatar alt="ADMIN" />
            </Grid>
            <Grid justifyContent="left" item xs zeroMinWidth>
              <h3 style={{ margin: 0, textAlign: "left" }}>ADMIN</h3>
              <p style={{ textAlign: "left" }}>
                This is an admin generated comment!
              </p>
              <p style={{ textAlign: "left", color: "gray" }}>
                posted 2 minute ago
              </p>
            </Grid>
          </Grid>
        </Paper>
      </div>
      <div className={classes.root1}>
        <TextField
          id="outlined-multiline-static"
          label=""
          multiline
          rows={2}
          variant="outlined"
        />
        <div className={classes.root2}>
          <Button variant="contained" color="primary">
            Post Comment
          </Button>
        </div>
      </div>
    </Fragment>
  );
}
