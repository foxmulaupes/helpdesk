import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import ArrowForwardIosIcon from '@material-ui/icons/ArrowForwardIos';
import Button from '@material-ui/core/Button';
import ReactDOM from "react-dom";
import Desc from './Desc';
import EditIcon from '@material-ui/icons/Edit';
function Render(){
  return(
  ReactDOM.render(<Desc />, document.getElementById("root"))
  );
}


const useStyles = makeStyles((theme) =>({
  table: {
    minWidth: 1250,
  },
  


}));

function createData(id, issue, status, assigned,category,issudate,dateresolved,userid,details) {
  return { id, issue, status, assigned,category,issudate,dateresolved,userid,details};
}

const rows = [
  createData('101','Finance','Completed','Rahul','xyz','08-06-20','10-04-20','5')
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
            <TableCell align="right"><b>DETAILS</b></TableCell>
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
              <TableCell align="right">
                <Button  color="primary"  onClick={Render}>
              <ArrowForwardIosIcon/>
        </Button>
        </TableCell>   
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}