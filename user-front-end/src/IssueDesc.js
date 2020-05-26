import React from 'react';
import Typography from '@material-ui/core/Typography';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
//import Checkbox from '@material-ui/core/Checkbox';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';

export default function IssueDesc() {
  const [value, setValue] = React.useState('female');

const handleChange = (event) => {
  setValue(event.target.value);
};

  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom>
        Issue Description
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="Description"
            label="IssueDescription"
            helperText="Issue Description in Detail"
            fullWidth
            multiline
          />
        </Grid>
            <Grid item xs={12}>
        <FormControl component="fieldset">
        <FormLabel component="legend">Priority</FormLabel>
        <RadioGroup aria-label="gender" name="gender1" value={value} onChange={handleChange}>
          <FormControlLabel value="low" control={<Radio />} label="Low" />
          <FormControlLabel value="medium" control={<Radio />} label="Medium" />
          <FormControlLabel value="high" control={<Radio />} label="High" />
        </RadioGroup>
      </FormControl>
        </Grid>
        </Grid>
    </React.Fragment>
  );
}
