import React, { useState, useEffect, useParams } from 'react'
import Alert from '@mui/material/Alert';
import TriangleInput from '../components/TriangleInput'
import AlertMessage from '../components/AlertMessage'
import getMsgType from '../utils/getMsgType'
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
        if (!serverResult) return;
        console.log("Received Result Data: ", serverResult);
        const data = serverResult.result;

        const resultType = getMsgType(data);
        console.log("getMsgType: ", resultType);
        setMsgType(resultType);
        if(resultType ==="error"){
            setResult("");
            setMessage(serverResult.result + " : " + serverResult.explanation);
            return;
        } 

        setResult(serverResult.result);
        setMessage(serverResult.explanation);

    }

    return (
        <div>
            <h1 className='main-title-container'>Types of Triangles {mode==='edit' ? `Edit ${id}` : ""} </h1>
            <div className='home-triangle-contianer'>
                <TriangleInput mode={mode} onResult={handleResult} />
                <div className='home-message-container'>
                { result && 
                    <h2> Result: {result} </h2>
                }
                <AlertMessage msgType={msgType} message={message} />
                </div>
            </div>
        </div>
    );
}

export default Home;