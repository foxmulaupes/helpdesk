import React, { Fragment } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import container from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Appbar from './Appbar';
import ReactDOM from "react-dom";
import User from './User';
function Render(){
  return(
  ReactDOM.render(<User />, document.getElementById("root"))
  );
}

const useStyles = makeStyles((theme) =>({
  root: {
    
    minHeight:300
  },
  
  title: {
    fontSize: 30,
  },
  
  submit: {
    marginTop: theme.spacing(5),
    marginLeft: theme.spacing(80),
  },
}));

export default function OutlinedCard() {
  const classes = useStyles();
  


  return (
     <Fragment>
    <Card className={classes.root} variant="outlined">
           <Appbar/>
      <CardContent>
        <Typography className={classes.title} color="inherit" gutterBottom>
          Ticket Description
        </Typography>
        <Typography variant="h6" color="textSecondary" gutterBottom>
        A help desk support system enables the companies to resolve customer issues efficiently by simply 
        automating complaint resolution process with ticket management. The lines can sometimes be blurred between
        a help desk management system or IT service management
        </Typography>
        
        </CardContent>
    </Card>
    <Button 
    type="submit"
    onClick={Render}
    variant="contained"
    color="primary"
    className={classes.submit}>CLOSE</Button>
    </Fragment>
  );
}