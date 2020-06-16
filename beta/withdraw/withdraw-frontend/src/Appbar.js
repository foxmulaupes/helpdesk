import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import ExitToAppOutlinedIcon from '@material-ui/icons/ExitToAppOutlined';
import ReactDOM from "react-dom";
import Avatar from '@material-ui/core/Avatar';

import App from './App';
function Render(){
    return(
    ReactDOM.render(<App />, document.getElementById("root"))
    );
  }
  

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  
  title: {
    flexGrow: 1,
  },
  small: {
    width: theme.spacing(3),
    height: theme.spacing(3),
  },
  
}));

export default function ButtonAppBar() {
  const classes = useStyles();
  

  return (
    <div className={classes.root}>
         <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" className={classes.title}>
            <b>HELPDESK</b>
          </Typography>
         
          <Avatar src="/broken-image.jpg"  className={classes.small}/>
          <Button color="inherit" >
        User
      </Button>
      <ExitToAppOutlinedIcon onClick={Render} />
        </Toolbar>
        
      </AppBar>
      </div>
  );
}