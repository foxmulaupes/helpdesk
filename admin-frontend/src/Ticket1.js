import React, { Fragment } from "react";
import Appbar from './Appbar';
import Ticket from './Ticket';
import Helpdesk from './Helpdesk'
function Ticket1(){
    return(

        <Fragment>
                 <Appbar />
                <Helpdesk/>
               
                <Ticket />
        </Fragment>


    );
}

export default Ticket1;