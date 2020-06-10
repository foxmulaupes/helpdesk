import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';



function refreshPage() {
  window.location.reload(false);
}

const useStyles = makeStyles((theme) =>({
  submit: {
    marginTop: theme.spacing(1),
    marginLeft: theme.spacing(95),
  },
 
}));

export default function EditTicket() {
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Button type="submit" variant="contained" color="light" className={classes.submit} onClick={handleClickOpen}>
        Withdraw
      </Button>
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Withdraw Ticket</DialogTitle>
        <DialogContent>
          
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Enter Ticket Id"
            type="id"
            fullWidth
          />
          </DialogContent>
          <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={refreshPage} color="primary">
            Submit
          </Button>
        </DialogActions>
          </Dialog>
          </div>


  );
}