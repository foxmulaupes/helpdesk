import React, { Fragment }from 'react';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import ReactDOM from "react-dom";
import Appbar from './Appbar';
import Tick from './Tick';
import Ticket from './Ticket';

function Render(){
  return(
  ReactDOM.render(<Ticket />, document.getElementById("root"))
  );
}

const useStyles = makeStyles((theme) =>({
  root: {
    width: '100%',
    flexGrow: 1,
    '& > *': {
      margin: theme.spacing(1),
    },
  },
  submit: {
    marginLeft: theme.spacing(70),
  },
 
}));

export default function StickyHeadTable() {
  const classes = useStyles();
  
  return (
    
    <Fragment>
      <Appbar/>
      <Tick/>
      <Button type="submit"
            onClick={Render}
            variant="contained"
            color="primary"
            className={classes.submit}>
        GENERATE NEW TICKET
      </Button>
    </Fragment>
    
  );
}
