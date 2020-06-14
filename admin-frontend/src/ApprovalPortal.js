import React from 'react';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import AppBar from "./Appbar";
//import FormControlLabel from '@material-ui/core/FormControlLabel';
//import Checkbox from '@material-ui/core/Checkbox';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import ReactDOM from "react-dom";
import Avatar from '@material-ui/core/Avatar';
import Toolbar from '@material-ui/core/Toolbar';
import SearchIcon from '@material-ui/icons/Search';
import EditTicket from './EditTicket';
import InputBase from '@material-ui/core/InputBase';
import AdminApproval from "./AdminApproval"

function Render(){
    return(
    ReactDOM.render(<AdminApproval />, document.getElementById("root"))
    );
}



function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {'Copyright © '}
        <Link color="inherit" href="https://material-ui.com/">
          Foxmula
        </Link>{' '}
        {new Date().getFullYear()}
        {'.'}
      </Typography>
    );
  }

  const useStyles1 = makeStyles((theme) => ({
    paper: {
      marginTop: theme.spacing(8),
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
    },
    avatar: {
      margin: theme.spacing(1),
      backgroundColor: theme.palette.secondary.main,
    },
    form: {
      width: '100%', // Fix IE 11 issue.
      marginTop: theme.spacing(1),
    },
    submit: {
      margin: theme.spacing(3, 0, 2),
      
    },
  }));
  
  
export default function ApprovalPortal() {
const [value, setValue] = React.useState('Approve');
const classes = useStyles1();
  const handleChange = (event) => {
    setValue(event.target.value);
  };
  return (
    <form className={classes.form} noValidate>
    <div className={classes.grow}>
    <AppBar position="static">
        <Toolbar>
        <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" className={classes.Av} />
          
          <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase
              placeholder="Search…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
           
          </div>

          <EditTicket/>
              
          <AdminApproval/>
          <div className={classes.grow} />
          <div className={classes.sectionDesktop} >
          
          <Button
            type="submit"
           
            
            variant="contained"
            color="light"
            className={classes.submit}
          >
            Log Out
          </Button>

                

          </div>
          
        </Toolbar>
      </AppBar>
      </div>
    <React.Fragment>
      <Typography variant="h6" gutterBottom>
        TicketDate
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="TicketDate"
            //label="TicketDate"
            //helperText="Tag Watchers to your Issue"
            fullWidth
          />
        </Grid>
      </Grid>
      <Typography variant="h6" gutterBottom>
        Ticket Title
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="TicketTitle"
            //label="TicketTitle"
            //helperText="Tag Watchers to your Issue"
            fullWidth
          />
        </Grid>
      </Grid>
      <Typography variant="h6" gutterBottom>
        Ticket Description
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="TicketDiscription"
            //label="TicketDescription"
            //helperText="Tag Watchers to your Issue"
            fullWidth
          />
        </Grid>
      </Grid>

      <Typography variant="h6" gutterBottom>
        Watchers
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="Watchers"
            //label="Watchers Details"
            //helperText="Tag Watchers to your Issue"
            fullWidth
          />
        </Grid>
      </Grid>
      <Typography variant="h6" gutterBottom>
        Priority
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="Priority"
            //label="Priority"
            //helperText="Tag Watchers to your Issue"
            fullWidth
          />
        </Grid>
      </Grid>
      <Typography variant="h6" gutterBottom>
        Approver Option
      </Typography>
      <Grid container spacing={5}>
      <Grid item xs={12}>
        <FormControl component="fieldset">
        <RadioGroup aria-label="approveroption" name="approveroption" value={value} onChange={handleChange}>
          <FormControlLabel value="Approve" control={<Radio />} label="Approve" />
          <FormControlLabel value="Reject" control={<Radio />} label="Reject" />
          <FormControlLabel value="Pending" control={<Radio />} label="Pending" />
        </RadioGroup>
      </FormControl>
        </Grid>
      </Grid>
    </React.Fragment>
    <Button
            type="submit"
            onClick={Render}
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Submit
          </Button>

        </form>
        );
}
