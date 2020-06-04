import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Addcomment from './Addcomment';
const useStyles = makeStyles({
  table: {
    minWidth: 1250,
  },
});

function createData(id, issue, status, assigned,category,issudate,dateresolved,userid,comment) {
  return { id, issue, status, assigned,category,issudate,dateresolved,userid,comment};
}

const rows = [
  createData('123','Help','Completed','ABC','abc','02-06-20','06-04-20','45',<Addcomment/>)
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
            <TableCell align="right"><b>ISSUE DATE</b></TableCell>
            <TableCell align="right"><b>DATE RESOLVED</b></TableCell>
            <TableCell align="right"><b>ADD COMMENT</b></TableCell>
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
              <TableCell align="right">{row.issudate}</TableCell>
              <TableCell align="right">{row.dateresolved}</TableCell>
              <TableCell align="right">{row.comment}</TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
