import React, { useState, useEffect, useParams } from 'react'
import Alert from '@mui/material/Alert';
import CheckIcon from '@mui/icons-material/Check';
import TriangleInput from '../components/TriangleInput'
import './Home.css';


function Home() {
    const [mode, setMode] = useState("save");
    const [result, setResult] = useState("");
    const [message, setMessage] = useState("");
    const [msgType, setMsgType] = useState("success");
    //const { id } = useParams();
    const id = "";

    const clearResult = () => {
        setResult("");
        setMessage("");
    }

    const handleResult = (serverResult) => {
        clearResult();
        console.log("Received Data: ", serverResult);
        const result = serverResult.result;

        if(result.toLowerCase().includes("error")){
            setMessage(serverResult.result + " : " + serverResult.explanation);
            setMsgType("error");
            return;
            
        } 
        if(result.toLowerCase().includes("not a triangle")) {
            setMsgType("info");
        } else {
            setMsgType("success");
        }
        setResult(serverResult.result);
        setMessage(serverResult.explanation);
    }

    return (
        <div>
            <h1 className='main-title-container'>Types of Triangles {mode==='edit' ? `Edit ${id}` : ""} </h1>
            <p></p>
            <div className='home-triangle-contianer'>
                <TriangleInput mode={mode} onResult={handleResult} />
                <div className='home-message-container'>
                { result && 
                    <h2> Result: {result} </h2>
                }
                { message &&
                    <Alert severity={msgType}>
                        {message}
                    </Alert>
                }
                </div>
            </div>
        </div>
    );
}

export default Home;