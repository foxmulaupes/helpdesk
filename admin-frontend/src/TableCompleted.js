import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
const useStyles = makeStyles({
  table: {
    minWidth: 1250,
  },
});

function createData(id, issue, status, assigned,category,priority,issudate,dateresolved,userid) {
  return { id, issue, status, assigned,category,priority,issudate,dateresolved,userid};
}

const rows = [
  createData('123','Help','Completed','ABC','abc','High','02-06-20','06-04-20','45')
];

export default function SimpleTable() {
  const classes = useStyles();

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell><b>TICKET ID</b></TableCell>

            <TableCell align="right"><b>USER ID</b></TableCell>
            <TableCell align="right"><b>ISSUE</b></TableCell>
            <TableCell align="right"><b>STATUS</b></TableCell>
            <TableCell align="right"><b>ASSIGNED TO</b></TableCell>
            <TableCell align="right"><b>CATEGORY</b></TableCell>
            <TableCell align="right"><b>PRIORITY</b></TableCell>
            <TableCell align="right"><b>ISSUE DATE</b></TableCell>
            <TableCell align="right"><b>DATE RESOLVED</b></TableCell>
            
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.id}>
              <TableCell component="th" scope="row">
                {row.id}
              </TableCell>
              <TableCell align="right">{row.userid}</TableCell>
              <TableCell align="right">{row.issue}</TableCell>
              <TableCell align="right">{row.status}</TableCell>
              <TableCell align="right">{row.assigned}</TableCell>
              <TableCell align="right">{row.category}</TableCell>
              <TableCell align="right">{row.priority}</TableCell>
              <TableCell align="right">{row.issudate}</TableCell>
              <TableCell align="right">{row.dateresolved}</TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
