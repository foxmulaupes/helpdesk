import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

const useStyles = makeStyles((theme) => ({
  formControl: {
    marginTop: theme.spacing(2),
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(0),
  },
}));

export default function SimpleSelectstatus() {
  const classes = useStyles();
  const [age, setAge] = React.useState('');

  const handleChange = (event) => {
    setAge(event.target.value);
  };

  return (
    <div>
     
      <FormControl variant="outlined" className={classes.formControl}>
        <InputLabel id="demo-simple-select-outlined-label">Status</InputLabel>
        <Select
          labelId="demo-simple-select-outlined-label"
          id="demo-simple-select-outlined"
          value={age}
          onChange={handleChange}
          label="Status"
        >
          
          <MenuItem value={10}>Completed</MenuItem>
          <MenuItem value={20}>In Progress</MenuItem>
          <MenuItem value={30}>Pending</MenuItem>
        </Select>
      </FormControl>
      
    </div>
  );
}
