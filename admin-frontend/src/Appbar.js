import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
 
  title: {
    flexGrow: 1,
  },
  panel:{

    paddingRight: '10',
  },
}));

function Appbar() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <AppBar position="static">
        
        <Toolbar>
          <Typography variant="h6" className={classes.title}>
            HelpDesk
          </Typography>


          <Typography variant="h6" className={classes.panel}>
            Admin Panel
          </Typography>
          
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default Appbar;
