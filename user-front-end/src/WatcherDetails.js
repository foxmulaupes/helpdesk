import React from 'react';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
//import FormControlLabel from '@material-ui/core/FormControlLabel';
//import Checkbox from '@material-ui/core/Checkbox';

export default function WatcherDetails() {
  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom>
        Watchers
      </Typography>
      <Grid container spacing={5}>
        <Grid item xs={12}>
          <TextField
            required
            id="Watchers"
            label="Watchers Details"
            helperText="Tag Watchers to your Issue"
            fullWidth
          />
        </Grid>
      </Grid>
    </React.Fragment>
  );
}
