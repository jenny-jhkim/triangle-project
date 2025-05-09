import React from "react"
import Alert from "@mui/material/Alert"

const AlertMessage = ({ msgType, message }) => {
    if(!message) return null;

    return <Alert severity={msgType}>{message}</Alert>
}

export default AlertMessage;